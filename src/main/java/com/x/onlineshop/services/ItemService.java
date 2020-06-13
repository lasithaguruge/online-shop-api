package com.x.onlineshop.services;

import com.x.onlineshop.dtos.Item;

import java.util.List;

/**
 * This interface declares the methods that provides CRUD operations for
 * {@link com.x.onlineshop.dtos.Item} Objects
 */
interface ItemService {
    /**
     * Creates a new item.
     * @param item  The information of the created item.
     * @return      The information of the created item.
     */
    Item create(Item item);

    /**
     * Finds all items.
     * @return      The information of all items.
     */
    List<Item> findAll();

    /**
     * Finds a single item.
     * @param id    The id of the requested item.
     * @return      The information of the requested item.
     * @throws com.x.onlineshop.repositories.ItemNotFoundException if no item is found.
     */
    Item findById(String id);
}
