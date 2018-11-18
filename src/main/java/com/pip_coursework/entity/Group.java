package com.pip_coursework.entity;

import com.pip_coursework.multipleKeys.groupsKey;

import javax.persistence.*;

@Entity
@IdClass(groupsKey.class)
@Table(name = "Groups")
public class Group {

    @ManyToOne
    private Game game;

    @Id
    @Column(name = "Game_Id", insertable = false, updatable = false)
    private long gameId;

    @ManyToOne
    private Character character;

    @Id
    @Column(name = "Character_Id", insertable = false, updatable = false)
    private long characterId;

    protected Group(){
    }

    public Group(long gameId, long characterId, Game game, Character character){
        this.gameId = gameId;
        this.game = game;
        this.characterId = characterId;
        this.character = character;
    }

    @Override
    public String toString() {
        return  String.format("Game - '%s', character - '%s'", game.toString(), character.toString());
    }
}