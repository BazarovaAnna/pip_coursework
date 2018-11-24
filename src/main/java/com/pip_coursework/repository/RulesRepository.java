package com.pip_coursework.repository;

import com.pip_coursework.entity.Rules;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface RulesRepository extends CrudRepository<Rules, Long> {
    ArrayList<Rules> findByTitle(String Title);

    ArrayList<Rules> findById(long Id);
}
