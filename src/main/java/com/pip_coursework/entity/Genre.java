package com.pip_coursework.entity;

import javax.persistence.*;

@Entity
@Table(name = "Genres")
public class Genre {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;

    @Column(name = "Name", unique = true, nullable = false)
    private String name;

    protected Genre(){

    }

    public Genre(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return  String.format("%s Genre name - '%s'",id, name);
    }
}
