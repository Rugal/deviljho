package ga.rugal.demo.core.dao;

import ga.rugal.demo.core.entity.Tag;

import org.springframework.data.repository.CrudRepository;

public interface TagDao extends CrudRepository<Tag, Integer> {
}
