package org.acetools.repository;

import org.acetools.entity.GearSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GearSetRepository extends JpaRepository<GearSet, Integer> {
    List<GearSet> findAll();
}
