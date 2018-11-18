package com.pip_coursework.repository;

import com.pip_coursework.entity.Member;
import org.springframework.data.repository.CrudRepository;
import com.pip_coursework.multipleKeys.membersKey;

import java.util.ArrayList;

public interface MemberRepository extends CrudRepository<Member, Long> {
    ArrayList<Member> findBySessionIdAndCharacterId(long sessionId, long characterId);
    ArrayList<Member> findBySessionId(long sessionId);
    ArrayList<Member> findByCharacterId(long characterId);
    //а нужен ли нам вообще этот метод? мне кажется нет, нам findall достаточно
    //хотя в принципе не так уж и плохо иметь кучу методов
}