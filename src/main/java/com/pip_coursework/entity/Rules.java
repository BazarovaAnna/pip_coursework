package com.pip_coursework.entity;

import javax.persistence.*;
import org.springframework.data.rest.core.annotation.RestResource;

@Entity
@Table(name = "Rules")
public class Rules {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @OneToOne
    @JoinColumn(name = "Creator_Id", unique = false, nullable = true)
    @RestResource(path = "Creator", rel="User")
    private long Creator;

    @Column(name = "Title", unique = true, nullable = false)
    private String Title;

    @Column(name = "Description", unique = true, nullable = false)
    private String Description;

    protected Rules(){

    }

    public Rules(long Creator, String Title, String Description){
        this.Creator = Creator;
        this.Title = Title;
        this.Description = Description;
    }

    public Rules(String Title, String Description){
        this.Title = Title;
        this.Description = Description;
    }

    @Override
    public String toString() {
        return  String.format("%s Rules title - '%s', created by %s. Description: %s", Id, Title, Creator, Description);
    }
}
