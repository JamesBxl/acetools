package org.acetools.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class SquadHero {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @OneToOne
    private Hero hero;
    @OneToMany
    private List<GearSet> sets;
    private int level;
    private int ascension;
    private int stars;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public List<GearSet> getSets() {
        return sets;
    }

    public void setSets(List<GearSet> sets) {
        this.sets = sets;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getAscension() {
        return ascension;
    }

    public void setAscension(int ascension) {
        this.ascension = ascension;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }
}
