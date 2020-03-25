package ga.rugal.fridge.core.service.impl;

import ga.rugal.fridge.core.dao.ItemTagDao;
import ga.rugal.fridge.core.entity.Item;
import ga.rugal.fridge.core.entity.ItemTag;
import ga.rugal.fridge.core.entity.Tag;
import ga.rugal.fridge.core.service.ItemTagService;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemTagServiceImpl implements ItemTagService {

  @Autowired
  @Getter
  @Setter
  private ItemTagDao dao;

  /**
   * {@inheritDoc}
   */
  @Override
  public ItemTag attachTag(final Item item, final Tag tag) {
    return this.dao.save(new ItemTag(item, tag));
  }
}
