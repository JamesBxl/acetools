package org.acetools.controller;

import io.swagger.annotations.Api;
import org.acetools.entity.Element;
import org.acetools.entity.Faction;
import org.acetools.entity.Hero;
import org.acetools.entity.Rarity;
import org.acetools.exception.HeroAlreadyExistsException;
import org.acetools.exception.HeroNotFoundException;
import org.acetools.repository.HeroRepository;
import org.acetools.util.Utils;
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

@Api(value = "/hero")
@RestController
public class HeroController {

    static Logger logger = LoggerFactory.getLogger(HeroController.class);

    private final HeroRepository heroRepository;

    @Autowired
    public HeroController(HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
    }

    @GetMapping("/hero")
    public CollectionModel<EntityModel<Hero>> all() {
        logger.debug("HeroController findAll - GET request for findAll.");
        List<EntityModel<Hero>> heroes = heroRepository.findAll().stream()
                .map(Utils::getHeroEntityModel)
                .collect(Collectors.toList());

        return CollectionModel.of(heroes, linkTo(methodOn(HeroController.class).all()).withSelfRel());
    }

    @GetMapping("/hero/element/{element}")
    public CollectionModel<EntityModel<Hero>> byElement(@PathVariable Element element) {
        logger.debug("HeroController findAll - GET request for findByElement.");
        List<EntityModel<Hero>> heroes = heroRepository.findAllByElement(element).stream()
                .map(Utils::getHeroEntityModel)
                .collect(Collectors.toList());

        return CollectionModel.of(heroes, linkTo(methodOn(HeroController.class).byElement(element)).withSelfRel());
    }

    @GetMapping("/hero/rarity/{rarity}")
    public CollectionModel<EntityModel<Hero>> byRarity(@PathVariable Rarity rarity) {
        logger.debug("HeroController findAll - GET request for findByRarity.");
        List<EntityModel<Hero>> heroes = heroRepository.findAllByRarity(rarity).stream()
                .map(Utils::getHeroEntityModel)
                .collect(Collectors.toList());

        return CollectionModel.of(heroes, linkTo(methodOn(HeroController.class).byRarity(rarity)).withSelfRel());
    }

    @GetMapping("/hero/faction/{faction}")
    public CollectionModel<EntityModel<Hero>> byFaction(@PathVariable Faction faction) {
        logger.debug("HeroController findAll - GET request for findByFaction.");
        List<EntityModel<Hero>> heroes = heroRepository.findAllByFaction(faction).stream()
                .map(Utils::getHeroEntityModel)
                .collect(Collectors.toList());

        return CollectionModel.of(heroes, linkTo(methodOn(HeroController.class).byFaction(faction)).withSelfRel());
    }

    @GetMapping("/hero/{id}")
    public EntityModel<Hero> one(@PathVariable int id) {
        logger.debug("HeroController findAll - GET request for findById.");
        Hero hero = heroRepository.findById(id).orElseThrow(() -> new HeroNotFoundException(id));

        return Utils.getHeroEntityModel(hero);
    }

    @PutMapping("/hero/")
    public Hero newHero(@RequestBody Hero newHero) {
        if (logger.isDebugEnabled()) {
            logger.debug("HeroController newHero - PUT request for potentially new hero: " + newHero.toString());
        }
        if (heroRepository.existsById(newHero.getId())) {
            throw new HeroAlreadyExistsException(newHero.getId());
        } else {
            return heroRepository.save(newHero);
        }
    }

    @PatchMapping("/hero/{id}")
    public Hero updateHero(@RequestBody Hero heroPatch, @PathVariable int id) {
        if (logger.isDebugEnabled()) {
            logger.debug("HeroController newHero - PATCH request for potential hero update: " + heroPatch.toString());
        }
        if (!heroRepository.existsById(id)) {
            throw new HeroNotFoundException(id);
        } else {
            return heroRepository.save(heroPatch);
        }
    }

    @DeleteMapping("/hero/{id}")
    public void deleteHero(@PathVariable int id) {
        if (logger.isDebugEnabled()) {
            logger.debug("HeroController deleteHero - DELETE request for hero with id: " + id);
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
