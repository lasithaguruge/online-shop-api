package com.x.onlineshop.services;

import com.x.onlineshop.dtos.Item;

import java.util.List;

/**
 * This interface declares the methods that provides CRUD operations for
 * {@link com.x.onlineshop.dtos.Item} Objects
 */
public interface ItemService {
    /**
     * Creates a new item.
     * @param item  The information of the created item.
     * @return      The information of the created item.
     */
    Item create(Item item);
}
