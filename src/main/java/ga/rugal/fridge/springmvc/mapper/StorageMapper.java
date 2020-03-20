package ga.rugal.fridge.springmvc.mapper;

import java.util.List;

import ga.rugal.fridge.core.entity.Storage;
import ga.rugal.fridge.graphql.StorageDto;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapstruct mapper for Storage.
 *
 * @author Rugal Bernstein
 */
@Mapper(config = CentralConfig.class)
@SuppressFBWarnings("UUF_UNUSED_PUBLIC_OR_PROTECTED_FIELD")
public interface StorageMapper {

  StorageMapper I = Mappers.getMapper(StorageMapper.class);

  Storage to(StorageDto dto);

  StorageDto from(Storage item);

  List<StorageDto> froms(List<Storage> it);
}
