package com.pip_coursework.repository;

import com.pip_coursework.entity.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ItemRepository extends CrudRepository<Item, Long> {
    ArrayList<Item> findByName(String name);
    ArrayList<Item> findById(long id);
}
