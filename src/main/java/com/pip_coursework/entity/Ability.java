package com.pip_coursework.entity;

import javax.persistence.*;

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

    protected Ability(){ }

    public Ability(String name, String description, char perkAbility){
        this.name = name;
        this.description= description;
        this.perkAbility = perkAbility;
    }

    @Override
    public String toString() {
        return  String.format("%s название %s - '%s'",id,perkAbility, name);
    }
}
