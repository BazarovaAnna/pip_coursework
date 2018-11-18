package com.pip_coursework.entity;

import javax.persistence.*;

@Entity
@Table(name = "Abilities")
public class Ability {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected  long id;

    @Column(name = "Name", unique = true, nullable = false)
    private String name;

    @Column(name = "Description")
    private String description;

    @Column(name = "Perk_Ability", nullable = false)
    private char perkAbility;

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