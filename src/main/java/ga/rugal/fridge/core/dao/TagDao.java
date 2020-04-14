package ga.rugal.fridge.core.dao;

import java.util.Optional;

import ga.rugal.fridge.core.entity.Tag;

import org.springframework.data.repository.CrudRepository;

public interface TagDao extends CrudRepository<Tag, Integer> {

  Optional<Tag> findByName(String name);

  boolean existsByName(String name);
}
