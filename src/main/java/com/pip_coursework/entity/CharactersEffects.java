package com.pip_coursework.entity;

import com.pip_coursework.multipleKeys.CharactersEffectsKey;

import javax.persistence.*;
import java.util.Date;

@Entity
@IdClass(CharactersEffectsKey.class)
@Table(name = "CharactersEffects")
public class CharactersEffects {
    @Id
    @ManyToOne
    @JoinColumn(name = "effect_id", referencedColumnName = "id")
    private Effect effect;

    public Effect getEffect() {
        return effect;
    }

    @Id
    @ManyToOne
    @JoinColumn(name = "character_id", referencedColumnName = "id")
    private Character character;

    public Character getCharacter() {
        return character;
    }

    @Basic(optional = false)
    @Column(name = "Time_Overlay", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeOverlay;

    public Date getTimeOverlay() {
        return timeOverlay;
    }


    @Basic(optional = false)
    @Column(name = "Time_Removal", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeRemoval;

    public Date getTimeRemoval() {
        return timeRemoval;
    }

    public void setTimeRemoval(Date timeRemoval) {
        this.timeRemoval = timeRemoval;
    }

    protected CharactersEffects(){}

    public CharactersEffects(Effect effect, Character character){
        this.effect = effect;
        this.character = character;
        this.timeOverlay = new Date();
    }

    @Override
    public String toString() {
        return String.format("Персонаж - %s, эффект - %s", character.toString(), effect.toString());
    }
}
