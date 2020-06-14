package com.assessment.onlineshop.controllers;

import com.assessment.onlineshop.dtos.Item;
import com.assessment.onlineshop.services.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ItemController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemController.class);
    private final ItemService service;

    public ItemController(ItemService service) {
        this.service = service;
    }

    @PostMapping("/api/items")
    @ResponseStatus(HttpStatus.CREATED)
    Item add(@RequestBody @Validated Item item) {
        LOGGER.info("Received a item data to add a new item ", item);

        Item created = service.create(item);

        return created;
    }

    @GetMapping("/api/items/{id}")
    @ResponseStatus(HttpStatus.OK)
    Item get(@PathVariable(value = "id") String id) {
        LOGGER.info("Received a item data to add a new item ", id);

        Item item = service.findById(id);

        return item;
    }

    @GetMapping("/api/items")
    @ResponseStatus(HttpStatus.OK)
    List<Item> getAll() {
        List<Item> items = service.findAll();

        return items;
    }

    @GetMapping("/api/items/prices")
    @ResponseStatus(HttpStatus.OK)
    List<Item> getPriceList() {
        return service.getItemPriceListByItemCount();
    }
}
