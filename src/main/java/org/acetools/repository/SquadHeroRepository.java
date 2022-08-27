package org.acetools.repository;

import org.acetools.entity.SquadHero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SquadHeroRepository extends JpaRepository<SquadHero, Integer> {
    List<SquadHero> findAll();
}
