package com.pip_coursework.repository;


import com.pip_coursework.entity.CharactersAbilities;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface CharacterAbilityRepository extends CrudRepository<CharactersAbilities, Long> {
    ArrayList<CharactersAbilities> findByCharacterIdAndAbilityId(long characterId, long abilityId);
}
