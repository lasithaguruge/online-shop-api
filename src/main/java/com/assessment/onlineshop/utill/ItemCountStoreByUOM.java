package com.assessment.onlineshop.utill;

import java.util.ArrayList;
import java.util.Map;

public class ItemCountStoreByUOM {
    private String id;
    private Map<String, Integer> countByUOM;

    public ItemCountStoreByUOM() {
    }

    public ItemCountStoreByUOM(String id, Map<String, Integer> countByUOM) {
        this.id = id;
        this.countByUOM = countByUOM;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, Integer> getCountByUOM() {
        return countByUOM;
    }

    public void setCountByUOM(Map<String, Integer> countByUOM) {
        this.countByUOM = countByUOM;
    }
}
