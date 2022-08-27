package org.acetools.controller;

import io.swagger.annotations.Api;
import org.acetools.exception.ElementNotFoundException;
import org.acetools.entity.Element;
import org.acetools.repository.ElementRepository;
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

@Api(value = "/element")
@RestController
public class ElementController {

    static Logger logger = LoggerFactory.getLogger(ElementController.class);

    private final ElementRepository elementRepository;

    @Autowired
    public ElementController(ElementRepository elementRepository) {
        this.elementRepository = elementRepository;
    }

    @GetMapping("/element")
    public CollectionModel<EntityModel<Element>> all() {
        logger.debug("ElementController findAll - GET request for findAll.");
        List<EntityModel<Element>> element = elementRepository.findAll().stream()
                .map(Utils::getElementEntityModel)
                .collect(Collectors.toList());

        return CollectionModel.of(element, linkTo(methodOn(ElementController.class).all()).withSelfRel());
    }

    @GetMapping("/element/{id}")
    public EntityModel<Element> one(@PathVariable int id) {
        logger.debug("ElementController findAll - GET request for findById.");
        Element element = elementRepository.findById(id).orElseThrow(() -> new ElementNotFoundException(id));

        return Utils.getElementEntityModel(element);
    }
}
