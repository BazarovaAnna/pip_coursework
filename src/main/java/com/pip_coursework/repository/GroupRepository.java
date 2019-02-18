package com.pip_coursework.repository;

import com.pip_coursework.entity.Character;
import com.pip_coursework.entity.Game;
import com.pip_coursework.entity.Group;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface GroupRepository extends CrudRepository<Group, Long> {
    ArrayList<Group> findByGameIdAndCharacterId(long gameId, long characterId);
    ArrayList<Group> findByGameId(long gameId);
    ArrayList<Group> findByCharacterId(long characterId);

    ArrayList<Character> findAllByGame(Game game);
}