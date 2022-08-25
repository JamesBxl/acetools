package org.acetools.repositories;

import org.acetools.models.Faction;
import org.acetools.models.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeroRepository extends JpaRepository<Hero, String> {
    List<Hero> findAllByElement(Hero.HeroElement element);
    List<Hero> findAllByRarity(Hero.HeroRarity rarity);
    List<Hero> findAllByFaction(Faction faction);
    List<Hero> findAll();
}
