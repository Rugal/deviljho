package ga.rugal.fridge.core.service;

import javax.annotation.Nonnull;

import ga.rugal.fridge.core.dao.StorageDao;
import ga.rugal.fridge.core.entity.Item;
import ga.rugal.fridge.core.entity.Storage;

import org.springframework.transaction.annotation.Transactional;

public interface StorageService extends BaseService<StorageDao> {

  @Nonnull
  @Transactional
  Storage consume(@Nonnull Storage s, int number);

  @Nonnull
  @Transactional
  Storage fill(@Nonnull Item item, int number);
}
