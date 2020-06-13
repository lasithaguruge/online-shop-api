package com.x.onlineshop.services;

import com.x.onlineshop.dtos.Item;
import com.x.onlineshop.repositories.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        repository.save(item);
        LOGGER.info("Created a new Item");

        return item;
    }
}
