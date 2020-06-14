package com.assessment.onlineshop.services;

import com.assessment.onlineshop.dtos.Item;
import com.assessment.onlineshop.repositories.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MongoItemService implements ItemService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MongoItemService.class);

    @Autowired
    private final ItemRepository repository;

    public MongoItemService(ItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public Item create(Item item) {
        LOGGER.info("Creatin a new item ", item);

        item.setId(UUID.randomUUID().toString());
        repository.save(item);

        LOGGER.info("Created a new Item");

        return item;
    }

    @Override
    public Item findById(String id) {
        Optional<Item> result = repository.findOneById(id);

        return result.orElseThrow(() -> new ItemNotFoundException(id));
    }

    @Override
    public List<Item> findAll() {
        List<Item> items = repository.findAll();

        return items;
    }

    @Override
    public List<Item> getItemPriceListByItemCount() {
        List<Item> items = repository.findAll();
        List<Item> itemPricesByNumberOfUnit = new ArrayList<>();

        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            itemPricesByNumberOfUnit.addAll(getItemPricesByNumberOfUnit(item, getItemPrice(item)));
        }
        return itemPricesByNumberOfUnit;
    }

    /**
     * This funcition is calculate the price of a given item
     * @param item
     * @return The calculated price
     */
    public double getItemPrice(Item item) {
        if (item == null) throw new NullPointerException();

        if (item.getNoOfUnits() == 0) throw new ArithmeticException();

        double cartonPrice = item.getPrice();
        double newCartonPrice = cartonPrice + (cartonPrice * 0.3);

        return newCartonPrice / item.getNoOfUnits();
    }

    public List<Item> getItemPricesByNumberOfUnit(Item item, double itemPrice) {
        List<Item> items = new ArrayList<>();

        for (int i = 1; i <= 50; i++) {
            Item newItem = new Item();
            newItem.setId(UUID.randomUUID().toString());
            newItem.setName(item.getName());
            newItem.setDescription(item.getDescription());
            newItem.setNoOfUnits(i);
            newItem.setUom("unit");
            newItem.setPrice(itemPrice * i);

            items.add(newItem);
        }

        return items;
    }
}
