package ga.rugal.fridge.core.dao;

import ga.rugal.fridge.core.entity.History;

import org.springframework.data.repository.CrudRepository;

public interface HistoryDao extends CrudRepository<History, Integer> {
}
