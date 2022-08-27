package org.acetools.repository;

import org.acetools.entity.Spell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpellRepository extends JpaRepository<Spell, Integer> {
    List<Spell> findAll();
}
