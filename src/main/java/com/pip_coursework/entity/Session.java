package com.pip_coursework.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Games")
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

    @Basic(optional = false)
    @Column(name = "Date_Start", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date_start;

    public Date getDate_start() {
        return date_start;
    }

    @Basic(optional = false)
    @Column(name = "Date_End")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date_end;

    public void setDate_end(Date date_end) {
        this.date_end = date_end;
    }

    public Date getDate_end() {
        return date_end;
    }

    // Нужен для получения данных из БД
    protected Session(){ }

    // Нужен для добавления данных в БД
    public Session(Game game){
        this.game = game;
        this.date_start = new Date();
    }

    public Session(Game game, float gmsRating){
        this.game = game;
        this.date_start = new Date();
        this.gmsRating = gmsRating;
    }

    @Override
    public String toString() {
        return  String.format("%s Сессия из игры: %s, рейтинг ГМа - %s, длится с %s по %s",
                id, game.toString(), gmsRating, date_start.toString(), date_end.toString());
    }
}
