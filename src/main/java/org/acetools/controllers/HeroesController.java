package org.acetools.controllers;

import io.swagger.annotations.Api;
import org.acetools.exceptions.HeroAlreadyExistsException;
import org.acetools.exceptions.HeroNotFoundException;
import org.acetools.models.Faction;
import org.acetools.models.Hero;
import org.acetools.repositories.HeroRepository;
import org.acetools.utils.HeroesUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Api(value = "/heroes")
@RestController
public class HeroesController {

    static Logger logger = LoggerFactory.getLogger(HeroesController.class);

    private final HeroRepository heroRepository;

    @Autowired
    public HeroesController(HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
    }

    @GetMapping("/heroes")
    public CollectionModel<EntityModel<Hero>> all() {
        logger.debug("HeroesController findAll - GET request for findAll.");
        List<EntityModel<Hero>> heroes = heroRepository.findAll().stream()
                .map(HeroesUtils::getHeroEntityModel)
                .collect(Collectors.toList());

        return CollectionModel.of(heroes, linkTo(methodOn(HeroesController.class).all()).withSelfRel());
    }

    @GetMapping("/heroes/element/{element}")
    public CollectionModel<EntityModel<Hero>> byElement(@PathVariable Hero.HeroElement element) {
        logger.debug("HeroesController findAll - GET request for findByElement.");
        List<EntityModel<Hero>> heroes = heroRepository.findAllByElement(element).stream()
                .map(HeroesUtils::getHeroEntityModel)
                .collect(Collectors.toList());

        return CollectionModel.of(heroes, linkTo(methodOn(HeroesController.class).byElement(element)).withSelfRel());
    }

    @GetMapping("/heroes/rarity/{rarity}")
    public CollectionModel<EntityModel<Hero>> byRarity(@PathVariable Hero.HeroRarity rarity) {
        logger.debug("HeroesController findAll - GET request for findByRarity.");
        List<EntityModel<Hero>> heroes = heroRepository.findAllByRarity(rarity).stream()
                .map(HeroesUtils::getHeroEntityModel)
                .collect(Collectors.toList());

        return CollectionModel.of(heroes, linkTo(methodOn(HeroesController.class).byRarity(rarity)).withSelfRel());
    }

    @GetMapping("/heroes/faction/{faction}")
    public CollectionModel<EntityModel<Hero>> byFaction(@PathVariable Faction faction) {
        logger.debug("HeroesController findAll - GET request for findByFaction.");
        List<EntityModel<Hero>> heroes = heroRepository.findAllByFaction(faction).stream()
                .map(HeroesUtils::getHeroEntityModel)
                .collect(Collectors.toList());

        return CollectionModel.of(heroes, linkTo(methodOn(HeroesController.class).byFaction(faction)).withSelfRel());
    }

    @GetMapping("/heroes/{id}")
    public EntityModel<Hero> one(@PathVariable String id) {
        logger.debug("HeroesController findAll - GET request for findById.");
        Hero hero = heroRepository.findById(id).orElseThrow(() -> new HeroNotFoundException(id));

        return HeroesUtils.getHeroEntityModel(hero);
    }


    @PostMapping("/heroes/")
    public Hero newHero(@RequestBody Hero newHero) {
        if (logger.isDebugEnabled()) {
            logger.debug("HeroesController newHero - POST request for potentially new hero: " + newHero.toString());
        }
        if (heroRepository.existsById(newHero.getId())) {
            throw new HeroAlreadyExistsException(newHero.getId());
        } else {
            return heroRepository.save(newHero);
        }
    }

    @DeleteMapping("/heroes/{id}")
    public void deleteHero(@PathVariable String id) {
        if (logger.isDebugEnabled()) {
            logger.debug("HeroesController deleteHero - DELETE request for hero with id: " + id);
        }
        if (heroRepository.existsById(id)) {
            if (logger.isDebugEnabled()) {
                logger.debug("HeroesController deleteHero - Found hero with id: " + id + ". Proceeding to delete.");
            }
            heroRepository.deleteById(id);
            if (logger.isDebugEnabled()) {
                logger.debug("HeroesController deleteHero - Hero with id: " + id + " deleted.");
            }
        } else {
            throw new HeroNotFoundException(id);
        }
    }
}
