package com.pip_coursework.entity;

import com.pip_coursework.multipleKeys.InventoryKey;

import javax.persistence.*;
import java.util.Date;

@Entity
@IdClass(InventoryKey.class)
@Table(name = "Inventory")
public class Inventory {
    @ManyToOne
    private Item item;

    @Id
    @Column(name = "Item_Id", insertable = false, updatable = false)
    private long itemId;

    @ManyToOne
    Character character;

    @Id
    @Column(name = "Character_Id", insertable = false, updatable = false)
    private long characterId;

    @Id
    @Basic(optional = false)
    @Column(name = "Time_Getting")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeGetting;

    @Basic(optional = false)
    @Column(name = "Time_Selling", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeSelling;

    public void SellTimeSelling(){
        this.timeSelling = new Date();
    }

    protected Inventory(){}

    public Inventory(Item item, Character character){
        this.itemId = item.getId();
        this.characterId = character.getId();
        this.item = item;
        this.character = character;
        this.timeGetting = new Date();
    }
}

