package com.pip_coursework.repository;

import com.pip_coursework.entity.Member;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface MemberRepository extends CrudRepository<Member, Long> {
    ArrayList<Member> findBySessionIdAndCharacterId(long sessionId, long characterId);
    ArrayList<Member> findBySessionId(long sessionId);
    ArrayList<Member> findByCharacterId(long characterId);
}