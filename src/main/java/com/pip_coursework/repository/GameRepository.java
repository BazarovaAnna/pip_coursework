package com.pip_coursework.repository;

import com.pip_coursework.entity.Game;
import com.pip_coursework.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface GameRepository extends CrudRepository<Game, Long> {
    Game findById(long Id);

    ArrayList<Game> findAllByState(String state);

    ArrayList<Game> findAllByGmAndState(User gm, String state);

    Game findByGmAndName(User user, String name);

    boolean existsByNameAndState(String name, String state);
}
