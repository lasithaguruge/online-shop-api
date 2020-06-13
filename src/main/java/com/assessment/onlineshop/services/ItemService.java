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
}
