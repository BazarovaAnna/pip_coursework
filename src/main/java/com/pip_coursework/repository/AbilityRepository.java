package com.pip_coursework.repository;

import com.pip_coursework.entity.Ability;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface AbilityRepository extends CrudRepository<Ability, Long> {
    ArrayList<Ability> findByName(String name);

    ArrayList<Ability> findById(long Id);
}
