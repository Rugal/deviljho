package ga.rugal.fridge.springmvc.mapper;

import ga.rugal.fridge.core.entity.ItemTag;
import ga.rugal.fridge.graphql.ItemTagDto;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapstruct mapper for Item Tag.
 *
 * @author Rugal Bernstein
 */
@Mapper(config = CentralConfig.class)
@SuppressFBWarnings("UUF_UNUSED_PUBLIC_OR_PROTECTED_FIELD")
public interface ItemTagMapper {

  ItemTagMapper I = Mappers.getMapper(ItemTagMapper.class);

  ItemTagDto from(ItemTag it);
}
