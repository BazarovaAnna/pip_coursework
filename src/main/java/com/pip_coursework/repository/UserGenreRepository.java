package com.pip_coursework.repository;

import com.pip_coursework.entity.UserGenre;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface UserGenreRepository extends CrudRepository<UserGenre, Long> {
    ArrayList<UserGenre> findByGenreIdAndUserId(long userId, long genreId);
    ArrayList<UserGenre> findByGenreId(long genreId);
    ArrayList<UserGenre> findByUserId(long userId);
}