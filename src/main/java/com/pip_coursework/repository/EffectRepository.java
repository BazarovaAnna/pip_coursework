package com.pip_coursework.repository;

import com.pip_coursework.entity.Effect;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface EffectRepository extends CrudRepository<Effect, Long> {
    ArrayList<Effect> findByName(String name);

    ArrayList<Effect> findById(long Id);
}
