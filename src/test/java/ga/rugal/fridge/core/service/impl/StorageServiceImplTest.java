package ga.rugal.fridge.core.service.impl;

import java.util.Optional;

import ga.rugal.UnitTestBase;
import ga.rugal.fridge.core.dao.HistoryDao;
import ga.rugal.fridge.core.dao.StorageDao;
import ga.rugal.fridge.core.entity.Item;
import ga.rugal.fridge.core.entity.Storage;
import ga.rugal.fridge.core.service.HistoryService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

public class StorageServiceImplTest extends UnitTestBase {

  @Autowired
  private Storage storage;

  @Autowired
  private Item item;

  @Autowired
  private StorageDao dao;

  @Autowired
  private HistoryDao historyDao;

  @Autowired
  private HistoryService historyService;

  private final StorageServiceImpl service = new StorageServiceImpl();

  @BeforeEach
  public void setUp() {
    this.service.setDao(this.dao);
    this.service.getDao();
    this.service.setHistoryService(this.historyService);

    BDDMockito.given(this.historyService.getDao())
            .willReturn(this.historyDao);

    BDDMockito.given(this.dao.save(Mockito.any()))
            .willReturn(this.storage);
  }

  @Test
  public void consume_not_enough() {
    Assertions.assertThrows(RuntimeException.class,
                            () -> this.service.consume(this.storage,
                                                       this.storage.getQuantity() + 1)
    );
  }

  @Test
  public void consume() {
    final Optional<Storage> consume = this.service.consume(this.storage,
                                                           this.storage.getQuantity() - 1);
    Assertions.assertTrue(consume.isPresent());
  }

  @Test
  public void consume_empty() {
    final Optional<Storage> consume = this.service.consume(this.storage,
                                                           this.storage.getQuantity());
    Assertions.assertTrue(consume.isEmpty());
  }

  @Test
  public void fill_not_positive() {
    Assertions.assertThrows(RuntimeException.class,
                            () -> this.service.fill(this.item, 0));
  }

  @Test
  public void fill() {
    Assertions.assertNotNull(this.service.fill(this.item, 1));
  }
}
