package com.pip_coursework.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Race implements Serializable {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String type;

    private String imgPath;

    private char sex;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        type = type;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        imgPath = imgPath;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        sex = sex;
    }

    public Race(){}

    public Race(char sex, String imgPath, String type) {
        this.sex = sex;
        this.imgPath = imgPath;
        this.type = type;
    }
}
