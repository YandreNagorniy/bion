package com.example.bionintelligence.domain.entities;

public class CalculatorParams {
    private int productive;
    private int cultureId;

    public CalculatorParams(int productive, int cultureId) {
        this.productive = productive;
        this.cultureId = cultureId;
    }

    public int getProductive() {
        return productive;
    }

    public void setProductive(int productive) {
        this.productive = productive;
    }

    public int getCultureId() {
        return cultureId;
    }

    public void setCultureId(int cultureId) {
        this.cultureId = cultureId;
    }

}

