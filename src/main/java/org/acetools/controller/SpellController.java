package org.acetools.controller;

import io.swagger.annotations.Api;
import org.acetools.entity.Spell;
import org.acetools.exception.SpellNotFoundException;
import org.acetools.repository.SpellRepository;
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

@Api(value = "/spell")
@RestController
public class SpellController {

    static Logger logger = LoggerFactory.getLogger(SpellController.class);

    private final SpellRepository spellRepository;

    @Autowired
    public SpellController(SpellRepository spellRepository) {
        this.spellRepository = spellRepository;
    }

    @GetMapping("/spell")
    public CollectionModel<EntityModel<Spell>> all() {
        logger.debug("SpellController findAll - GET request for findAll.");
        List<EntityModel<Spell>> spell = spellRepository.findAll().stream()
                .map(Utils::getSpellEntityModel)
                .collect(Collectors.toList());

        return CollectionModel.of(spell, linkTo(methodOn(SpellController.class).all()).withSelfRel());
    }

    @GetMapping("/spell/{id}")
    public EntityModel<Spell> one(@PathVariable int id) {
        logger.debug("SpellController findAll - GET request for findById.");
        Spell spell = spellRepository.findById(id).orElseThrow(() -> new SpellNotFoundException(id));

        return Utils.getSpellEntityModel(spell);
    }
}
