package org.acetools.utils;

import org.acetools.controllers.FactionsController;
import org.acetools.models.Faction;
import org.springframework.hateoas.EntityModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public final class FactionUtils {
    public static EntityModel<Faction> getFactionEntityModel(Faction faction) {
        return EntityModel.of(faction,
                linkTo(methodOn(FactionsController.class).one(faction.getId())).withSelfRel(),
                linkTo(methodOn(FactionsController.class).all()).withRel("factions"));
    }
}
