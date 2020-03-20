package ga.rugal.fridge.core.service.impl;

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

  @Nonnull
  @Transactional
  public Storage consume(final @Nonnull Storage s, final int number) {
    if (s.getQuantity() < number) {
      throw new RuntimeException("Not enough storage");
    }
    this.historyService.getDao().save(new History(s.getItem(), number));
    return this.dao.save(s.consume(number));
  }

  @Nonnull
  @Transactional
  public Storage fill(final @Nonnull Item item, final int number) {
    this.historyService.getDao().save(new History(item, number));
    return this.dao.save(new Storage(item, number));
  }
}
