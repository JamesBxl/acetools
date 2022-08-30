package org.acetools.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Squad contains up to four SquadHeroes.
 */
@Entity
public class Squad {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    public final static int MAX_HEROES = 4;
    public final static int MAX_SPELLS = 2;

    private String name;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<SquadHero> heroes;
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Spell> spells;

    public Squad() {
        // Default constructor
    }

    public Squad(int id, Set<SquadHero> heroes) {
        this.id = id;
        this.heroes = heroes;
    }

    public Squad(int id, String name, Set<SquadHero> heroes, Set<Spell> spells) {
        this.id = id;
        this.name = name;
        this.heroes = heroes;
        this.spells = spells;
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

    public Set<SquadHero> getHeroes() {
        return heroes;
    }

    public void setHeroes(Set<SquadHero> heroes) {
        if (heroes.size() <= MAX_HEROES) {
            this.heroes = heroes;
        }
    }

    public Set<Spell> getSpells() {
        return spells;
    }

    public void setSpells(Set<Spell> spells) {
        if (spells.size() <= MAX_SPELLS) {
            this.spells = spells;
        }
    }

    /**
     * Adds a SquadHero to this squad, if a hero with the same ID isn't already in the squad.
     * @param heroToAdd The SquadHero to add.
     * @return true if the SquadHero was added, false if not.
     */
    public boolean addSquadHero(SquadHero heroToAdd) {
        if (null == this.heroes) {
            heroes = new LinkedHashSet<>();
        }
        if (heroes.size() <= MAX_HEROES) {
            for (SquadHero squadHero : heroes) {
                if (squadHero.getHero().getId() == heroToAdd.getHero().getId()) {
                    return false;
                }
            }
            heroes.add(heroToAdd);
            return true;
        }
        return false;
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
