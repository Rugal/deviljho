package ga.rugal.fridge.springmvc.graphql;

import java.util.Collection;

import ga.rugal.fridge.core.service.ItemService;
import ga.rugal.fridge.core.service.StorageService;
import ga.rugal.fridge.core.service.TagService;
import ga.rugal.fridge.graphql.ItemDto;
import ga.rugal.fridge.graphql.Query;
import ga.rugal.fridge.graphql.StorageDto;
import ga.rugal.fridge.graphql.TagDto;
import ga.rugal.fridge.springmvc.mapper.ItemMapper;
import ga.rugal.fridge.springmvc.mapper.StorageMapper;
import ga.rugal.fridge.springmvc.mapper.TagMapper;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * GraphQL root query.
 *
 * @author Rugal Bernstein
 */
@Component
public class RootQuery implements GraphQLQueryResolver, Query {

  @Autowired
  private ItemService itemService;

  @Autowired
  private TagService tagService;

  @Autowired
  private StorageService storageService;

  @Override
  public Collection<TagDto> tags() throws Exception {
    return TagMapper.I.froms(Lists.newArrayList(this.tagService.getDao().findAll()));
  }

  @Override
  public Collection<ItemDto> items() throws Exception {
    return ItemMapper.I.froms(Lists.newArrayList(this.itemService.getDao().findAll()));
  }

  @Override
  public Collection<StorageDto> storages() throws Exception {
    return StorageMapper.I.froms(Lists.newArrayList(this.storageService.getDao().findAll()));
  }
}
