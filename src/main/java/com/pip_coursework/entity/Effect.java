package com.pip_coursework.entity;

import javax.persistence.*;

@Entity
@Table(name = "Effects")
public class Effect {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;

    @Column(name = "Name", unique = true, nullable = false)
    private String name;

    @Column(name = "Description")
    private String description;

    protected Effect(){

    }

    public Effect(String name, String description){
        this.name = name;
        this.description= description;
    }

    @Override
    public String toString() {
        return  String.format("%s Effect name - '%s'",id, name);
    }
}
