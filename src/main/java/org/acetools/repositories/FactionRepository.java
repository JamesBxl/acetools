package org.acetools.repositories;

import org.acetools.models.Faction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FactionRepository extends JpaRepository<Faction, String> {
    List<Faction> findAll();
}
