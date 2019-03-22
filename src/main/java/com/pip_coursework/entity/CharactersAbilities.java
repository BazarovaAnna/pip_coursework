package com.pip_coursework.entity;

import com.pip_coursework.multipleKeys.CharactersAbilitiesKey;

import javax.persistence.*;
import java.util.Date;

@Entity
@IdClass(CharactersAbilitiesKey.class)
@Table(name = "CharactersAbilities")
public class CharactersAbilities {
    @Id
    @ManyToOne
    @JoinColumn(name = "ability_id", referencedColumnName = "id")
    private Ability ability;

    public Ability getAbility() {
        return ability;
    }

    @Id
    @ManyToOne
    @JoinColumn(name = "character_id", referencedColumnName = "id")
    private Character character;

    public Character getCharacter() {
        return character;
    }

    @Basic(optional = false)
    @Column(name = "Time_Learning", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeLearning;

    public Date getTimeLearning() {
        return timeLearning;
    }

    protected CharactersAbilities(){}

    public CharactersAbilities(Ability ability, Character character, Date timeLearning){
        this.ability = ability;
        this.character = character;
        this.timeLearning = timeLearning;
    }

    @Override
    public String toString() {
        return  String.format("Персонаж - %s, абилка - %s", character.toString(), ability.toString());
    }
}


