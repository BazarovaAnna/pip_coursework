package com.pip_coursework.entity;

import javax.persistence.*;

@Entity
@Table(name = "Rules")
public class Rules {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public long getId() {
        return id;
    }

    @ManyToOne
    private User creator;

    public User getCreator() {
        return creator;
    }

    @Column(name = "Title", unique = true, nullable = false)
    private String title;

    public String getTitle() {
        return title;
    }

    @Column(name = "Description", unique = true, nullable = false)
    private String description;

    public String getDescription() {
        return description;
    }

    protected Rules(){ }

    public Rules(User Creator, String Title, String Description){
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
        return  String.format("%s Название правил - '%s', созданы: %s. Описание: %s", id, title, creator, description);
    }
}
