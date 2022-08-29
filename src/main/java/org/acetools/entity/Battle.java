package org.acetools.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Battle consists of a defending squad ("defenseSquad") and an attacking squad ("counterSquad").
 * Name is optional, as is description. Description can be used to provide details on the counter approach.
 */
@Entity
public class Battle {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String name;
    private String description;

    @OneToOne(fetch = FetchType.EAGER)
    private Squad defenseSquad;
    @OneToOne(fetch = FetchType.EAGER)
    private Squad counterSquad;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Squad getDefenseSquad() {
        return defenseSquad;
    }

    public void setDefenseSquad(Squad defenseSquad) {
        this.defenseSquad = defenseSquad;
    }

    public Squad getCounterSquad() {
        return counterSquad;
    }

    public void setCounterSquad(Squad counterSquad) {
        this.counterSquad = counterSquad;
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
