package com.pip_coursework.repository;

import com.pip_coursework.entity.Abilitie;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface AbilitieRepository extends CrudRepository<Abilitie, Long> {
    ArrayList<Abilitie> findByName(String name);
}
