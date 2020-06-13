package com.assessment.onlineshop.repositories;

import com.assessment.onlineshop.dtos.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

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

    /**
     * Find an item for a given item id
     * @param id
     * @return The infomation of the found item. if no item is found
     *         this method returns an empty {@link java.util.Optional} object
     */
    Optional<Item> findOneById(String id);
}
