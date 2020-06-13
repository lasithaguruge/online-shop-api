package com.x.onlineshop.repositories;

import com.x.onlineshop.dtos.Item;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

/**
 * This repository provides CRUD operations for Item objects.
 */
interface ItemRepository extends Repository<Item, String> {
    /**
     * Finds all item entries from the database.
     * @return  The information of all item entries that are found from the database.
     */
    List<Item> findAll();

    /**
     * Finds the information of a single item entry.
     * @param id    The id of the requested item entry.
     * @return      The information of the found item entry. If no item entry
     *              is found, this method returns an empty {@link java.util.Optional} object.
     */
    Optional<Item> findOne(String id);

    /**
     * Saves a new item entry to the database.
     * @param saved The information of the saved item entry.
     * @return      The information of the saved item entry.
     */
    Item save(Item saved);
}
