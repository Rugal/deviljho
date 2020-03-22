package ga.rugal.fridge.core.service.impl;

import java.util.Optional;
import javax.annotation.Nonnull;

import ga.rugal.fridge.core.dao.StorageDao;
import ga.rugal.fridge.core.entity.History;
import ga.rugal.fridge.core.entity.Item;
import ga.rugal.fridge.core.entity.Storage;
import ga.rugal.fridge.core.service.HistoryService;
import ga.rugal.fridge.core.service.StorageService;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StorageServiceImpl implements StorageService {

  @Autowired
  @Getter
  @Setter
  private StorageDao dao;

  @Autowired
  @Setter
  private HistoryService historyService;

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
  @Override
  public Optional<Storage> consume(final @Nonnull Storage s, final int number) {
    if (s.getQuantity() < number) {
      throw new RuntimeException("Not enough storage");
    }
    // consume is negative
    this.historyService.getDao().save(new History(s.getItem(), -1 * number));
    if (s.getQuantity() > number) {
      return Optional.of(this.dao.save(s.consume(number)));
    }
    // remove storage item if all consumed
    this.dao.deleteById(s.getSid());
    return Optional.empty();
  }

  /**
   * Fill in item with quantity.
   *
   * @param item   item object
   * @param number fill quantity, positive number
   *
   * @return new storage record
   */
  @Nonnull
  @Transactional
  @Override
  public Storage fill(final @Nonnull Item item, final int number) {
    this.historyService.getDao().save(new History(item, number));
    return this.dao.save(new Storage(item, number));
  }
}
