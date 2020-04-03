package ga.rugal.fridge.core.service.impl;

import ga.rugal.UnitTestBase;
import ga.rugal.fridge.core.dao.ItemTagDao;
import ga.rugal.fridge.core.entity.Item;
import ga.rugal.fridge.core.entity.Tag;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ItemTagServiceImplTest extends UnitTestBase {

  @Autowired
  private Item item;

  @Autowired
  private Tag tag;

  @Autowired
  private ItemTagDao dao;

  private final ItemTagServiceImpl service = new ItemTagServiceImpl();

  @BeforeEach
  public void setUp() {
    this.service.setDao(this.dao);
    this.service.getDao();
  }

  @Test
  public void attachTag() {
    Assertions.assertNotNull(this.tag);
    Assertions.assertNotNull(this.item);
    this.service.attachTag(this.item, this.tag);
  }
}
