package com.pip_coursework.repository;

import com.pip_coursework.entity.Inventory;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface InventoryRepository extends CrudRepository<Inventory, Long> {
    ArrayList<Inventory> findByCharacterIdAndItemId(long characterId, long itemId);
}
