package com.pip_coursework.entity;

import javax.persistence.*;

@Entity
@Table(name = "Characters")
public class Character {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private  User user;

    @Column(name = "Name", unique = true, nullable = false)
    private  String name;

    @Column(name = "Class", nullable = false)
    private  String userClass;

    @Column(name = "Race", nullable = false)
    private  String race;

    @Column(name = "Story")
    private  String story;

    @Column(name = "Sex", length = 1)
    private char sex;

    @Column(name =  "Condition", length = 40, nullable = false)
    private char[] condition;

    @Column(name = "Pers_Money", nullable = false)
    private double persMoney;

    @Column(name = "Max_Weight", nullable = false)
    private double maxWeight;

    @Column(name = "Level", nullable = false)
    private long level;

    protected Character(){ }

    public  Character(User user, String name, String userClass,
                      String race, String story, char sex,
                      char[] condition, double persMoney,
                      double maxWeight, long level){
        this.user = user;
        this.name = name;
        this.userClass = userClass;
        this.race = race;
        this.story = story;
        this.sex = sex;
        this.condition = condition;
        this.persMoney = persMoney;
        this.maxWeight = maxWeight;
        this.level = level;
    }

    @Override
    public String toString() {
        return String.format("%s это %s %s %d левела", name, userClass, race, level);
    }
}