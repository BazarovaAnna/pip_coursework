package com.pip_coursework.entity;

import com.pip_coursework.multipleKeys.MembersKey;

import javax.persistence.*;

@Entity
@IdClass(MembersKey.class)
@Table(name = "Members")
public class Member {
    @Id
    @ManyToOne
    @JoinColumn(name = "session_id", referencedColumnName = "id")
    private Session session;

    public Session getSession() {
        return session;
    }

    @Id
    @ManyToOne
    @JoinColumn(name = "character_id", referencedColumnName = "id")
    private Character character;

    public Character getCharacter() {
        return character;
    }

    @Column(name = "Characters_Rating", nullable = true)
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