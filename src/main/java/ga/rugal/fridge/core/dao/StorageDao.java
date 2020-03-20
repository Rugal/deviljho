package ga.rugal.fridge.core.dao;

import ga.rugal.fridge.core.entity.Storage;

import org.springframework.data.repository.CrudRepository;

public interface StorageDao extends CrudRepository<Storage, Integer> {
}
