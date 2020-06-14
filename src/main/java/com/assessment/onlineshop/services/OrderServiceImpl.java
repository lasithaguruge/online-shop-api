package com.assessment.onlineshop.services;

import com.assessment.onlineshop.dtos.Item;
import com.assessment.onlineshop.dtos.OrderItem;
import com.assessment.onlineshop.utill.ItemCountStoreByUOM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private final ItemService itemService;

    public OrderServiceImpl(ItemService itemService) {
        this.itemService = itemService;
    }

    @Override
    public double calculateOrderTotal(ArrayList<OrderItem> orderItems) {
        List<ItemCountStoreByUOM> itemCountListByUOM = getItemCountListByUOM(orderItems);
        double orderTotal = 0;

        for (int i = 0; i < itemCountListByUOM.size(); i++) {
            ItemCountStoreByUOM itemCountStoreByUOM = itemCountListByUOM.get(i);

            int cartonCount = itemCountStoreByUOM.getCartonCount();
            int unitCount = itemCountStoreByUOM.getItemUnitCount();

            Item item = itemService.findById(itemCountStoreByUOM.getId());

            if (cartonCount >= 3) orderTotal = orderTotal + getDiscountCartonAmount(item) * cartonCount;
            else orderTotal = orderTotal + item.getPrice() * cartonCount;

            orderTotal = orderTotal + getItemPrice(item) * unitCount;
        }

        return orderTotal;
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
    public List<ItemCountStoreByUOM> getItemCountListByUOM(ArrayList<OrderItem> orderItems) {
        Map<String, Integer> cartonItemCountMap = new HashMap<>();
        Map<String, Integer> singleItemCountMap = new HashMap<>();
        List<String> itemIds = new ArrayList<>();

        for (int i = 0; i < orderItems.size(); i++) {
            OrderItem orderItem = orderItems.get(i);
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

                    updateCountMap(itemId, singleItemCountMap, existingSingleItemCount, newSingleItemCount);

                    if (cartonItemCountMap.containsKey(itemId)) {
                        int existingCartonCount = cartonItemCountMap.get(itemId);
                        int newCartonCount = existingCartonCount + cartonCountFromSingleItems;

                        updateCountMap(itemId, cartonItemCountMap, existingCartonCount, newCartonCount);
                    } else {
                        cartonItemCountMap.put(item.getId(), cartonCountFromSingleItems);
                    }
                }
            }
        }

        return getItemCountListByUOM(itemIds, cartonItemCountMap, singleItemCountMap);
    }

    public List<ItemCountStoreByUOM> getItemCountListByUOM(List<String> itemIds, Map<String, Integer> cartonCountMap, Map<String, Integer> singleItemCountMap) {
        List<ItemCountStoreByUOM> countStoreByUOMS = new ArrayList<>();

        itemIds.forEach(id -> {
            int cartonCount = 0;
            int itemCount = 0;

            if (cartonCountMap.containsKey(id)) cartonCount = cartonCountMap.get(id);
            if (singleItemCountMap.containsKey(id)) itemCount = singleItemCountMap.get(id);

            countStoreByUOMS.add(new ItemCountStoreByUOM(id, cartonCount, itemCount));
        });

        return countStoreByUOMS;
    }

    /**
     * This function is update the given map values by matching the order item id with keys of map
     * @param map
     * @param orderItem
     */
    public void updateCountMap(Map<String, Integer> map, OrderItem orderItem) {
        Item item = orderItem.getItem();

        if (map.isEmpty()) {
            map.put(item.getId(), orderItem.getQuantity());
        } else {
            if (map.containsKey(item.getId())) {
                int exisitingCount = map.get(item.getId());
                int newCount = exisitingCount + orderItem.getQuantity();

                updateCountMap(item.getId(), map, exisitingCount, newCount);
            } else {
                map.put(item.getId(), orderItem.getQuantity());
            }
        }
    }

    public void updateCountMap(String id, Map<String, Integer> map, int oldCount, int newCount) {
        map.replace(id, oldCount, newCount);
    }
}
