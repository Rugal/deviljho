package ga.rugal.fridge.springmvc.mapper;

import java.util.List;

import ga.rugal.fridge.core.entity.Item;
import ga.rugal.fridge.core.entity.Tag;
import ga.rugal.fridge.graphql.TagDto;
import ga.rugal.fridge.graphql.TagInputDto;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapstruct mapper for Tag.
 *
 * @author Rugal Bernstein
 */
@Mapper(config = CentralConfig.class)
@SuppressFBWarnings("UUF_UNUSED_PUBLIC_OR_PROTECTED_FIELD")
public interface TagMapper {

  TagMapper I = Mappers.getMapper(TagMapper.class);

  Item to(TagDto tagDto);

  Tag to(TagInputDto dto);

  TagDto from(Tag item);

  List<TagDto> froms(List<Tag> it);
}
