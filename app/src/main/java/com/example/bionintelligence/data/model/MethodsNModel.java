package com.example.bionintelligence.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class MethodsNModel {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String grade;
    private double tyrinMin;
    private double tyrinMax;
    private double kornfildMin;
    private double kornfildMax;
    private double mineralMin;
    private double mineralMax;
    private double indexTyrin;
    private double indexKornfild;

    public MethodsNModel(String grade, double tyrinMin, double tyrinMax, double kornfildMin,
                         double kornfildMax, double mineralMin, double mineralMax,
                         double indexTyrin, double indexKornfild) {
        this.grade = grade;
        this.tyrinMin = tyrinMin;
        this.tyrinMax = tyrinMax;
        this.kornfildMin = kornfildMin;
        this.kornfildMax = kornfildMax;
        this.mineralMin = mineralMin;
        this.mineralMax = mineralMax;
        this.indexTyrin = indexTyrin;
        this.indexKornfild = indexKornfild;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public double getTyrinMin() {
        return tyrinMin;
    }

    public void setTyrinMin(double tyrinMin) {
        this.tyrinMin = tyrinMin;
    }

    public double getTyrinMax() {
        return tyrinMax;
    }

    public void setTyrinMax(double tyrinMax) {
        this.tyrinMax = tyrinMax;
    }

    public double getKornfildMin() {
        return kornfildMin;
    }

    public void setKornfildMin(double kornfildMin) {
        this.kornfildMin = kornfildMin;
    }

    public double getKornfildMax() {
        return kornfildMax;
    }

    public void setKornfildMax(double kornfildMax) {
        this.kornfildMax = kornfildMax;
    }

    public double getMineralMin() {
        return mineralMin;
    }

    public void setMineralMin(double mineralMin) {
        this.mineralMin = mineralMin;
    }

    public double getMineralMax() {
        return mineralMax;
    }

    public void setMineralMax(double mineralMax) {
        this.mineralMax = mineralMax;
    }

    public double getIndexTyrin() {
        return indexTyrin;
    }

    public void setIndexTyrin(double indexTyrin) {
        this.indexTyrin = indexTyrin;
    }

    public double getIndexKornfild() {
        return indexKornfild;
    }

    public void setIndexKornfild(double indexKornfild) {
        this.indexKornfild = indexKornfild;
    }


}

