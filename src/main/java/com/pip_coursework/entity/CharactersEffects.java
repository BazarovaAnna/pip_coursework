package com.pip_coursework.entity;

import com.pip_coursework.entity.Effect;
import com.pip_coursework.multipleKeys.CharactersEffectsKey;

import javax.persistence.*;
import java.util.Date;

@Entity
@IdClass(CharactersEffectsKey.class)
@Table(name = "CharactersEffects")
public class CharactersEffects {

    @ManyToOne
    private Effect effect;

    @Id
    @Column(name = "Effect_Id", insertable = false, updatable = false)
    private long effectId;

    @ManyToOne
    Character character;

    @Id
    @Column(name = "Character_Id", insertable = false, updatable = false)
    private long characterId;

    @Basic(optional = false)
    @Column(name = "Time_Removal")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeRemoval;

    @Basic(optional = false)
    @Column(name = "Time_Overlay", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeOverlay;

    protected CharactersEffects(){}

    public CharactersEffects(Effect effect, Character character){
        this.effectId = effect.getId();
        this.characterId = character.getId();
        this.effect = effect;
        this.character = character;
        this.timeOverlay = new Date();
    }
}
