package com.pip_coursework.repository;

import com.pip_coursework.entity.Character;
import com.pip_coursework.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface CharacterRepository extends CrudRepository<Character, Long> {
    Character findByName(String name);

    ArrayList<Character> findById(long id);

    boolean existsByName(String name);

    ArrayList<Character> findAllByUser(User user);
}
