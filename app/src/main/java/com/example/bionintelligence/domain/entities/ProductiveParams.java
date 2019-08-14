package com.example.bionintelligence.domain.entities;

public class ProductiveParams {
    private int cultureId;
    private String itemName;
    private int newValue;
    private int productive;

    public ProductiveParams(int cultureId, String itemName, int newValue, int productive) {
        this.cultureId = cultureId;
        this.itemName = itemName;
        this.newValue = newValue;
        this.productive = productive;
    }

    public int getCultureId() {
        return cultureId;
    }

    public void setCultureId(int cultureId) {
        this.cultureId = cultureId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getNewValue() {
        return newValue;
    }

    public void setNewValue(int newValue) {
        this.newValue = newValue;
    }

    public int getProductive() {
        return productive;
    }

    public void setProductive(int productive) {
        this.productive = productive;
    }
}
