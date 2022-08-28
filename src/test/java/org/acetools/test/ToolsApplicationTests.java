package org.acetools.test;

import org.acetools.entity.Hero;
import org.acetools.entity.SquadHero;
import org.acetools.repository.*;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class ToolsApplicationTests {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired private ElementRepository elementRepository;
    @Autowired private FactionRepository factionRepository;
    @Autowired private GearSetRepository gearSetRepository;
    @Autowired private HeroRepository heroRepository;
    @Autowired private RarityRepository rarityRepository;
    @Autowired private SpellRepository spellRepository;
    @Autowired private SquadHeroRepository squadHeroRepository;
    @Autowired private SquadRepository squadRepository;

    @Test
    void injectedComponentsAreNotNull() {
        assertThat(elementRepository).isNotNull();
        assertThat(factionRepository).isNotNull();
        assertThat(gearSetRepository).isNotNull();
        assertThat(heroRepository).isNotNull();
        assertThat(rarityRepository).isNotNull();
        assertThat(spellRepository).isNotNull();
        assertThat(squadHeroRepository).isNotNull();
        assertThat(squadRepository).isNotNull();
    }

    @Test
    void createSquad() throws Exception {
        Session session = null;
        if (entityManager == null
                || (session = entityManager.unwrap(Session.class)) == null) {

            throw new NullPointerException();
        }
        final Hero heroA = heroRepository.getReferenceById(40);
        final Hero heroB = heroRepository.getReferenceById(58);
        final Hero heroC = heroRepository.getReferenceById(126);
        final Hero heroD = heroRepository.getReferenceById(43);
        final SquadHero squadHeroA = new SquadHero(heroA, null, 60, 6, 6);
        final SquadHero squadHeroB = new SquadHero(heroB, null, 60, 6, 6);
        final SquadHero squadHeroC = new SquadHero(heroC, null, 60, 6, 6);
        final SquadHero squadHeroD = new SquadHero(heroD, null, 60, 6, 6);

        session.saveOrUpdate(squadHeroA);
        session.saveOrUpdate(squadHeroB);
        session.saveOrUpdate(squadHeroC);
        session.saveOrUpdate(squadHeroD);

        List<SquadHero> foundSquadHeroA = squadHeroRepository.findByHeroId(heroA.getId());
        assertThat(foundSquadHeroA.size()).isGreaterThan(0);

        List<SquadHero> foundSquadHeroB = squadHeroRepository.findByHeroId(heroB.getId());
        assertThat(foundSquadHeroB.size()).isGreaterThan(0);

        List<SquadHero> foundSquadHeroC = squadHeroRepository.findByHeroId(heroC.getId());
        assertThat(foundSquadHeroC.size()).isGreaterThan(0);

        List<SquadHero> foundSquadHeroD = squadHeroRepository.findByHeroId(heroD.getId());
        assertThat(foundSquadHeroD.size()).isGreaterThan(0);

    }
}
