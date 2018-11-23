package com.pip_coursework.entity;

import javax.persistence.*;
import com.pip_coursework.multipleKeys.usersGenresKey;

@Entity
@IdClass(usersGenresKey.class)
@Table(name = "Users_Genres")
public class UserGenre {

    @ManyToOne
    private User user;

    @Id
    @Column(name = "User_Id", insertable = false, updatable = false)
    private long userId;

    @ManyToOne
    private Genre genre;

    @Id
    @Column(name = "Genre_Id", insertable = false, updatable = false)
    private long genreId;

    protected UserGenre(){
    }

    public UserGenre(long userId, long genreId, User user, Genre genre){
        this.userId = userId;
        this.genreId = genreId;
        this.user = user;
        this.genre = genre;
    }

    @Override
    public String toString() {
        return  String.format("User - '%s', genre - '%s'", user, genre);
    }
}
