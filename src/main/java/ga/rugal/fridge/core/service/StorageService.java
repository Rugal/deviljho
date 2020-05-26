package ga.rugal.fridge.core.service;

import java.util.Optional;
import javax.annotation.Nonnull;

import ga.rugal.fridge.core.dao.StorageDao;
import ga.rugal.fridge.core.entity.Item;
import ga.rugal.fridge.core.entity.Storage;

import org.springframework.transaction.annotation.Transactional;

public interface StorageService extends BaseService<StorageDao> {

  /**
   * Consume existing storage.
   *
   * @param s      existing storage object
   * @param number a positive number
   *
   * @return empty object if last item is consumed, otherwise an updated storage object
   *
   * @throws RuntimeException not enough storage for consumption
   */
  @Nonnull
  @Transactional
  Optional<Storage> consume(@Nonnull Storage s, int number);

  /**
   * Fill in item with quantity.
   *
   * @param item   item object
   * @param number fill quantity, positive number
   *
   * @return new storage record
   *
   * @throws RuntimeException Fill quantity is not positive
   */
  @Nonnull
  @Transactional
  Storage fill(@Nonnull Item item, int number);
}
