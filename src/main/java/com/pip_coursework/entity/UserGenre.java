package com.pip_coursework.entity;

import com.pip_coursework.multipleKeys.GenresUsersKey;

import javax.persistence.*;

@Entity
@IdClass(GenresUsersKey.class)
@Table(name = "Users_Genres")
public class UserGenre {
    @Id
    @ManyToOne
    private User user;

    public User getUser() {
        return user;
    }

    @Id
    @ManyToOne
    private Genre genre;

    public Genre getGenre() {
        return genre;
    }

    protected UserGenre(){ }

    public UserGenre(User user, Genre genre){
        this.user = user;
        this.genre = genre;
    }

    @Override
    public String toString() {
        return  String.format("Пользователь - '%s', жанр - '%s'", user.toString(), genre.toString());
    }
}
