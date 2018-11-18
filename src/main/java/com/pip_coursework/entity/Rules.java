package com.pip_coursework.entity;

import javax.persistence.*;
//import org.springframework.data.rest.core.annotation.RestResource;

@Entity
@Table(name = "Rules")
public class Rules {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private User creator;

    @Column(name = "Creator")
    private long creatorId;

    @Column(name = "Title", unique = true, nullable = false)
    private String title;

    @Column(name = "Description", unique = true, nullable = false)
    private String description;

    protected Rules(){

    }

    public Rules(long creatorId, User Creator, String Title, String Description){
        this.creatorId = creatorId;
        this.creator = Creator;
        this.title = Title;
        this.description = Description;
    }

    public Rules(String Title, String Description){
        this.title = Title;
        this.description = Description;
    }

    @Override
    public String toString() {
        return  String.format("%s Rules title - '%s', created by %s. Description: %s", id, title, creator, description);
    }
}
