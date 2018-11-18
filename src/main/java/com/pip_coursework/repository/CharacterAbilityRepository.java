package com.pip_coursework.repository;


import com.pip_coursework.entity.Characters_Abilities;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface CharacterAbilityRepository extends CrudRepository<Characters_Abilities, Long> {
    ArrayList<Characters_Abilities> findByCharacterIdAndAbilityId(long characterId, long abilityId);
}
