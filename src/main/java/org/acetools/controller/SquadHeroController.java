package org.acetools.controller;

import io.swagger.annotations.Api;
import org.acetools.entity.SquadHero;
import org.acetools.exception.SquadHeroAlreadyExistsException;
import org.acetools.exception.SquadHeroNotFoundException;
import org.acetools.repository.SquadHeroRepository;
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

@Api(value = "/squadhero")
@RestController
public class SquadHeroController {

    static Logger logger = LoggerFactory.getLogger(SquadHeroController.class);

    private final SquadHeroRepository squadHeroRepository;

    @Autowired
    public SquadHeroController(SquadHeroRepository squadHeroRepository) {
        this.squadHeroRepository = squadHeroRepository;
    }

    @GetMapping("/squadhero")
    public CollectionModel<EntityModel<SquadHero>> all() {
        logger.debug("SquadController findAll - GET request for findAll.");
        List<EntityModel<SquadHero>> squadHero = squadHeroRepository.findAll().stream()
                .map(Utils::getSquadHeroEntityModel)
                .collect(Collectors.toList());

        return CollectionModel.of(squadHero, linkTo(methodOn(SquadHeroController.class).all()).withSelfRel());
    }

    @GetMapping("/squadhero/{id}")
    public EntityModel<SquadHero> one(@PathVariable int id) {
        logger.debug("SquadController findAll - GET request for findById.");
        SquadHero squadHero = squadHeroRepository.findById(id).orElseThrow(() -> new SquadHeroNotFoundException(id));

        return Utils.getSquadHeroEntityModel(squadHero);
    }

    @PutMapping("/squadhero/")
    public SquadHero newSquad(@RequestBody SquadHero newSquadHero) {
        if (logger.isDebugEnabled()) {
            logger.debug("SquadController newSquad - POST request for potentially new squad: " + newSquadHero.toString());
        }
        if (squadHeroRepository.existsById(newSquadHero.getId())) {
            throw new SquadHeroAlreadyExistsException(newSquadHero.getId());
        } else {
            return squadHeroRepository.save(newSquadHero);
        }
    }
}
