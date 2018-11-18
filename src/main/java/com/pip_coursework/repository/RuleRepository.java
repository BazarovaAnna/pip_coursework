package com.pip_coursework.repository;

import com.pip_coursework.entity.Rule;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface RuleRepository extends CrudRepository<Rule, Long> {
    List<Rule> findByTitle(String Title);

    ArrayList<Rule> findById(long Id);
}
