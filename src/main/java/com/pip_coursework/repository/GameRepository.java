package com.pip_coursework.repository;

import com.pip_coursework.entity.Game;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface GameRepository extends CrudRepository<Game, Long> {
    ArrayList<Game> findById(long Id);
}
