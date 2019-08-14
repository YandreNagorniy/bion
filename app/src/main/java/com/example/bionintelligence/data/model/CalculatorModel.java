package com.example.bionintelligence.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class CalculatorModel {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int N;
    private int P2O5;
    private int K2O;
    private int CaO;
    private int MgO;
    private int S;
    private int H2O;
    private int H2O2;

//    private double zpv;
//    private double productive;   //урожайность
//    private double humidityOfSoil;     //влажность почвы

    @Ignore
    public CalculatorModel() {
    }

    public CalculatorModel(int N, int P2O5, int K2O, int CaO, int MgO,
                           int S, int H2O/*, double humidityOfSoil, double zpv, double productive*/) {
        this.N = N;
        this.P2O5 = P2O5;
        this.K2O = K2O;
        this.CaO = CaO;
        this.MgO = MgO;
        this.S = S;
        this.H2O = H2O;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getN() {
        return N;
    }

    public void setN(int N) {
        this.N = N;
    }

    public int getP2O5() {
        return P2O5;
    }

    public void setP2O5(int P2O5) {
        this.P2O5 = P2O5;
    }

    public int getK2O() {
        return K2O;
    }

    public void setK2O(int K2O) {
        this.K2O = K2O;
    }

    public int getCaO() {
        return CaO;
    }

    public void setCaO(int CaO) {
        this.CaO = CaO;
    }

    public int getMgO() {
        return MgO;
    }

    public void setMgO(int MgO) {
        this.MgO = MgO;
    }

    public int getS() {
        return S;
    }

    public void setS(int S) {
        this.S = S;
    }

    public int getH2O() {
        return H2O;
    }

    public void setH2O(int H2O) {
        this.H2O = H2O;
    }

    public int getH2O2() {
        return H2O2;
    }

    public void setH2O2(int h2O2) {
        H2O2 = h2O2;
    }
}

