package ga.rugal.fridge.core.dao;

import java.util.List;

import ga.rugal.fridge.core.entity.Item;
import ga.rugal.fridge.core.entity.ItemTag;
import ga.rugal.fridge.core.entity.Tag;

import org.springframework.data.repository.CrudRepository;

public interface ItemTagDao extends CrudRepository<ItemTag, Integer> {

  List<ItemTag> findByItem(Item item);

  List<ItemTag> findByTag(Tag tag);
}
