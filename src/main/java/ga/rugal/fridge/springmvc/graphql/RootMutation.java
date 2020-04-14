package ga.rugal.fridge.springmvc.graphql;

import java.util.Objects;
import java.util.Optional;

import ga.rugal.fridge.core.entity.Item;
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
import ga.rugal.fridge.springmvc.graphql.exception.ItemDuplicateException;
import ga.rugal.fridge.springmvc.graphql.exception.ItemNotFoundException;
import ga.rugal.fridge.springmvc.graphql.exception.StorageNotFoundException;
import ga.rugal.fridge.springmvc.graphql.exception.TagDuplicateException;
import ga.rugal.fridge.springmvc.mapper.ItemMapper;
import ga.rugal.fridge.springmvc.mapper.ItemTagMapper;
import ga.rugal.fridge.springmvc.mapper.StorageMapper;
import ga.rugal.fridge.springmvc.mapper.TagMapper;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * GraphQL mutation.
 *
 * @author Rugal Bernstein
 */
@Component
@Slf4j
public class RootMutation implements GraphQLMutationResolver, Mutation {

  @Autowired
  private ItemService itemService;

  @Autowired
  private TagService tagService;

  @Autowired
  private StorageService storageService;

  @Autowired
  private ItemTagService itemTagService;

  /**
   * Parse input object into Tag object. <BR>
   * Create a tag if not found in system, or get it by tag name.
   *
   * @param input tag input DTO
   *
   * @return tag object with name matched
   */
  private Tag doAddTag(final TagInputDto input) {
    // get tag if does exist, otherwise create new tag
    return this.tagService.getOrSave(TagMapper.I.to(input));
  }

  @Override
  public ItemDto addItem(final ItemInputDto input) throws Exception {
    // check uniquiness
    if (this.itemService.getDao().existsByName(input.getName())) {
      throw new ItemDuplicateException();
    }
    // save item itself
    final Item to = this.itemService.getDao().save(ItemMapper.I.to(input));
    if (Objects.nonNull(input.getTags())) {
      input.getTags().stream()
              // attach tag to this item
              .forEach(t -> this.itemTagService.attachTag(to, this.doAddTag(t)));
    }
    return ItemMapper.I.from(to);
  }

  @Override
  public TagDto addTag(final TagInputDto input) throws Exception {
    if (this.tagService.getDao().existsByName(input.getName())) {
      throw new TagDuplicateException();
    }
    return TagMapper.I.from(this.doAddTag(input));
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

    return ItemTagMapper.I.from(this.itemTagService.attachTag(optionalItem.get(),
                                                              optionalTag.get()));
  }

  @Override
  public StorageDto consume(final ConsumeInputDto input) throws Exception {
    final Optional<Storage> optional = this.storageService.getDao().findById(input.getSid());
    if (optional.isEmpty()) {
      throw new StorageNotFoundException(input.getSid());
    }
    final Optional<Storage> c = this.storageService.consume(optional.get(), input.getQuantity());
    return c.isEmpty()
           ? null
           : StorageMapper.I.from(c.get());
  }

  @Override
  public StorageDto fill(final FillInputDto input) throws Exception {
    final Optional<Item> optional = this.itemService.getDao().findById(input.getIid());
    if (optional.isEmpty()) {
      throw new ItemNotFoundException(input.getIid());
    }
    final Storage c = this.storageService.fill(optional.get(), input.getQuantity());
    return StorageMapper.I.from(c);
  }
}
