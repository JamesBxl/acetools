package org.acetools.heroes.utils;

import org.acetools.heroes.controllers.HeroesController;
import org.acetools.heroes.models.Hero;
import org.springframework.hateoas.EntityModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public final class HeroesUtils {
    public static EntityModel<Hero> getHeroEntityModel(Hero hero) {
        return EntityModel.of(hero,
                linkTo(methodOn(HeroesController.class).one(hero.getId())).withSelfRel(),
                linkTo(methodOn(HeroesController.class).byElement(hero.getElement())).withRel("element"),
                linkTo(methodOn(HeroesController.class).byRarity(hero.getRarity())).withRel("rarity"),
                linkTo(methodOn(HeroesController.class).all()).withRel("heroes"));
    }
}
