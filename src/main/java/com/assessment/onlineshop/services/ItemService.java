package com.assessment.onlineshop.services;

import com.assessment.onlineshop.dtos.Item;

/**
 * This interface declares the methods that provides CRUD operations for
 * {@link Item} Objects
 */
public interface ItemService {
    /**
     * Creates a new item.
     * @param item  The information of the created item.
     * @return      The information of the created item.
     */
    Item create(Item item);

    /**
     * Find an item for a given item id
     * @param item
     * @return The infomation of the found item
     * @throws com.assessment.onlineshop.services.ItemNotFoundException if no item found
     */
    Item findById(Item item);
}
