package com.pip_coursework.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Genres")
public class Genre {
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

    @OneToMany(mappedBy = "genre")
    private List<UserGenre> userGenres;

    public void setUserGenre(List<UserGenre> UserGenres) {
        this.userGenres = UserGenres;
    }

    public List<UserGenre> getUserGenres() {
        return userGenres;
    }

    public void addUserGenre(UserGenre userGenre) {
        this.userGenres.add(userGenre);
    }

    public void removeUserGenre(UserGenre userGenre) {
        this.userGenres.remove(userGenre);
    }

    protected Genre(){ }

    public Genre(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return  String.format("%s Название жанра - '%s'", id, name);
    }
}
