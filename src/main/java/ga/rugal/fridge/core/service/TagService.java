package ga.rugal.fridge.core.service;

import ga.rugal.fridge.core.dao.TagDao;
import ga.rugal.fridge.core.entity.Tag;

public interface TagService extends BaseService<TagDao> {

  /**
   * Create a tag if name not found in system, or get the target by name.
   *
   * @param tag a tag object with name
   *
   * @return tag object with name matched
   */
  Tag getOrSave(Tag tag);
}
