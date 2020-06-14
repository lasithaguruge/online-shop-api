package com.assessment.onlineshop.services;

import com.assessment.onlineshop.dtos.Item;

import java.util.List;

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
     * @param id
     * @return The infomation of the found item
     * @throws com.assessment.onlineshop.services.ItemNotFoundException if no item found
     */
    Item findById(String id);

    /**
     * Find all items in the item collection
     * @return List of item objects
     */
    List<Item> findAll();

    /**
     * Find each item actual prices from 1-50 units
     * @return The item price list
     */
    List<Item> getItemPriceListByItemCount();
}
