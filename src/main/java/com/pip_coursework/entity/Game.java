package com.pip_coursework.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Games")
public class Game {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Genre genre;

    @Column(name = "Genre_Id", insertable = false, updatable = false)
    private long genreId;

    @ManyToOne
    private Rules rules;

    @Column(name = "Rules_Id", insertable = false, updatable = false)
    private long rulesId;

    @ManyToOne
    private User gm;

    @Column(name = "GM_Id", insertable = false, updatable = false)
    private long gmId;

    @Column(name = "State", unique = true, nullable = false)
    private String state;

    @Basic(optional = false)
    @Column(name = "Time_Creating", unique = true, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date time_creating;

    @Basic(optional = false)
    @Column(name = "Time_Deleting", unique = true, nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date time_deleting;

    // Нужен для получения данных из БД
    protected Game(){ }

    // Нужен для добавления данных в БД
    public Game(long genreId, long rulesId, long gmId, Genre genre, Rules rules, User gm, String state){
        this.genreId = genreId;
        this.rulesId = rulesId;
        this.gmId = gmId;
        this.genre = genre;
        this.gm = gm;
        this.rules = rules;
        this.state = state;
        this.time_creating = new Date();
    }

    @Override
    public String toString() {
        return  String.format("%s game genre - '%s', rules - %s, gm - %s, state - %s, creation time - %s, deleting time - @s", id, genre.toString(), rules.toString(), gm.toString(), state, time_creating.toString(), time_deleting.toString());
    }
}
