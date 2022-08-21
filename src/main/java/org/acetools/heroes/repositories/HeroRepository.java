package org.acetools.heroes.repositories;

import org.acetools.heroes.models.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeroRepository extends JpaRepository<Hero, String> {
    List<Hero> findAllByElement(Hero.HeroElement element);
    List<Hero> findAllByRarity(Hero.HeroRarity rarity);
    List<Hero> findAllByFaction(Hero.HeroFaction faction);
    List<Hero> findAll();
}
