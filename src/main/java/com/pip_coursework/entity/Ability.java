package com.pip_coursework.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Abilities")
public class Ability {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public long getId() {
        return id;
    }

    @Column(name = "Name", unique = true, nullable = false)
    private String name;

    public String getName() {
        return name;
    }

    @Column(name = "Description")
    private String description;

    public String getDescription() {
        return description;
    }

    @Column(name = "Perk_Ability", nullable = false)
    private char perkAbility;

    public char getPerkAbility() {
        return perkAbility;
    }

    @OneToMany(mappedBy = "ability")
    @JsonBackReference
    private List<CharactersAbilities> charactersAbilities;

    public void setCharactersAbilities(List<CharactersAbilities> charactersAbilities) {
        this.charactersAbilities = charactersAbilities;
    }

    public List<CharactersAbilities> getCharactersAbilities() {
        return charactersAbilities;
    }

    public void addCharacterAbility(CharactersAbilities charactersAbilities) {
        this.charactersAbilities.add(charactersAbilities);
    }

    public void removeCharacterAbility(CharactersAbilities charactersAbilities) {
        this.charactersAbilities.remove(charactersAbilities);
    }

    protected Ability() {
    }

    public Ability(String name, String description, char perkAbility) {
        this.name = name;
        this.description = description;
        this.perkAbility = perkAbility;
    }

    @Override
    public String toString() {
        return String.format("{\"id\":\"%s\",\"name\":\"%s\",\"description\":\"%s\",\"p_a\":\"%s\"}", id, name, description, perkAbility);
    }
}