package org.acetools.controller;

import io.swagger.annotations.Api;
import org.acetools.exception.RarityNotFoundException;
import org.acetools.entity.Rarity;
import org.acetools.repository.RarityRepository;
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

@Api(value = "/rarity")
@RestController
public class RarityController {

    static Logger logger = LoggerFactory.getLogger(RarityController.class);

    private final RarityRepository rarityRepository;

    @Autowired
    public RarityController(RarityRepository rarityRepository) {
        this.rarityRepository = rarityRepository;
    }

    @GetMapping("/rarity")
    public CollectionModel<EntityModel<Rarity>> all() {
        logger.debug("RarityController findAll - GET request for findAll.");
        List<EntityModel<Rarity>> rarity = rarityRepository.findAll().stream()
                .map(Utils::getRarityEntityModel)
                .collect(Collectors.toList());

        return CollectionModel.of(rarity, linkTo(methodOn(RarityController.class).all()).withSelfRel());
    }

    @GetMapping("/rarity/{id}")
    public EntityModel<Rarity> one(@PathVariable int id) {
        logger.debug("RarityController findAll - GET request for findById.");
        Rarity rarity = rarityRepository.findById(id).orElseThrow(() -> new RarityNotFoundException(id));

        return Utils.getRarityEntityModel(rarity);
    }
}
