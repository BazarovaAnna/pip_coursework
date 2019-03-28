package com.pip_coursework.repository;

import com.pip_coursework.entity.CharactersEffects;
import com.pip_coursework.entity.Effect;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

public interface CharacterEffectRepository extends CrudRepository<CharactersEffects, Long> {
    ArrayList<CharactersEffects> findByCharacterIdAndEffectId(long characterId, long effectId);

    ArrayList<CharactersEffects> findByCharacterId(long id);

    @Transactional
    void deleteByCharacterIdAndEffectId(long characterId, long effectId);
}
