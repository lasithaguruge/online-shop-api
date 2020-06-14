package com.assessment.onlineshop.utill;

public class ItemCountStoreByUOM {
    private String id;
    private int cartonCount;
    private int itemUnitCount;

    public ItemCountStoreByUOM() {
    }

    public ItemCountStoreByUOM(String id, int cartonCount, int itemUnitCount) {
        this.id = id;
        this.cartonCount = cartonCount;
        this.itemUnitCount = itemUnitCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCartonCount() {
        return cartonCount;
    }

    public void setCartonCount(int cartonCount) {
        this.cartonCount = cartonCount;
    }

    public int getItemUnitCount() {
        return itemUnitCount;
    }

    public void setItemUnitCount(int itemUnitCount) {
        this.itemUnitCount = itemUnitCount;
    }
}
