package com.pip_coursework.repository;

import com.pip_coursework.entity.User_Genre;
import org.springframework.data.repository.CrudRepository;
import com.pip_coursework.multipleKeys.usersGenresKey;

import java.util.ArrayList;

public interface User_GenreRepository extends CrudRepository<User_Genre, Long> {
    ArrayList<User_Genre> findByGenreIdAndUserId(long userId, long genreId);
    ArrayList<User_Genre> findByGenreId(long genreId);
    ArrayList<User_Genre> findByUserId(long userId);
    //а нужен ли нам вообще этот метод? мне кажется нет, нам findall достаточно
    //хотя в принципе не так уж и плохо иметь кучу методов
}