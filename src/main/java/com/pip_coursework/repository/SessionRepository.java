package com.pip_coursework.repository;

import com.pip_coursework.entity.Session;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface SessionRepository extends CrudRepository<Session, Long> {
    ArrayList<Session> findById(long Id);
}
