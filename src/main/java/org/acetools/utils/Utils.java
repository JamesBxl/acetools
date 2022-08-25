package org.acetools.utils;

import org.acetools.controllers.ElementController;
import org.acetools.controllers.FactionsController;
import org.acetools.controllers.HeroesController;
import org.acetools.controllers.RarityController;
import org.acetools.models.Element;
import org.acetools.models.Faction;
import org.acetools.models.Hero;
import org.acetools.models.Rarity;
import org.springframework.hateoas.EntityModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public final class Utils {
    public static EntityModel<Hero> getHeroEntityModel(Hero hero) {
        return EntityModel.of(hero,
                linkTo(methodOn(HeroesController.class).one(hero.getId())).withSelfRel(),
                linkTo(methodOn(HeroesController.class).byFaction(hero.getFaction())).withRel("faction"),
                linkTo(methodOn(HeroesController.class).byElement(hero.getElement())).withRel("element"),
                linkTo(methodOn(HeroesController.class).byRarity(hero.getRarity())).withRel("rarity"),
                linkTo(methodOn(HeroesController.class).all()).withRel("heroes"));
    }
    public static EntityModel<Faction> getFactionEntityModel(Faction faction) {
        return EntityModel.of(faction,
                linkTo(methodOn(FactionsController.class).one(faction.getId())).withSelfRel(),
                linkTo(methodOn(FactionsController.class).all()).withRel("factions"));
    }
    public static EntityModel<Rarity> getRarityEntityModel(Rarity rarity) {
        return EntityModel.of(rarity,
                linkTo(methodOn(RarityController.class).one(rarity.getId())).withSelfRel(),
                linkTo(methodOn(RarityController.class).all()).withRel("rarity"));
    }
    public static EntityModel<Element> getElementEntityModel(Element element) {
        return EntityModel.of(element,
                linkTo(methodOn(ElementController.class).one(element.getId())).withSelfRel(),
                linkTo(methodOn(ElementController.class).all()).withRel("element"));
    }
}
