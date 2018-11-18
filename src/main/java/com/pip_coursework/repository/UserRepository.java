package com.pip_coursework.repository;

import com.pip_coursework.entity.Effect;
import com.pip_coursework.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByLogin(String login);

    ArrayList<User> findById(long Id);
}
