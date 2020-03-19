package ga.rugal.demo.core.dao;

import ga.rugal.demo.core.entity.Item;

import org.springframework.data.repository.CrudRepository;

public interface ItemDao extends CrudRepository<Item, Integer> {
}
