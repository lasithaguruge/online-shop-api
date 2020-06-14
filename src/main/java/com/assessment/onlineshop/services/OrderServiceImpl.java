package com.assessment.onlineshop.services;

import com.assessment.onlineshop.dtos.Item;
import com.assessment.onlineshop.dtos.Order;
import com.assessment.onlineshop.utill.ItemCountStoreByUOM;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    @Override
    public double calculateOrderTotal(ArrayList<Order.OrderItem> orderItems) {
        List<ItemCountStoreByUOM> itemCountListByUOM = getItemCountListByUOM(orderItems);
        return 0;
    }

    /**
     * This funcition is calculate the price of a given item
     * @param item
     * @return The calculated price
     */
    public double calculateItemPrice(Item item) {
        if (item == null) throw new NullPointerException();

        if (item.getNoOfUnits() == 0) throw new ArithmeticException();

        double cartonPrice = item.getPrice();
        double newCartonPrice = cartonPrice + (cartonPrice * 0.3);

        return newCartonPrice / item.getNoOfUnits();
    }

    /**
     * This function calculate the carton discount of a given item
     * @param item
     * @return The discount amount
     */
    public double getDiscountCartonAmount(Item item) {
        if (item == null) throw new NullPointerException();

        double cartonPrice = item.getPrice();
        double discountCartonPrice = cartonPrice - (cartonPrice * 0.1);

        return discountCartonPrice;
    }

    /**
     * This function is find item count for unit of measure in each item in the order items
     * @param orderItems
     * @return The object which contains item id and quantities for each unit of measure
     */
    public List<ItemCountStoreByUOM> getItemCountListByUOM(ArrayList<Order.OrderItem> orderItems) {
        Map<String, Integer> cartonItemCountMap = new HashMap<>();
        Map<String, Integer> singleItemCountMap = new HashMap<>();
        List<String> itemIds = new ArrayList<>();

        for (int i = 0; i < orderItems.size(); i++) {
            Order.OrderItem orderItem = orderItems.get(i);
            Item item = orderItem.getItem();
            String itemId = item.getId();

            if (!itemIds.contains(itemId)) itemIds.add(itemId);

            if (orderItem.getUom().equals("carton")) {
                updateCountMap(cartonItemCountMap, orderItem);
            } else {
                updateCountMap(singleItemCountMap, orderItem);

                int existingSingleItemCount = singleItemCountMap.get(itemId);
                if (existingSingleItemCount > item.getNoOfUnits()) {
                    int cartonCountFromSingleItems = existingSingleItemCount / item.getNoOfUnits();
                    int newSingleItemCount = existingSingleItemCount - (cartonCountFromSingleItems * item.getNoOfUnits());

                    updateCountMap(itemId, cartonItemCountMap, existingSingleItemCount, newSingleItemCount);

                    if (cartonItemCountMap.containsKey(itemId)) {
                        int existingCartonCount = cartonItemCountMap.get(itemId);
                        int newCartonCount = existingCartonCount + cartonCountFromSingleItems;

                        updateCountMap(itemId, cartonItemCountMap, existingCartonCount, newCartonCount);
                    }
                }
            }
        }

        return getItemCountListByUOM(itemIds, cartonItemCountMap, singleItemCountMap);
    }

    public List<ItemCountStoreByUOM> getItemCountListByUOM(List<String> itemIds, Map<String, Integer> cartonCountMap, Map<String, Integer> singleItemCountMap) {
        List<ItemCountStoreByUOM> countStoreByUOMS = new ArrayList<>();
        itemIds.forEach(id -> {
            Map<String, Integer> countMapByUOM = new HashMap<>();

            countMapByUOM.put("carton", cartonCountMap.get(id));
            countMapByUOM.put("unit", singleItemCountMap.get(id));

            countStoreByUOMS.add(new ItemCountStoreByUOM(id, cartonCountMap));
        });

        return countStoreByUOMS;
    }

    /**
     * This function is update the given map values by matching the order item id with keys of map
     * @param map
     * @param orderItem
     */
    public void updateCountMap(Map<String, Integer> map, Order.OrderItem orderItem) {
        Item item = orderItem.getItem();

        if (map.isEmpty()) {
            map.put(item.getId(), orderItem.getQuantity());
        } else {
            int exisitingCount = map.get(item.getId());
            int newCount = exisitingCount + orderItem.getQuantity();

            updateCountMap(item.getId(), map, exisitingCount, newCount);
        }
    }

    public void updateCountMap(String id, Map<String, Integer> map, int oldCount, int newCount) {
        map.replace(id, oldCount, newCount);
    }
}
