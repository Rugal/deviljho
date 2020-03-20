package ga.rugal.fridge.core.service.impl;

import ga.rugal.fridge.core.dao.HistoryDao;
import ga.rugal.fridge.core.service.HistoryService;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoryServiceImpl implements HistoryService {

  @Autowired
  @Getter
  @Setter
  private HistoryDao dao;
}
