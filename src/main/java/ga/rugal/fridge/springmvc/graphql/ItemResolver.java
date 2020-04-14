package ga.rugal.fridge.springmvc.graphql;

import java.util.Collection;
import java.util.stream.Collectors;

import ga.rugal.fridge.core.service.ItemTagService;
import ga.rugal.fridge.graphql.ItemDto;
import ga.rugal.fridge.graphql.TagDto;
import ga.rugal.fridge.springmvc.mapper.ItemMapper;
import ga.rugal.fridge.springmvc.mapper.TagMapper;

import com.coxautodev.graphql.tools.GraphQLResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Item resolver.
 *
 * @author Rugal Bernstein
 */
@Component
public class ItemResolver implements GraphQLResolver<ItemDto> {

  @Autowired
  private ItemTagService itemTagService;

  /**
   * Resolve tags field for item.
   *
   * @param dto target item object
   *
   * @return list of tag of this item
   */
  public Collection<TagDto> tags(final ItemDto dto) {
    return this.itemTagService.getDao()
            .findByItem(ItemMapper.I.to(dto)).stream()
            .map(itemTag -> TagMapper.I.from(itemTag.getTag()))
            .collect(Collectors.toList());
  }
}
