package com.pip_coursework.entity;

import com.pip_coursework.multipleKeys.membersKey;

import javax.persistence.*;

@Entity
@IdClass(membersKey.class)
@Table(name = "Members")
public class Member {

    @ManyToOne
    private Session session;

    @Id
    @Column(name = "Session_Id", insertable = false, updatable = false)
    private long sessionId;

    @ManyToOne
    private Character character;

    @Id
    @Column(name = "Character_Id", insertable = false, updatable = false)
    private long characterId;

    @Column(name = "Characters_Rating", nullable = false)
    private float charactersRating;

    protected Member(){
    }

    public Member(long sessionId, long characterId, Session session, Character character, float charactersRating){
        this.sessionId = sessionId;
        this.session = session;
        this.characterId = characterId;
        this.character = character;
        this.charactersRating = charactersRating;
    }

    @Override
    public String toString() {
        return  String.format("Session - '%s', character - '%s', character's rating - '%s'", session.toString(), character.toString(), charactersRating);
    }
}