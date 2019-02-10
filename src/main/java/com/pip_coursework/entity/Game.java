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

    public long getId() {
        return id;
    }

    @ManyToOne
    private Genre genre;

    public Genre getGenre() {
        return genre;
    }

    @ManyToOne
    private Rules rules;

    public Rules getRules() {
        return rules;
    }

    @ManyToOne
    private User gm;

    public User getGm() {
        return gm;
    }

    @Column(name = "State", nullable = false)
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic(optional = false)
    @Column(name = "Time_Creating", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeCreating;

    public Date getTimeCreating() {
        return timeCreating;
    }

    @Basic(optional = false)
    @Column(name = "Time_Deleting", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeDeleting;

    public Date getTimeDeleting() {
        return timeDeleting;
    }

    public void setTimeDeleting(Date timeDeleting) {
        this.timeDeleting = timeDeleting;
    }

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String name;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Нужен для получения данных из БД
    public Game(){ }

    public Game(String state){
        this.state = state;
    }

    // Нужен для добавления данных в БД
    public Game(Genre genre, Rules rules, User gm, String state){
        this.genre = genre;
        this.gm = gm;
        this.rules = rules;
        this.state = state;
        this.timeCreating = new Date();
    }
}
