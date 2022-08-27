package org.acetools.repository;

import org.acetools.entity.Element;
import org.acetools.entity.Faction;
import org.acetools.entity.Hero;
import org.acetools.entity.Rarity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Integer> {
    List<Hero> findAllByElement(Element element);
    List<Hero> findAllByRarity(Rarity rarity);
    List<Hero> findAllByFaction(Faction faction);
    List<Hero> findAll();
}
