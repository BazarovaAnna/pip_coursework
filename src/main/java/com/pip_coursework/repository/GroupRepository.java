package com.pip_coursework.repository;

import com.pip_coursework.entity.Group;
import org.springframework.data.repository.CrudRepository;
import com.pip_coursework.multipleKeys.groupsKey;

import java.util.ArrayList;

public interface GroupRepository extends CrudRepository<Group, Long> {
    ArrayList<Group> findByGameIdAndCharacterId(long gameId, long characterId);
    ArrayList<Group> findByGameId(long gameId);
    ArrayList<Group> findByCharacterId(long characterId);
    //а нужен ли нам вообще этот метод? мне кажется нет, нам findall достаточно
    //хотя в принципе не так уж и плохо иметь кучу методов
}