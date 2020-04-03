package config;

import ga.rugal.fridge.core.dao.HistoryDao;
import ga.rugal.fridge.core.dao.ItemDao;
import ga.rugal.fridge.core.dao.ItemTagDao;
import ga.rugal.fridge.core.dao.StorageDao;
import ga.rugal.fridge.core.dao.TagDao;
import ga.rugal.fridge.core.service.HistoryService;
import ga.rugal.fridge.core.service.ItemService;
import ga.rugal.fridge.core.service.ItemTagService;
import ga.rugal.fridge.core.service.StorageService;
import ga.rugal.fridge.core.service.TagService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 *
 * @author Rugal Bernstein
 */
@Configuration
public class UnitTestApplicationContext {

  @Bean
  @ConditionalOnMissingBean
  public ObjectMapper objectMapper() {
    return new ObjectMapper();
  }

  @Bean
  @Scope("prototype")
  public HistoryDao historyDao() {
    return Mockito.mock(HistoryDao.class);
  }

  @Bean
  @Scope("prototype")
  public ItemDao itemDao() {
    return Mockito.mock(ItemDao.class);
  }

  @Bean
  @Scope("prototype")
  public ItemTagDao itemTagDao() {
    return Mockito.mock(ItemTagDao.class);
  }

  @Bean
  @Scope("prototype")
  public StorageDao storageDao() {
    return Mockito.mock(StorageDao.class);
  }

  @Bean
  @Scope("prototype")
  public TagDao tagDao() {
    return Mockito.mock(TagDao.class);
  }

  @Bean
  @Scope("prototype")
  public HistoryService historyService() {
    return Mockito.mock(HistoryService.class);
  }

  @Bean
  @Scope("prototype")
  public ItemService itemService() {
    return Mockito.mock(ItemService.class);
  }

  @Bean
  @Scope("prototype")
  public ItemTagService itemTagService() {
    return Mockito.mock(ItemTagService.class);
  }

  @Bean
  @Scope("prototype")
  public StorageService storageService() {
    return Mockito.mock(StorageService.class);
  }

  @Bean
  @Scope("prototype")
  public TagService tagService() {
    return Mockito.mock(TagService.class);
  }
}
