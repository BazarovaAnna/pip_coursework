package com.pip_coursework.entity;

import javax.persistence.*;

@Entity
@Table(name = "Characters")
public class Character {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public long getId() {
        return id;
    }

    @ManyToOne
    private User user;

    @ManyToOne
    private Character Role;

    public User getUser() {
        return user;
    }

    @Column(name = "Name", unique = true, nullable = false)
    private  String name;

    public String getName() {
        return name;
    }

    @Column(name = "Class", nullable = false)
    private  String userClass;

    public String getUserClass() {
        return userClass;
    }

    @Column(name = "Race", nullable = false)
    private  String race;

    public String getRace() {
        return race;
    }

    @Column(name = "Story")
    private  String story;

    public String getStory() {
        return story;
    }

    @Column(name = "Sex", length = 1)
    private char sex;

    public char getSex() {
        return sex;
    }

    @Column(name =  "Condition", length = 40, nullable = false)
    private char[] condition;

    public char[] getCondition() {
        return condition;
    }

    public void setCondition(char[] condition) {
        this.condition = condition;
    }

    @Column(name = "Pers_Money", nullable = false)
    private double persMoney;

    public double getPersMoney() {
        return persMoney;
    }

    public void setPersMoney(double persMoney) {
        this.persMoney = persMoney;
    }

    @Column(name = "Max_Weight", nullable = false)
    private double maxWeight;

    public double getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
    }

    @Column(name = "Level", nullable = false)
    private long level;

    public long getLevel() {
        return level;
    }

    public void setLevel(long level) {
        this.level = level;
    }

    public Character getRole() {
        return Role;
    }

    public void setRole(Character role) {
        Role = role;
    }

    protected Character(){ }

    public Character(User user, String name, String userClass,
                      String race, String story, char sex,
                      char[] condition, double maxWeight, long level){
        this.user = user;
        this.name = name;
        this.userClass = userClass;
        this.race = race;
        this.story = story;
        this.sex = sex;
        this.condition = condition;
        this.persMoney = 0;
        this.maxWeight = maxWeight;
        this.level = level;
    }

    public Character(User user, String name, String userClass,
                     String race, char sex,
                     char[] condition, double persMoney,
                     double maxWeight, long level){
        this.user = user;
        this.name = name;
        this.userClass = userClass;
        this.race = race;
        this.sex = sex;
        this.condition = condition;
        this.persMoney = persMoney;
        this.maxWeight = maxWeight;
        this.level = level;
    }

    public Character(User user, String name, String userClass,
                     String race, String story,
                     char[] condition, double maxWeight, long level){
        this.user = user;
        this.name = name;
        this.userClass = userClass;
        this.race = race;
        this.story = story;
        this.condition = condition;
        this.persMoney = 0;
        this.maxWeight = maxWeight;
        this.level = level;
    }

    public Character(User user, String name, String userClass, String race,
                     char[] condition, double maxWeight, long level){
        this.user = user;
        this.name = name;
        this.userClass = userClass;
        this.race = race;
        this.condition = condition;
        this.persMoney = 0;
        this.maxWeight = maxWeight;
        this.level = level;
    }

    @Override
    public String toString() {
        return String.format("%s это %s %s %d левела.", name, userClass, race, level);
    }


}