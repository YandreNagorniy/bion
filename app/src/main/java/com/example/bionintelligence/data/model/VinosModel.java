package com.example.bionintelligence.data.model;
//Вынос

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = CultureModel.class, parentColumns = "id", childColumns = "cultureId", onDelete = CASCADE))
public class VinosModel {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(index = true)
    private int cultureId;
    @ColumnInfo(name = "vinos_N")
    private double N;
    @ColumnInfo(name = "vinos_P2O5")
    private double P2O5;
    @ColumnInfo(name = "vinos_K2O")
    private double K2O;
    @ColumnInfo(name = "vinos_CaO")
    private double CaO;
    @ColumnInfo(name = "vinos_MgO")
    private double MgO;
    @ColumnInfo(name = "vinos_S")
    private double S;

    public VinosModel(int cultureId, double N, double P2O5, double K2O,
                      double CaO, double MgO, double S) {
        this.cultureId = cultureId;
        this.N = N;
        this.P2O5 = P2O5;
        this.K2O = K2O;
        this.CaO = CaO;
        this.MgO = MgO;
        this.S = S;
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

    public double getN() {
        return N;
    }

    public void setN(double n) {
        N = n;
    }

    public double getP2O5() {
        return P2O5;
    }

    public void setP2O5(double p2O5) {
        P2O5 = p2O5;
    }

    public double getK2O() {
        return K2O;
    }

    public void setK2O(double k2O) {
        K2O = k2O;
    }

    public double getCaO() {
        return CaO;
    }

    public void setCaO(double caO) {
        CaO = caO;
    }

    public double getMgO() {
        return MgO;
    }

    public void setMgO(double mgO) {
        MgO = mgO;
    }

    public double getS() {
        return S;
    }

    public void setS(double s) {
        S = s;
    }
}
