package ga.rugal.fridge.springmvc.mapper;

import java.util.List;

import ga.rugal.fridge.core.entity.Item;
import ga.rugal.fridge.graphql.ItemDto;
import ga.rugal.fridge.graphql.ItemInputDto;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapstruct mapper for Item.
 *
 * @author Rugal Bernstein
 */
@Mapper(config = CentralConfig.class)
@SuppressFBWarnings("UUF_UNUSED_PUBLIC_OR_PROTECTED_FIELD")
public interface ItemMapper {

  ItemMapper I = Mappers.getMapper(ItemMapper.class);

  Item to(ItemDto itemDto);

  Item to(ItemInputDto input);

  ItemDto from(Item item);

  List<ItemDto> froms(List<Item> it);
}
