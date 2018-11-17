package com.pip_coursework.repository;

import com.pip_coursework.entity.Genre;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface GenreRepository extends CrudRepository<Genre, Long> {
    ArrayList<Genre> findByName(String name);
}
