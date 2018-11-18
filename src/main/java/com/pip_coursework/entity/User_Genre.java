package com.pip_coursework.entity;

import javax.persistence.*;
import com.pip_coursework.multipleKeys.usersGenresKey;

@Entity
@IdClass(usersGenresKey.class)
@Table(name = "Users_Genres")
public class User_Genre {
    @Id
    @ManyToOne
    private User user;

    @Column(name = "User_Id")
    private long userId;

    @Id
    @ManyToOne
    private Genre genre;

    @Column(name = "Genre_Id")
    private long genreId;

    protected User_Genre(){
    }

    public User_Genre(long userId, long genreId, User user, Genre genre){
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
