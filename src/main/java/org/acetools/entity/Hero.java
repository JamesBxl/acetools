package org.acetools.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.swagger.annotations.ApiParam;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Hero {
    @Id
    private int id;
    private String name;
    @ApiParam(required = true)
    @OneToOne
    @RestResource(path = "elementDetails", rel="element")
    private Element element;
    @ApiParam(required = true)
    @OneToOne
    @RestResource(path = "rarityDetails", rel="rarity")
    private Rarity rarity;
    @ApiParam(required = true)
    @OneToOne
    @RestResource(path = "factionDetails", rel="faction")
    private Faction faction;

    public Hero() {
        // Default constructor
    }

    public Hero(int id, Element element, Rarity rarity, Faction faction) {
        this.id = id;
        this.element = element;
        this.rarity = rarity;
        this.faction = faction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    public Faction getFaction() {
        return faction;
    }

    public void setFaction(Faction faction) {
        this.faction = faction;
    }

    @Override
    public String toString() {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            return ow.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
