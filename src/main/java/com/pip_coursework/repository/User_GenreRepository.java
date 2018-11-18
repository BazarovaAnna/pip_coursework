package com.pip_coursework.repository;

import com.pip_coursework.entity.User;
import com.pip_coursework.entity.Genre;
import com.pip_coursework.entity.User_Genre;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface User_GenreRepository extends CrudRepository<User_Genre, Long> {
    ArrayList<Genre> findByGenreName(String name);

    ArrayList<Genre> findByGenreId(long Id);

    ArrayList<User> findByUserLogin(String name);

    ArrayList<User> findByUserId(long Id);
}