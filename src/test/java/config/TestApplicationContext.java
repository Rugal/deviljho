package config;

import ga.rugal.fridge.core.entity.History;
import ga.rugal.fridge.core.entity.Item;
import ga.rugal.fridge.core.entity.ItemTag;
import ga.rugal.fridge.core.entity.Storage;
import ga.rugal.fridge.core.entity.Tag;

import com.github.javafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Rugal Bernstein
 */
@Configuration
public class TestApplicationContext {

  @Bean
  public Faker faker() {
    return new Faker();
  }

  @Bean
  public Item item(final Faker faker) {
    final Item item = new Item();
    item.setName(faker.name().name());
    return item;
  }

  @Bean
  public Tag tag(final Faker faker) {
    final Tag tag = new Tag();
    tag.setName(faker.funnyName().name());
    return tag;
  }

  @Bean
  public Storage storage(final Item item, final Faker faker) {
    return new Storage(item, faker.number().numberBetween(1, 10));
  }

  @Bean
  public History history(final Item item, final Faker faker) {
    return new History(item, faker.number().numberBetween(1, 10));
  }

  @Bean
  public ItemTag itemTag(final Item item, final Tag tag) {
    return new ItemTag(item, tag);
  }
}
