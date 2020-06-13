package com.assessment.onlineshop.controllers;

import com.assessment.onlineshop.dtos.Item;
import com.assessment.onlineshop.services.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    Item add(@PathVariable(value = "id") String id) {
        LOGGER.info("Received a item data to add a new item ", id);

        Item item = service.findById(id);

        return item;
    }
}
