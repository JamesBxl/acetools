package org.acetools.util;

import org.acetools.controller.*;
import org.acetools.entity.*;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public final class Utils {
    public static EntityModel<Hero> getHeroEntityModel(Hero hero) {
        return EntityModel.of(hero,
                linkTo(methodOn(HeroController.class).one(hero.getId())).withSelfRel(),
                linkTo(methodOn(HeroController.class).byFaction(hero.getFaction())).withRel("faction"),
                linkTo(methodOn(HeroController.class).byElement(hero.getElement())).withRel("element"),
                linkTo(methodOn(HeroController.class).byRarity(hero.getRarity())).withRel("rarity"),
                linkTo(methodOn(HeroController.class).all()).withRel("heroes"));
    }
    public static EntityModel<Faction> getFactionEntityModel(Faction faction) {
        return EntityModel.of(faction,
                linkTo(methodOn(FactionController.class).one(faction.getId())).withSelfRel(),
                linkTo(methodOn(FactionController.class).all()).withRel("factions"));
    }
    public static EntityModel<Rarity> getRarityEntityModel(Rarity rarity) {
        return EntityModel.of(rarity,
                linkTo(methodOn(RarityController.class).one(rarity.getId())).withSelfRel(),
                linkTo(methodOn(RarityController.class).all()).withRel("rarity"));
    }
    public static EntityModel<Element> getElementEntityModel(Element element) {
        return EntityModel.of(element,
                linkTo(methodOn(ElementController.class).one(element.getId())).withSelfRel(),
                linkTo(methodOn(ElementController.class).all()).withRel("element"));
    }
    public static EntityModel<Spell> getSpellEntityModel(Spell spell) {
        return EntityModel.of(spell,
                linkTo(methodOn(SpellController.class).one(spell.getId())).withSelfRel(),
                linkTo(methodOn(SpellController.class).all()).withRel("spell"));
    }
    public static EntityModel<Squad> getSquadEntityModel(Squad squad) {
        return EntityModel.of(squad,
                linkTo(methodOn(SquadController.class).one(squad.getId())).withSelfRel(),
                linkTo(methodOn(SquadController.class).all()).withRel("squad"));
    }
    public static EntityModel<GearSet> getGearSetEntityModel(GearSet gearSet) {
        return EntityModel.of(gearSet,
                linkTo(methodOn(GearSetController.class).one(gearSet.getId())).withSelfRel(),
                linkTo(methodOn(GearSetController.class).all()).withRel("gearset"));
    }
    public static EntityModel<SquadHero> getSquadHeroEntityModel(SquadHero squadHero) {
        return EntityModel.of(squadHero,
                linkTo(methodOn(SquadHeroController.class).one(squadHero.getId())).withSelfRel(),
                linkTo(methodOn(SquadHeroController.class).all()).withRel("squadhero"));
    }
    public static EntityModel<Battle> getBattleEntityModel(Battle battle) {
        return EntityModel.of(battle,
                linkTo(methodOn(BattleController.class).one(battle.getId())).withSelfRel(),
                linkTo(methodOn(BattleController.class).all()).withRel("battle"));
    }

    @Value("${trust.store}")
    private Resource trustStore;
    @Value("${trust.store.password}")
    private String trustStorePassword;
    RestTemplate restTemplate() throws Exception {
        SSLContext sslContext = new SSLContextBuilder()
                .loadTrustMaterial(trustStore.getURL(), trustStorePassword.toCharArray())
                .build();
        SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslContext);
        HttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(socketFactory)
                .build();
        HttpComponentsClientHttpRequestFactory factory =
                new HttpComponentsClientHttpRequestFactory(httpClient);
        return new RestTemplate(factory);
    }
}
