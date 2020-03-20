package ga.rugal.fridge.springmvc.graphql;

import java.util.Optional;

import ga.rugal.fridge.core.entity.Item;
import ga.rugal.fridge.core.entity.ItemTag;
import ga.rugal.fridge.core.entity.Storage;
import ga.rugal.fridge.core.entity.Tag;
import ga.rugal.fridge.core.service.ItemService;
import ga.rugal.fridge.core.service.ItemTagService;
import ga.rugal.fridge.core.service.StorageService;
import ga.rugal.fridge.core.service.TagService;
import ga.rugal.fridge.graphql.ConsumeInputDto;
import ga.rugal.fridge.graphql.FillInputDto;
import ga.rugal.fridge.graphql.ItemDto;
import ga.rugal.fridge.graphql.ItemInputDto;
import ga.rugal.fridge.graphql.ItemTagDto;
import ga.rugal.fridge.graphql.Mutation;
import ga.rugal.fridge.graphql.StorageDto;
import ga.rugal.fridge.graphql.TagDto;
import ga.rugal.fridge.graphql.TagInputDto;
import ga.rugal.fridge.graphql.TagItemInputDto;
import ga.rugal.fridge.springmvc.mapper.ItemMapper;
import ga.rugal.fridge.springmvc.mapper.ItemTagMapper;
import ga.rugal.fridge.springmvc.mapper.StorageMapper;
import ga.rugal.fridge.springmvc.mapper.TagMapper;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * GraphQL mutation.
 *
 * @author Rugal Bernstein
 */
@Component
public class RootMutation implements GraphQLMutationResolver, Mutation {

  @Autowired
  private ItemService itemService;

  @Autowired
  private TagService tagService;

  @Autowired
  private StorageService storageService;

  @Autowired
  private ItemTagService itemTagService;

  @Override
  public ItemDto addItem(final ItemInputDto input) throws Exception {
    return ItemMapper.I.from(this.itemService.getDao().save(ItemMapper.I.to(input)));
  }

  @Override
  public TagDto addTag(final TagInputDto input) throws Exception {
    return TagMapper.I.from(this.tagService.getDao().save(TagMapper.I.to(input)));
  }

  @Override
  public ItemTagDto tagItem(final TagItemInputDto input) throws Exception {
    final Optional<Item> optionalItem = this.itemService.getDao().findById(input.getIid());
    final Optional<Tag> optionalTag = this.tagService.getDao().findById(input.getIid());
    if (optionalItem.isEmpty()) {
      throw new RuntimeException("Item not found");
    }
    if (optionalTag.isEmpty()) {
      throw new RuntimeException("Tag not found");
    }
    final ItemTag it = this.itemTagService.getDao().save(new ItemTag(optionalItem.get(),
                                                                     optionalTag.get()));
    return ItemTagMapper.I.from(it);
  }

  @Override
  public StorageDto consume(final ConsumeInputDto input) throws Exception {
    final Optional<Storage> optional = this.storageService.getDao().findById(input.getSid());
    if (optional.isEmpty()) {
      throw new RuntimeException("Storage not found");
    }
    final Storage c = this.storageService.consume(optional.get(), input.getQuantity());
    return StorageMapper.I.from(c);
  }

  @Override
  public StorageDto fill(final FillInputDto input) throws Exception {
    final Optional<Item> optional = this.itemService.getDao().findById(input.getIid());
    if (optional.isEmpty()) {
      throw new RuntimeException("Storage not found");
    }
    final Storage c = this.storageService.fill(optional.get(), input.getQuantity());
    return StorageMapper.I.from(c);
  }
}
