package com.pip_coursework.repository;

import com.pip_coursework.entity.Abilitie;
import com.pip_coursework.entity.Effect;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface AbilitieRepository extends CrudRepository<Abilitie, Long> {
    ArrayList<Abilitie> findByName(String name);

    ArrayList<Abilitie> findById(long Id);
}
