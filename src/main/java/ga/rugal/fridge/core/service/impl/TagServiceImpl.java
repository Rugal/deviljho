package ga.rugal.fridge.core.service.impl;

import ga.rugal.fridge.core.dao.TagDao;
import ga.rugal.fridge.core.entity.Tag;
import ga.rugal.fridge.core.service.TagService;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TagServiceImpl implements TagService {

  @Autowired
  @Getter
  @Setter
  private TagDao dao;

  /**
   * {@inheritDoc}
   */
  @Override
  public Tag getOrSave(final Tag tag) {
    return this.dao.findByName(tag.getName())
            .orElseGet(() -> this.dao.save(tag));
  }
}
