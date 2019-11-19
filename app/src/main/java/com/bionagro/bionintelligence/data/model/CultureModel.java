package com.bionagro.bionintelligence.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class CultureModel {
    @PrimaryKey(autoGenerate = false)
    private int id;
    private String cultureName;
    private int imgLink;


    public CultureModel(int id, String cultureName, int imgLink) {
        this.id= id;
        this.cultureName = cultureName;
        this.imgLink = imgLink;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCultureName() {
        return cultureName;
    }

    public void setCultureName(String cultureName) {
        this.cultureName = cultureName;
    }

    public int getImgLink() {
        return imgLink;
    }

    public void setImgLink(int imgLink) {
        this.imgLink = imgLink;
    }

}

