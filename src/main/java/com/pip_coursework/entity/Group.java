package com.pip_coursework.entity;

import com.pip_coursework.multipleKeys.GroupsKey;

import javax.persistence.*;

@Entity
@IdClass(GroupsKey.class)
@Table(name = "Groups")
public class Group {
    @Id
    @ManyToOne
    @JoinColumn(name = "game_id", referencedColumnName = "id")
    private Game game;

    @Id
    @ManyToOne
    @JoinColumn(name = "character_id", referencedColumnName = "id")
    private Character character;

    protected Group(){
    }

    public Group(Game game, Character character){
        this.game = game;
        this.character = character;
    }
}
