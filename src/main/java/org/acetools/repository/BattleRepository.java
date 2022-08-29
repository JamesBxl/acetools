package org.acetools.repository;

import org.acetools.entity.Battle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BattleRepository extends JpaRepository<Battle, Integer> {
    List<Battle> findAll();
}
