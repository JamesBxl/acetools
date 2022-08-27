package org.acetools.repository;

import org.acetools.entity.Rarity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RarityRepository extends JpaRepository<Rarity, Integer> {
    List<Rarity> findAll();
}
