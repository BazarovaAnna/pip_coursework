package com.pip_coursework.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;

@Entity
@Table(name = "Games")
public class Session {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Game game;

    @Column(name = "Game_Id", insertable = false, updatable = false)
    private long gameId;

    @Column(name = "GMs_Rating", nullable = false)
    private float gmsRating;

    @Basic(optional = false)
    @Column(name = "Date_Start", unique = true, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date_start;

    @Basic(optional = false)
    @Column(name = "Date_End", unique = true, nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date_end;

    private ArrayList<Character> members;
    public void addMemberToSession(Character character) {
        members.add(character);
    }
    public void removeMemberFromSession (Character character) { members.remove(character); }
    public ArrayList<Character> getMembers() {
        return members;
    }

    // Нужен для получения данных из БД
    protected Session(){ }

    // Нужен для добавления данных в БД
    public Session(long gameId, Game game, float gmsRating){
        this.gameId = gameId;
        this.game = game;
        this.gmsRating = gmsRating;
        this.date_start = new Date();
    }

    @Override
    public String toString() {
        return  String.format("%s session from game - '%s', GM's rating is - %s, Start date - %s, End date - @s", id, game.toString(), gmsRating, date_start.toString(), date_end.toString());
    }
}
