package com.pip_coursework.repository;

import com.pip_coursework.entity.Character;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface CharacterRepository extends CrudRepository<Character, Long> {
    ArrayList<Character> findByName(String name);
}
