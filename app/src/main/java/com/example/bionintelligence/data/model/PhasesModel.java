package com.example.bionintelligence.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = CultureModel.class, parentColumns = "id", childColumns = "cultureId", onDelete = CASCADE),
        indices = @Index(value = "cultureId"))
public class PhasesModel {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int cultureId;
    private int productive;
    private double phaseOne;
    private double phaseTwo;
    private double phaseThree;
    private double phaseFour;
    private double phaseFive;
    private double phaseSix;

    public PhasesModel(int cultureId, int productive, double phaseOne, double phaseTwo, double phaseThree, double phaseFour,
                       double phaseFive, double phaseSix) {
        this.cultureId = cultureId;
        this.phaseOne = phaseOne;
        this.phaseTwo = phaseTwo;
        this.phaseThree = phaseThree;
        this.phaseFour = phaseFour;
        this.phaseFive = phaseFive;
        this.phaseSix = phaseSix;
        this.productive = productive;
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

    public int getProductive() {
        return productive;
    }

    public void setProductive(int productive) {
        this.productive = productive;
    }

    public double getPhaseOne() {
        return phaseOne;
    }

    public void setPhaseOne(double phaseOne) {
        this.phaseOne = phaseOne;
    }

    public double getPhaseTwo() {
        return phaseTwo;
    }

    public void setPhaseTwo(double phaseTwo) {
        this.phaseTwo = phaseTwo;
    }

    public double getPhaseThree() {
        return phaseThree;
    }

    public void setPhaseThree(double phaseThree) {
        this.phaseThree = phaseThree;
    }

    public double getPhaseFour() {
        return phaseFour;
    }

    public void setPhaseFour(double phaseFour) {
        this.phaseFour = phaseFour;
    }

    public double getPhaseFive() {
        return phaseFive;
    }

    public void setPhaseFive(double phaseFive) {
        this.phaseFive = phaseFive;
    }

    public double getPhaseSix() {
        return phaseSix;
    }

    public void setPhaseSix(double phaseSix) {
        this.phaseSix = phaseSix;
    }
}
