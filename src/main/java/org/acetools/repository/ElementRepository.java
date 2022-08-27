package org.acetools.repository;

import org.acetools.entity.Element;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ElementRepository extends JpaRepository<Element, Integer> {
    List<Element> findAll();
}
