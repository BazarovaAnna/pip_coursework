package com.pip_coursework.entity;

import com.pip_coursework.multipleKeys.InventoryKey;

import javax.persistence.*;
import java.util.Date;

@Entity
@IdClass(InventoryKey.class) //embedable id
@Table(name = "Inventory")
public class Inventory {
    @Id
    @ManyToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private Item item;

    public Item getItem() {
        return item;
    }

    @Id
    @ManyToOne
    @JoinColumn(name = "character_id", referencedColumnName = "id")
    private Character character;

    public Character getCharacter() {
        return character;
    }

    @Basic(optional = false)
    @Column(name = "Time_Getting", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeGetting;

    public Date getTimeGetting() {
        return timeGetting;
    }

    public void setTimeGetting() { this.timeGetting = new Date(); }

    @Basic(optional = false)
    @Column(name = "Time_Selling", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeSelling;

    public Date getTimeSelling() {
        return timeSelling;
    }

    public void setTimeSelling(){
        this.timeSelling = new Date();
    }

    protected Inventory(){}

    public Inventory(Item item, Character character){
        this.item = item;
        this.character = character;
        this.timeGetting = new Date();
    }

    @Override
    public String toString() {
        return  String.format("Персонаж - %s, предмет - %s", character.toString(), item.toString());
    }
}

