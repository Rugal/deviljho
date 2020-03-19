package ga.rugal.demo.springmvc.graphql;

import ga.rugal.demo.core.entity.FridgeStorage;
import ga.rugal.demo.core.entity.Item;
import ga.rugal.demo.core.entity.Tag;
import ga.rugal.demo.core.service.FridgeStorageService;
import ga.rugal.demo.core.service.ItemService;
import ga.rugal.demo.core.service.TagService;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * GraphQL root query for Course.
 *
 * @author Rugal Bernstein
 */
@Component
public class RootQuery implements GraphQLQueryResolver {

  @Autowired
  private ItemService itemService;

  @Autowired
  private TagService tagService;

  @Autowired
  private FridgeStorageService fridgeStorageService;

  public Iterable<Item> items() {
    return this.itemService.getDao().findAll();
  }

  public Iterable<Tag> tags() {
    return this.tagService.getDao().findAll();
  }

  public Iterable<FridgeStorage> storages() {
    return this.fridgeStorageService.getDao().findAll();
  }
}
