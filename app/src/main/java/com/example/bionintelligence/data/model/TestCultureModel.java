package com.example.bionintelligence.data.model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.example.bionintelligence.data.database.converters.PhasesImgConverters;
import com.example.bionintelligence.data.database.converters.TestCultureConverters;

import java.util.List;

@Entity
public class TestCultureModel {
    @PrimaryKey
    private int cultureId;
    private String cultureName;
    private int imgLink;
    private int productiveMax;
    private int productive;
    private int productiveMin;
    private int productiveStep;
    @Embedded
    private TestPhasesImgModel phasesImgModel;
    @TypeConverters(TestCultureConverters.class)
    private List<TestPhasesModel> phasesModelList;

    public TestCultureModel(int cultureId, String cultureName, int productiveMin, int productive, int productiveMax, int productiveStep, int imgLink,
                            List<TestPhasesModel> phasesModelList, TestPhasesImgModel phasesImgModel) {
        this.cultureId = cultureId;
        this.cultureName = cultureName;
        this.productiveMin = productiveMin;
        this.productive= productive;
        this.productiveMax = productiveMax;
        this.productiveStep = productiveStep;
        this.imgLink = imgLink;
        this.phasesModelList = phasesModelList;
        this.phasesImgModel = phasesImgModel;
    }

    public int getCultureId() {
        return cultureId;
    }

    public void setCultureId(int cultureId) {
        this.cultureId = cultureId;
    }

    public String getCultureName() {
        return cultureName;
    }

    public void setCultureName(String cultureName) {
        this.cultureName = cultureName;
    }

    public int getProductiveStep() {
        return productiveStep;
    }

    public void setProductiveStep(int productiveStep) {
        this.productiveStep = productiveStep;
    }

    public int getImgLink() {
        return imgLink;
    }

    public void setImgLink(int imgLink) {
        this.imgLink = imgLink;
    }

    public List<TestPhasesModel> getPhasesModelList() {
        return phasesModelList;
    }

    public void setPhasesModelList(List<TestPhasesModel> phasesModelList) {
        this.phasesModelList = phasesModelList;
    }

    public TestPhasesImgModel getPhasesImgModel() {
        return phasesImgModel;
    }

    public void setPhasesImgModel(TestPhasesImgModel phasesImgModel) {
        this.phasesImgModel = phasesImgModel;
    }

    public int getProductive() {
        return productive;
    }

    public void setProductive(int productive) {
        this.productive = productive;
    }

    public int getProductiveMax() {
        return productiveMax;
    }

    public void setProductiveMax(int productiveMax) {
        this.productiveMax = productiveMax;
    }

    public int getProductiveMin() {
        return productiveMin;
    }

    public void setProductiveMin(int productiveMin) {
        this.productiveMin = productiveMin;
    }
}

