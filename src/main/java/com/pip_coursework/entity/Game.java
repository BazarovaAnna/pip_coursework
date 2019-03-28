package com.pip_coursework.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;
import java.util.List;

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

    public void setGm(User gm) { this.gm = gm; }

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

    @Column(name = "Time_Creating", nullable = false)
    private Instant timeCreating;

    public void setTimeCreating(Instant timeCreating){this.timeCreating = timeCreating; }

    public Instant getTimeCreating() {
        return timeCreating;
    }

    @Column(name = "Time_Deleting", nullable = true)
    private Instant timeDeleting;

    public Instant getTimeDeleting() {
        return timeDeleting;
    }

    public void setTimeDeleting(Instant timeDeleting) {
        this.timeDeleting = timeDeleting;
    }

    @OneToMany(mappedBy = "character")
    @JsonBackReference
    private List<Group> groups;

    public void setGroups(List<Group> Groups) {
        this.groups = Groups;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void addGroup(Group group) {
        this.groups.add(group);
    }

    public void removeGroup(Group group) {
        this.groups.remove(group);
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
        this.state = GameState.INACTIVESTATE;
        this.timeCreating = Instant.now();
    }
}
