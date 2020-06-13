package com.assessment.onlineshop.repositories;

import com.assessment.onlineshop.dtos.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * This repository provides CRUD operations for Item objects.
 */

public interface ItemRepository extends MongoRepository<Item, String> {
     /**
     * Saves a new item to the database.
     * @param saved The information of the saved item.
     * @return      The information of the saved item.
     */
    Item save(Item saved);
}
