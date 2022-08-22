package org.acetools.heroes.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.swagger.annotations.ApiParam;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Hero {
    @Id
    private String id;
    @ApiParam(required = true, allowableValues = "dark,fire,light,water,wood")
    private HeroElement element;
    @ApiParam(required = true, allowableValues = "common,rare,elite,epic,legendary")
    private HeroRarity rarity;
    @ApiParam(required = true, allowableValues = "crisas,dragonscalemarsh,freecities,holylightempire,lasir,soulplunders,sylvanwoodlands,titanicelands")
    private HeroFaction faction;

    private int level; // 1-60
    private int stars; // 1-6
    private int ascension; // 0-6
    private int statAttack;
    private int statHealth;
    private int statDefense;
    private int statSpeed;
    private int statCritRate;
    private int statCritDamage;
    private int statFocus;
    private int statResistance;
    private int statAgility;
    private int statPrecision;

    public enum HeroElement{dark, fire, light, water, wood};
    public enum HeroRarity{common, rare, elite, epic, legendary};
    public enum HeroFaction{crisas, dragonscalemarsh, freecities, holylightempire, lasir, soulplunders, sylvanwoodlands, titanicelands};

    public Hero() {
        // Default constructor
    }

    public Hero(String id, HeroElement element, HeroRarity rarity, HeroFaction faction, int level, int stars, int ascension) {
        this.id = id;
        this.element = element;
        this.rarity = rarity;
        this.faction = faction;
        this.level = level;
        this.stars = stars;
        this.ascension = ascension;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HeroElement getElement() {
        return element;
    }

    public void setElement(HeroElement element) {
        this.element = element;
    }

    public HeroRarity getRarity() {
        return rarity;
    }

    public void setRarity(HeroRarity rarity) {
        this.rarity = rarity;
    }

    public HeroFaction getFaction() {
        return faction;
    }

    public void setFaction(HeroFaction faction) {
        this.faction = faction;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getAscension() {
        return ascension;
    }

    public void setAscension(int ascension) {
        this.ascension = ascension;
    }

    public int getStatAttack() {
        return statAttack;
    }

    public void setStatAttack(int statAttack) {
        this.statAttack = statAttack;
    }

    public int getStatHealth() {
        return statHealth;
    }

    public void setStatHealth(int statHealth) {
        this.statHealth = statHealth;
    }

    public int getStatDefense() {
        return statDefense;
    }

    public void setStatDefense(int statDefense) {
        this.statDefense = statDefense;
    }

    public int getStatSpeed() {
        return statSpeed;
    }

    public void setStatSpeed(int statSpeed) {
        this.statSpeed = statSpeed;
    }

    public int getStatCritRate() {
        return statCritRate;
    }

    public void setStatCritRate(int statCritRate) {
        this.statCritRate = statCritRate;
    }

    public int getStatCritDamage() {
        return statCritDamage;
    }

    public void setStatCritDamage(int statCritDamage) {
        this.statCritDamage = statCritDamage;
    }

    public int getStatFocus() {
        return statFocus;
    }

    public void setStatFocus(int statFocus) {
        this.statFocus = statFocus;
    }

    public int getStatResistance() {
        return statResistance;
    }

    public void setStatResistance(int statResistance) {
        this.statResistance = statResistance;
    }

    public int getStatAgility() {
        return statAgility;
    }

    public void setStatAgility(int statAgility) {
        this.statAgility = statAgility;
    }

    public int getStatPrecision() {
        return statPrecision;
    }

    public void setStatPrecision(int statPrecision) {
        this.statPrecision = statPrecision;
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
