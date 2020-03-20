package ga.rugal.fridge.core.dao;

import ga.rugal.fridge.core.entity.Tag;

import org.springframework.data.repository.CrudRepository;

public interface TagDao extends CrudRepository<Tag, Integer> {
}
