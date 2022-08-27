package org.acetools.controller;

import io.swagger.annotations.Api;
import org.acetools.exception.FactionNotFoundException;
import org.acetools.entity.Faction;
import org.acetools.repository.FactionRepository;
import org.acetools.util.Utils;
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

@Api(value = "/faction")
@RestController
public class FactionController {

    static Logger logger = LoggerFactory.getLogger(FactionController.class);

    private final FactionRepository factionRepository;

    @Autowired
    public FactionController(FactionRepository factionRepository) {
        this.factionRepository = factionRepository;
    }

    @GetMapping("/faction")
    public CollectionModel<EntityModel<Faction>> all() {
        logger.debug("FactionController findAll - GET request for findAll.");
        List<EntityModel<Faction>> factions = factionRepository.findAll().stream()
                .map(Utils::getFactionEntityModel)
                .collect(Collectors.toList());

        return CollectionModel.of(factions, linkTo(methodOn(FactionController.class).all()).withSelfRel());
    }

    @GetMapping("/faction/{id}")
    public EntityModel<Faction> one(@PathVariable int id) {
        logger.debug("FactionController findAll - GET request for findById.");
        Faction faction = factionRepository.findById(id).orElseThrow(() -> new FactionNotFoundException(id));

        return Utils.getFactionEntityModel(faction);
    }
}