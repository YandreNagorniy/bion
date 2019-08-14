package com.example.bionintelligence.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

//pH
@Entity
public class PHModel {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "pH_pH")
    private double PH;
    @ColumnInfo(name = "pH_N")
    private double N;
    @ColumnInfo(name = "pH_P2O5")
    private double P2O5;
    @ColumnInfo(name = "pH_K2O")
    private double K2O;
    @ColumnInfo(name = "pH_CaO")
    private double CaO;
    @ColumnInfo(name = "pH_MgO")
    private double MgO;
    @ColumnInfo(name = "pH_S")
    private double S;
    @ColumnInfo(name = "pH_Zn")
    private double Zn;
    @ColumnInfo(name = "pH_Mo")
    private double Mo;
    @ColumnInfo(name = "pH_Cu")
    private double Cu;
    @ColumnInfo(name = "pH_Mn")
    private double Mn;
    @ColumnInfo(name = "pH_Co")
    private double Co;
    @ColumnInfo(name = "pH_B")
    private double B;
    @ColumnInfo(name = "pH_Fe")
    private double Fe;

    public PHModel(double PH, double N, double P2O5, double K2O, double CaO, double MgO, double S,
                   double Zn, double Mo, double Cu, double Mn, double Co, double B, double Fe) {
        this.PH = PH;
        this.N = N;
        this.P2O5 = P2O5;
        this.K2O = K2O;
        this.CaO = CaO;
        this.MgO = MgO;
        this.S = S;
        this.Zn = Zn;
        this.Mo = Mo;
        this.Cu = Cu;
        this.Mn = Mn;
        this.Co = Co;
        this.B = B;
        this.Fe = Fe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPH() {
        return PH;
    }

    public void setPH(double PH) {
        this.PH = PH;
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

    public double getZn() {
        return Zn;
    }

    public void setZn(double zn) {
        Zn = zn;
    }

    public double getMo() {
        return Mo;
    }

    public void setMo(double mo) {
        Mo = mo;
    }

    public double getCu() {
        return Cu;
    }

    public void setCu(double cu) {
        Cu = cu;
    }

    public double getMn() {
        return Mn;
    }

    public void setMn(double mn) {
        Mn = mn;
    }

    public double getCo() {
        return Co;
    }

    public void setCo(double co) {
        Co = co;
    }

    public double getB() {
        return B;
    }

    public void setB(double b) {
        B = b;
    }

    public double getFe() {
        return Fe;
    }

    public void setFe(double fe) {
        Fe = fe;
    }
}
