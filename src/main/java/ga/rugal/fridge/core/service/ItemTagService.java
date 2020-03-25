package ga.rugal.fridge.core.service;

import ga.rugal.fridge.core.dao.ItemTagDao;
import ga.rugal.fridge.core.entity.Item;
import ga.rugal.fridge.core.entity.ItemTag;
import ga.rugal.fridge.core.entity.Tag;

public interface ItemTagService extends BaseService<ItemTagDao> {

  /**
   * Attach tag to this item.
   *
   * @param item this item object
   * @param tag  target target
   *
   * @return ItemTag object
   */
  ItemTag attachTag(Item item, Tag tag);
}
