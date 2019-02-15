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

    @Column(name = "Name", nullable = false)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "Password", nullable = true)
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "PersonCount", nullable = false)
    private int personCount;

    public int getPersonCount() {
        return personCount;
    }

    public void setPersonCount(int personCount) {
        this.personCount = personCount;
    }

    @Column(name = "Description", nullable = true)
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    // Нужен для получения данных из БД
    public Game(){ }

    public Game(String state){
        this.state = state;
    }

    // Нужен для добавления данных в БД
    public Game(User gm, String name, String password, String description, int personCount){
        this.gm = gm;
        this.name = name;
        this.password = password;
        this.personCount = personCount;
        this.description = description;
        this.state = GameState.ACTIVESTATE;
        this.timeCreating = new Date();
    }
}
