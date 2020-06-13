package com.assessment.onlineshop.services;

import com.assessment.onlineshop.dtos.Item;
import com.assessment.onlineshop.repositories.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

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

    public double calculateItemPrice(Item item) {
        if (item == null) throw new NullPointerException();

        if (item.getNoOfUnits() == 0) throw new ArithmeticException();

        double cartonPrice = item.getPrice();
        double newCartonPrice = cartonPrice + (cartonPrice * 0.3);

        return newCartonPrice / item.getNoOfUnits();
    }

    public double calculateDiscount(Item item, int quantity) {
        if (item == null) throw new NullPointerException();

        double cartonPrice = item.getPrice();
        double discountCartonPrice = cartonPrice - (cartonPrice * 0.1);

        return discountCartonPrice * quantity;
    }
}
