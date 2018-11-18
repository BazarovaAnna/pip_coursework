package com.pip_coursework.repository;

import com.pip_coursework.entity.Characters_Effects;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ChracterEffectRepository extends CrudRepository<Characters_Effects, Long> {
    ArrayList<Characters_Effects> findByCharacterIdAndEffectId(long characterId, long effectId);
}
