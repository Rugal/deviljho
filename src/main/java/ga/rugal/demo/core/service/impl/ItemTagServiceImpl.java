package ga.rugal.demo.core.service.impl;

import ga.rugal.demo.core.dao.ItemTagDao;
import ga.rugal.demo.core.service.ItemTagService;

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
}
