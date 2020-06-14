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
}
