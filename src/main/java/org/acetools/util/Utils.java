package org.acetools.util;

import org.acetools.controller.*;
import org.acetools.entity.*;
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
    public static EntityModel<Spell> getSpellEntityModel(Spell spell) {
        return EntityModel.of(spell,
                linkTo(methodOn(SpellController.class).one(spell.getId())).withSelfRel(),
                linkTo(methodOn(SpellController.class).all()).withRel("spell"));
    }
    public static EntityModel<Squad> getSquadEntityModel(Squad squad) {
        return EntityModel.of(squad,
                linkTo(methodOn(SquadController.class).one(squad.getId())).withSelfRel(),
                linkTo(methodOn(SquadController.class).all()).withRel("squad"));
    }
}
