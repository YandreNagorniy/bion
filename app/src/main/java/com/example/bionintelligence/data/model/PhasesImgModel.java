package com.example.bionintelligence.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = CultureModel.class, parentColumns = "id", childColumns = "cultureId", onDelete = CASCADE),
        indices = @Index(value = "cultureId"))
public class PhasesImgModel {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int cultureId;
    private int phaseImgOne;
    private int phaseImgTwo;
    private int phaseImgThree;
    private int phaseImgFour;
    private int phaseImgFive;
    private int phaseImgSix;

    public PhasesImgModel(int cultureId, int phaseImgOne, int phaseImgTwo, int phaseImgThree, int phaseImgFour,
                          int phaseImgFive, int phaseImgSix) {
        this.cultureId = cultureId;
        this.phaseImgOne = phaseImgOne;
        this.phaseImgTwo = phaseImgTwo;
        this.phaseImgThree = phaseImgThree;
        this.phaseImgFour = phaseImgFour;
        this.phaseImgFive = phaseImgFive;
        this.phaseImgSix = phaseImgSix;
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

    public int getPhaseImgOne() {
        return phaseImgOne;
    }

    public void setPhaseImgOne(int phaseImgOne) {
        this.phaseImgOne = phaseImgOne;
    }

    public int getPhaseImgTwo() {
        return phaseImgTwo;
    }

    public void setPhaseImgTwo(int phaseImgTwo) {
        this.phaseImgTwo = phaseImgTwo;
    }

    public int getPhaseImgThree() {
        return phaseImgThree;
    }

    public void setPhaseImgThree(int phaseImgThree) {
        this.phaseImgThree = phaseImgThree;
    }

    public int getPhaseImgFour() {
        return phaseImgFour;
    }

    public void setPhaseImgFour(int phaseImgFour) {
        this.phaseImgFour = phaseImgFour;
    }

    public int getPhaseImgFive() {
        return phaseImgFive;
    }

    public void setPhaseImgFive(int phaseImgFive) {
        this.phaseImgFive = phaseImgFive;
    }

    public int getPhaseImgSix() {
        return phaseImgSix;
    }

    public void setPhaseImgSix(int phaseImgSix) {
        this.phaseImgSix = phaseImgSix;
    }
}
