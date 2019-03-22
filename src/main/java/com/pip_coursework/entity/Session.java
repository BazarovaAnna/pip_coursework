package com.pip_coursework.entity;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Sessions")
public class Session {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public long getId() {
        return id;
    }

    @ManyToOne
    private Game game;

    public Game getGame() {
        return game;
    }

    @Column(name = "GMs_Rating")
    private float gmsRating;

    public float getGmsRating() {
        return gmsRating;
    }

    public void setGmsRating(float gmsRating) {
        this.gmsRating = gmsRating;
    }

    @Column(name = "DateStart", nullable = false)
    private Instant dateStart;

    public Instant getDateStart() {
        return dateStart;
    }

    @Column(name = "DateEnd", nullable = true)
    private Instant dateEnd;

    public void setDateEnd(Instant dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Instant getDate_end() {
        return dateEnd;
    }

    @OneToMany(mappedBy = "session")
    private List<Member> members;

    public void setMembers(List<Member> member) {
        this.members = member;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void addMember(Member member) {
        this.members.add(member);
    }

    public void removeMember(Member member) {
        this.members.remove(member);
    }

    // Нужен для получения данных из БД
    protected Session(){ }

    public Session(Game game){
        this.game = game;
        this.dateStart = Instant.now();
    }
}
