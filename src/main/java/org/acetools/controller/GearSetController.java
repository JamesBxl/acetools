package org.acetools.controller;

import io.swagger.annotations.Api;
import org.acetools.entity.GearSet;
import org.acetools.exception.GearSetAlreadyExistsException;
import org.acetools.exception.GearSetNotFoundException;
import org.acetools.repository.GearSetRepository;
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

@Api(value = "/gearset")
@RestController
public class GearSetController {

    static Logger logger = LoggerFactory.getLogger(GearSetController.class);

    private final GearSetRepository gearSetRepository;

    @Autowired
    public GearSetController(GearSetRepository gearSetRepository) {
        this.gearSetRepository = gearSetRepository;
    }

    @GetMapping("/gearset")
    public CollectionModel<EntityModel<GearSet>> all() {
        logger.debug("GearSetController findAll - GET request for findAll.");
        List<EntityModel<GearSet>> gearSet = gearSetRepository.findAll().stream()
                .map(Utils::getGearSetEntityModel)
                .collect(Collectors.toList());

        return CollectionModel.of(gearSet, linkTo(methodOn(GearSetController.class).all()).withSelfRel());
    }

    @GetMapping("/gearset/{id}")
    public EntityModel<GearSet> one(@PathVariable int id) {
        logger.debug("GearSetController findAll - GET request for findById.");
        GearSet gearSet = gearSetRepository.findById(id).orElseThrow(() -> new GearSetNotFoundException(id));

        return Utils.getGearSetEntityModel(gearSet);
    }

    @PutMapping("/gearset/")
    public GearSet newGearSet(@RequestBody GearSet newGearSet) {
        if (logger.isDebugEnabled()) {
            logger.debug("GearSetController newGearSet - PUT request for potentially new gear set: " + newGearSet.toString());
        }
        if (gearSetRepository.existsById(newGearSet.getId())) {
            throw new GearSetAlreadyExistsException(newGearSet.getId());
        } else {
            return gearSetRepository.save(newGearSet);
        }
    }
}
