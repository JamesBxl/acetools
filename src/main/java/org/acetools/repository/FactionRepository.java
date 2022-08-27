package org.acetools.repository;

import org.acetools.entity.Faction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FactionRepository extends JpaRepository<Faction, Integer> {
    List<Faction> findAll();
}
