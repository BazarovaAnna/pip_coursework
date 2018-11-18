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

    @ManyToOne
    private Rule rule;

    @ManyToOne
    private User gm;

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
    public Game(Genre genre, Rule rule, User gm, String state){
        this.genre = genre;
        this.gm = gm;
        this.rule = rule;
        this.state = state;
        this.time_creating = new Date();
    }

    @Override
    public String toString() {
        return  String.format("%s game genre - '%s', rule - %s, gm - %s, state - %s, creation time - %s, deleting time - @s", id, genre.toString(), rule.toString(), gm.toString(), state, time_creating.toString(), time_deleting.toString());
    }
}
