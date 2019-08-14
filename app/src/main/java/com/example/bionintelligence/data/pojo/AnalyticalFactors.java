package com.example.bionintelligence.data.pojo;

public class AnalyticalFactors {
    private int afN;
    private int afP2O5;
    private int afK2O;

    public AnalyticalFactors(int afN, int afP2O5, int afK2O) {
        this.afN = afN;
        this.afP2O5 = afP2O5;
        this.afK2O = afK2O;
    }

    public int getAfN() {
        return afN;
    }

    public void setAfN(int afN) {
        this.afN = afN;
    }

    public int getAfP2O5() {
        return afP2O5;
    }

    public void setAfP2O5(int afP2O5) {
        this.afP2O5 = afP2O5;
    }

    public int getAfK2O() {
        return afK2O;
    }

    public void setAfK2O(int afK2O) {
        this.afK2O = afK2O;
    }
}
