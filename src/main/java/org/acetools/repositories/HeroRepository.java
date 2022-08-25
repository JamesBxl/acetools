package org.acetools.repositories;

import org.acetools.models.Element;
import org.acetools.models.Faction;
import org.acetools.models.Hero;
import org.acetools.models.Rarity;
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
