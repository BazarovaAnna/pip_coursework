package com.pip_coursework.entity;

import javax.persistence.*;
//import org.springframework.data.rest.core.annotation.RestResource;

@Entity
@Table(name = "Rules")
public class Rule {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private User creator;

    @Column(name = "Title", unique = true, nullable = false)
    private String title;

    @Column(name = "Description", unique = true, nullable = false)
    private String description;

    protected Rule(){

    }

    public Rule(User Creator, String Title, String Description){
        this.creator = Creator;
        this.title = Title;
        this.description = Description;
    }

    public Rule(String Title, String Description){
        this.title = Title;
        this.description = Description;
    }

    @Override
    public String toString() {
        return  String.format("%s Rule title - '%s', created by %s. Description: %s", id, title, creator, description);
    }
}
