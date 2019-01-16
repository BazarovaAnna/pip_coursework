package com.pip_coursework.repository;

import com.pip_coursework.entity.CharactersEffects;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ChracterEffectRepository extends CrudRepository<CharactersEffects, Long> {
    ArrayList<CharactersEffects> findByCharacterIdAndEffectId(long characterId, long effectId);
}
