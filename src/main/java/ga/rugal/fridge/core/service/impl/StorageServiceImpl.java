package ga.rugal.fridge.core.service.impl;

import java.util.Optional;
import javax.annotation.Nonnull;

import ga.rugal.fridge.core.dao.StorageDao;
import ga.rugal.fridge.core.entity.History;
import ga.rugal.fridge.core.entity.Item;
import ga.rugal.fridge.core.entity.Storage;
import ga.rugal.fridge.core.service.HistoryService;
import ga.rugal.fridge.core.service.StorageService;
import ga.rugal.fridge.springmvc.graphql.exception.FillPositiveException;
import ga.rugal.fridge.springmvc.graphql.exception.StorageNotEnoughException;

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
   * {@inheritDoc}
   */
  @Nonnull
  @Transactional
  @Override
  public Optional<Storage> consume(final @Nonnull Storage s, final int number) {
    if (s.getQuantity() < number) {
      throw new StorageNotEnoughException(s.getSid());
    }
    // consume is negative
    this.historyService.getDao().save(History.consume(s.getItem(), number));
    if (s.getQuantity() > number) {
      return Optional.of(this.dao.save(s.consume(number)));
    }
    // remove storage item if all consumed
    this.dao.deleteById(s.getSid());
    return Optional.empty();
  }

  /**
   * {@inheritDoc}
   */
  @Nonnull
  @Transactional
  @Override
  public Storage fill(final @Nonnull Item item, final int number) {
    if (number < 1) {
      throw new FillPositiveException();
    }
    // Save history
    this.historyService.getDao().save(History.fill(item, number));
    return this.dao.save(new Storage(item, number));
  }
}
