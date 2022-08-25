package org.acetools.controllers;

import io.swagger.annotations.Api;
import org.acetools.exceptions.FactionNotFoundException;
import org.acetools.models.Faction;
import org.acetools.repositories.FactionRepository;
import org.acetools.utils.FactionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Api(value = "/factions")
@RestController
public class FactionsController {

    static Logger logger = LoggerFactory.getLogger(FactionsController.class);

    private final FactionRepository factionRepository;

    @Autowired
    public FactionsController(FactionRepository factionRepository) {
        this.factionRepository = factionRepository;
    }

    @GetMapping("/factions")
    public CollectionModel<EntityModel<Faction>> all() {
        logger.debug("FactionsController findAll - GET request for findAll.");
        List<EntityModel<Faction>> factions = factionRepository.findAll().stream()
                .map(FactionUtils::getFactionEntityModel)
                .collect(Collectors.toList());

        return CollectionModel.of(factions, linkTo(methodOn(FactionsController.class).all()).withSelfRel());
    }

    @GetMapping("/factions/{id}")
    public EntityModel<Faction> one(@PathVariable String id) {
        logger.debug("FactionsController findAll - GET request for findById.");
        Faction faction = factionRepository.findById(id).orElseThrow(() -> new FactionNotFoundException(id));

        return FactionUtils.getFactionEntityModel(faction);
    }
}
