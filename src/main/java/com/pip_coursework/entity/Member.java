package com.pip_coursework.entity;

import com.pip_coursework.multipleKeys.membersKey;

import javax.persistence.*;

@Entity
@IdClass(membersKey.class)
@Table(name = "Members")
public class Member {
    @Id
    @ManyToOne
    private Session session;

    public Session getSession() {
        return session;
    }

    @Id
    @ManyToOne
    private Character character;

    public Character getCharacter() {
        return character;
    }

    @Column(name = "Characters_Rating")
    private float charactersRating;

    public float getCharactersRating() {
        return charactersRating;
    }

    public void setCharactersRating(float charactersRating) {
        this.charactersRating = charactersRating;
    }

    protected Member(){
    }

    public Member(Session session, Character character, float charactersRating){
        this.session = session;
        this.character = character;
        this.charactersRating = charactersRating;
    }

    public Member(Session session, Character character){
        this.session = session;
        this.character = character;
    }

    @Override
    public String toString() {
        return  String.format("Сессия: '%s', персонаж: '%s', рейтинг персонажа: '%s'",
                session.toString(), character.toString(), charactersRating);
    }
}