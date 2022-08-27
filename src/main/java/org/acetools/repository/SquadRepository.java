package org.acetools.repository;

import org.acetools.entity.Squad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SquadRepository extends JpaRepository<Squad, Integer> {
    List<Squad> findAll();
}
