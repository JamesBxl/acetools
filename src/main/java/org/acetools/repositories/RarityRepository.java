package org.acetools.repositories;

import org.acetools.models.Rarity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RarityRepository extends JpaRepository<Rarity, Integer> {
    List<Rarity> findAll();
}
