package com.pip_coursework.entity;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

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

    // Нужен для получения данных из БД
    protected Session(){ }

    public Session(Game game){
        this.game = game;
        this.dateStart = Instant.now();
    }
}
