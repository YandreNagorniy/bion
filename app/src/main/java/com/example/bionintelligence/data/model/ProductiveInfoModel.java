package com.example.bionintelligence.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = CultureModel.class, parentColumns = "id", childColumns = "cultureId", onDelete = CASCADE),
        indices = @Index(value = "cultureId"))
public class ProductiveInfoModel {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int cultureId;
    private int productiveStep;
    private int productiveMin;
    private int productiveMax;

    public ProductiveInfoModel(int cultureId, int productiveStep, int productiveMin, int productiveMax) {
        this.cultureId = cultureId;
        this.productiveStep = productiveStep;
        this.productiveMin = productiveMin;
        this.productiveMax = productiveMax;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCultureId() {
        return cultureId;
    }

    public void setCultureId(int cultureId) {
        this.cultureId = cultureId;
    }

    public int getProductiveStep() {
        return productiveStep;
    }

    public void setProductiveStep(int productiveStep) {
        this.productiveStep = productiveStep;
    }

    public int getProductiveMin() {
        return productiveMin;
    }

    public void setProductiveMin(int productiveMin) {
        this.productiveMin = productiveMin;
    }

    public int getProductiveMax() {
        return productiveMax;
    }

    public void setProductiveMax(int productiveMax) {
        this.productiveMax = productiveMax;
    }
}
