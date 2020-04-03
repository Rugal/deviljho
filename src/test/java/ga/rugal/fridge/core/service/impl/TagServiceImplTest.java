package ga.rugal.fridge.core.service.impl;

import ga.rugal.UnitTestBase;
import ga.rugal.fridge.core.dao.TagDao;
import ga.rugal.fridge.core.entity.Item;
import ga.rugal.fridge.core.entity.Tag;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TagServiceImplTest extends UnitTestBase {

  @Autowired
  private Item item;

  @Autowired
  private Tag tag;

  @Autowired
  private TagDao dao;

  private final TagServiceImpl service = new TagServiceImpl();

  @BeforeEach
  public void setUp() {
    this.service.setDao(this.dao);
    this.service.getDao();
  }

  @Test
  public void getDao() {
    Assertions.assertNotNull(this.tag);
    Assertions.assertNotNull(this.item);
    this.service.getDao();
  }
}
