package com.x.onlineshop.repositories;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String itemId) {
        super(String.format("No item found with id: <%s>", itemId));
    }
}
