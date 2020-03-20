package ga.rugal.fridge.core.dao;

import ga.rugal.fridge.core.entity.Item;

import org.springframework.data.repository.CrudRepository;

public interface ItemDao extends CrudRepository<Item, Integer> {
}
