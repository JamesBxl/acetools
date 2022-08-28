package org.acetools.test;

import org.acetools.entity.Hero;
import org.acetools.entity.Squad;
import org.acetools.entity.SquadHero;
import org.acetools.repository.*;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        Session session;
        if (entityManager == null || (session = entityManager.unwrap(Session.class)) == null) {
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

        List<SquadHero> testSquadHeroes = new LinkedList<>();
        testSquadHeroes.add(squadHeroA);
        testSquadHeroes.add(squadHeroB);
        testSquadHeroes.add(squadHeroC);
        testSquadHeroes.add(squadHeroD);

        Squad testSquad = new Squad();

        for (SquadHero testSquadHero : testSquadHeroes) {
            session.saveOrUpdate(testSquadHero);

            Optional<SquadHero> foundSquadHero = squadHeroRepository.findById(testSquadHero.getId());
            assertThat(foundSquadHero.isPresent());

            assertTrue(testSquad.addSquadHero(foundSquadHero.get()));
        }
        testSquad.setName("Test squad");
        squadRepository.save(testSquad);
        assertTrue(squadRepository.existsById(testSquad.getId()));

        // Clean up
        for (SquadHero testSquadHero : testSquadHeroes) { session.delete(testSquadHero); }
        session.delete(testSquad);

    }
}
