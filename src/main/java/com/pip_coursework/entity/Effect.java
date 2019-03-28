package com.pip_coursework.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Effects")
public class Effect{
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;

    public long getId() {
        return id;
    }

    @Column(name = "Name", unique = true, nullable = false)
    private String name;

    public String getName() {
        return name;
    }

    @Column(name = "Description")
    private String description;

    public String getDescription() {
        return description;
    }

    @OneToMany(mappedBy = "effect")
    private List<CharactersEffects> charactersEffects;

    public void setCharactersEffects(List<CharactersEffects> charactersEffects) {
        this.charactersEffects = charactersEffects;
    }

    public List<CharactersEffects> getCharactersEffects() {
        return charactersEffects;
    }

    public void addCharacterEffect(CharactersEffects charactersEffect) {
        this.charactersEffects.add(charactersEffect);
    }

    public void removeCharacterEffects(CharactersEffects charactersEffect) {
        this.charactersEffects.remove(charactersEffect);
    }

    protected Effect(){ }

    public Effect(String name, String description){
        this.name = name;
        this.description= description;
    }

    @Override
    public String toString() {
        return  String.format("{\"id\":\"%s\",\"name\":\"%s\",\"description\":\"%s\"}", id, name, description);
    }
}
