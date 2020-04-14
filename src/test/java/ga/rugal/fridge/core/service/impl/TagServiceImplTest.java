package ga.rugal.fridge.core.service.impl;

import java.util.Optional;

import ga.rugal.UnitTestBase;
import ga.rugal.fridge.core.dao.TagDao;
import ga.rugal.fridge.core.entity.Item;
import ga.rugal.fridge.core.entity.Tag;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
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

    BDDMockito.given(this.dao.findByName(Mockito.any()))
            .willReturn(Optional.of(this.tag));
    BDDMockito.given(this.dao.save(Mockito.any()))
            .willReturn(this.tag);
  }

  @Test
  public void getDao() {
    Assertions.assertNotNull(this.tag);
    Assertions.assertNotNull(this.item);
    this.service.getDao();
  }

  @Test
  public void getOrSave_get() {
    Assertions.assertNotNull(this.tag);
    Assertions.assertNotNull(this.item);
    Assertions.assertNotNull(this.service.getOrSave(this.tag));

    BDDMockito.verify(this.dao, Mockito.never()).save(Mockito.any());
  }

  @Test
  public void getOrSave_save() {
    BDDMockito.given(this.dao.findByName(Mockito.any()))
            .willReturn(Optional.empty());

    Assertions.assertNotNull(this.tag);
    Assertions.assertNotNull(this.item);
    Assertions.assertNotNull(this.service.getOrSave(this.tag));

    BDDMockito.verify(this.dao).save(Mockito.any());
  }
}
