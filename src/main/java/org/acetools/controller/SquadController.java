package org.acetools.controller;

import io.swagger.annotations.Api;
import org.acetools.entity.Squad;
import org.acetools.exception.HeroAlreadyExistsException;
import org.acetools.exception.SquadNotFoundException;
import org.acetools.repository.SquadRepository;
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

@Api(value = "/squad")
@RestController
public class SquadController {

    static Logger logger = LoggerFactory.getLogger(SquadController.class);

    private final SquadRepository squadRepository;

    @Autowired
    public SquadController(SquadRepository squadRepository) {
        this.squadRepository = squadRepository;
    }

    @GetMapping("/squad")
    public CollectionModel<EntityModel<Squad>> all() {
        logger.debug("SquadController findAll - GET request for findAll.");
        List<EntityModel<Squad>> squad = squadRepository.findAll().stream()
                .map(Utils::getSquadEntityModel)
                .collect(Collectors.toList());

        return CollectionModel.of(squad, linkTo(methodOn(SquadController.class).all()).withSelfRel());
    }

    @GetMapping("/squad/{id}")
    public EntityModel<Squad> one(@PathVariable int id) {
        logger.debug("SquadController findAll - GET request for findById.");
        Squad squad = squadRepository.findById(id).orElseThrow(() -> new SquadNotFoundException(id));

        return Utils.getSquadEntityModel(squad);
    }

    @PostMapping("/squad/")
    public Squad newSquad(@RequestBody Squad newSquad) {
        if (logger.isDebugEnabled()) {
            logger.debug("SquadController newSquad - POST request for potentially new squad: " + newSquad.toString());
        }
        if (squadRepository.existsById(newSquad.getId())) {
            throw new HeroAlreadyExistsException(newSquad.getId());
        } else {
            return squadRepository.save(newSquad);
        }
    }
}
