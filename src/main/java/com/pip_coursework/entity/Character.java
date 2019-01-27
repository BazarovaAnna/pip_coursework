package com.pip_coursework.entity;

import javax.persistence.*;

@Entity
@Table(name = "Characters")
public class Character {
    private static final String BASECONDITION = "Жив";
    private static final long BASELEVEL = 1;
    private static final double BASEMAXWEIGHT = 20.0;
    private static final double BASEPERMONEY = 0.0;


    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public long getId() {
        return id;
    }

    @ManyToOne
    private User user;

    public User getUser() {
        return user;
    }

    @ManyToOne
    private Race race;

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


    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Character(){}

    public Character(String name, char sex, String userClass, User user, Race race){
        this.condition = BASECONDITION.toCharArray();
        this.level = BASELEVEL;
        this.maxWeight = calculateMaxWeight(race.getType(), sex, user.getLogin());
        this.name = name;
        this.persMoney = calculatePersMoney(race.getType(), sex, user.getLogin());
        this.sex = sex;
        this.userClass = userClass;
        this.user = user;
        this.race = race;
    }

    // Вычисление максимального веса
    private double calculateMaxWeight(String type, char sex, String  username){
        double weight = BASEMAXWEIGHT;
        if(sex == 'f'){
            weight -= 10;
        }
        if(type.equals("орк") || type.equals("гном")){
            weight += 10;
        }

        if(username.equals("Admin")){
            weight += 40;
        }

        return weight;
    }

    // Вычисление стартового кошелька
    private double calculatePersMoney(String type, char sex, String  username){
        double perMoney = BASEPERMONEY;
        if(type.equals("эльф")){
            perMoney += 10;
        }
        if(sex == 'm'){
            perMoney += 5;
        }

        if(username.equals("Admin")){
            perMoney += 1000;
        }

        return perMoney;
    }
}