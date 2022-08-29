package org.acetools.controller;

import io.swagger.annotations.Api;
import org.acetools.entity.Battle;
import org.acetools.exception.BattleAlreadyExistsException;
import org.acetools.exception.BattleNotFoundException;
import org.acetools.repository.BattleRepository;
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

@Api(value = "/battle")
@RestController
public class BattleController {

    static Logger logger = LoggerFactory.getLogger(BattleController.class);

    private final BattleRepository battleRepository;

    @Autowired
    public BattleController(BattleRepository battleRepository) {
        this.battleRepository = battleRepository;
    }

    @GetMapping("/battle")
    public CollectionModel<EntityModel<Battle>> all() {
        logger.debug("BattleController findAll - GET request for findAll.");
        List<EntityModel<Battle>> battle = battleRepository.findAll().stream()
                .map(Utils::getBattleEntityModel)
                .collect(Collectors.toList());

        return CollectionModel.of(battle, linkTo(methodOn(BattleController.class).all()).withSelfRel());
    }

    @GetMapping("/battle/{id}")
    public EntityModel<Battle> one(@PathVariable int id) {
        logger.debug("BattleController findAll - GET request for findById.");
        Battle battle = battleRepository.findById(id).orElseThrow(() -> new BattleNotFoundException(id));

        return Utils.getBattleEntityModel(battle);
    }

    @PutMapping("/battle/")
    public Battle newBattle(@RequestBody Battle newBattle) {
        if (logger.isDebugEnabled()) {
            logger.debug("BattleController newBattle - PUT request for potentially new battle: " + newBattle.toString());
        }
        if (battleRepository.existsById(newBattle.getId())) {
            throw new BattleAlreadyExistsException(newBattle.getId());
        } else {
            return battleRepository.save(newBattle);
        }
    }
}
