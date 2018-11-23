package com.pip_coursework.entity;

import com.pip_coursework.multipleKeys.CharactersAbilitiesKey;

import javax.persistence.*;
import java.util.Date;

@Entity
@IdClass(CharactersAbilitiesKey.class)
@Table(name = "CharactersAbilities")
public class CharactersAbilities {
    @ManyToOne
    private Ability ability;

    @Id
    @Column(name = "Ability_Id", insertable = false, updatable = false)
    private long abilityId;

    @ManyToOne
    Character character;

    @Id
    @Column(name = "Character_Id", insertable = false, updatable = false)
    private long characterId;

    @Id
    @Basic(optional = false)
    @Column(name = "Time_Learning")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeLearning;

    protected CharactersAbilities(){}

    public CharactersAbilities(Ability ability, Character character, Date timeLearning){
        this.abilityId = ability.getId();
        this.characterId = character.getId();
        this.ability = ability;
        this.character = character;
        this.timeLearning = timeLearning;
    }
}


