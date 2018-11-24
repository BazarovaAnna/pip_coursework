package com.pip_coursework.entity;

import com.pip_coursework.multipleKeys.groupsKey;

import javax.persistence.*;

@Entity
@IdClass(groupsKey.class)
@Table(name = "Groups")
public class Group {
    @Id
    @ManyToOne
    private Game game;

    @Id
    @ManyToOne
    private Character character;

    protected Group(){
    }

    public Group(Game game, Character character){
        this.game = game;
        this.character = character;
    }

    @Override
    public String toString() {
        return  String.format("Игра: %s, персонаж: %s", game.toString(), character.toString());
    }
}
