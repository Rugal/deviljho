package ga.rugal.demo.core.service.impl;

import ga.rugal.demo.core.dao.FridgeStorageDao;
import ga.rugal.demo.core.service.FridgeStorageService;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FridgeStorageServiceImpl implements FridgeStorageService {

  @Autowired
  @Getter
  @Setter
  private FridgeStorageDao dao;
}
