package com.example.bionintelligence.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

//Коэффициенты усвояемости
@Entity(foreignKeys = @ForeignKey(entity = CultureModel.class, parentColumns = "id", childColumns = "cultureId", onDelete = CASCADE),
        indices = @Index(value = "cultureId"))
public class KUsvModel {

    @PrimaryKey(autoGenerate = true)
    private int id;
    //    @ColumnInfo(index = true)
    private int cultureId;
    @ColumnInfo(name = "kusv_N")
    private double N;
    @ColumnInfo(name = "kusv_P2O5")
    private double P2O5;
    @ColumnInfo(name = "kusv_K2O")
    private double K2O;
    @ColumnInfo(name = "kusv_CaO")
    private double CaO;
    @ColumnInfo(name = "kusv_MgO")
    private double MgO;
    @ColumnInfo(name = "kusv_S")
    private double S;
//    @ColumnInfo(name = "kusv_Zn")
//    private double Zn;
//    @ColumnInfo(name = "kusv_Mo")
//    private double Mo;
//    @ColumnInfo(name = "kusv_Cu")
//    private double Cu;
//    @ColumnInfo(name = "kusv_Mn")
//    private double Mn;
//    @ColumnInfo(name = "kusv_Co")
//    private double Co;
//    @ColumnInfo(name = "kusv_B")
//    private double B;
//    @ColumnInfo(name = "kusv_Fe")
//    private double Fe;

    public KUsvModel(int cultureId, double N, double P2O5, double K2O, double CaO, double MgO,
                     double S/*, double Zn, double Mo, double Cu, double Mn, double Co, double B, double Fe*/) {
        this.cultureId = cultureId;
        this.N = N;
        this.P2O5 = P2O5;
        this.K2O = K2O;
        this.CaO = CaO;
        this.MgO = MgO;
        this.S = S;
//        this.Zn = Zn;
//        this.Mo = Mo;
//        this.Cu = Cu;
//        this.Mn = Mn;
//        this.Co = Co;
//        this.B = B;
//        this.Fe = Fe;
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

    public void setN(double N) {
        this.N = N;
    }

    public double getP2O5() {
        return P2O5;
    }

    public void setP2O5(double P2O5) {
        this.P2O5 = P2O5;
    }

    public double getK2O() {
        return K2O;
    }

    public void setK2O(double K2O) {
        this.K2O = K2O;
    }

    public double getCaO() {
        return CaO;
    }

    public void setCaO(double CaO) {
        this.CaO = CaO;
    }

    public double getMgO() {
        return MgO;
    }

    public void setMgO(double MgO) {
        this.MgO = MgO;
    }

    public double getS() {
        return S;
    }

    public void setS(double S) {
        this.S = S;
    }

//    public double getZn() {
//        return Zn;
//    }
//
//    public void setZn(double Zn) {
//        this.Zn = Zn;
//    }
//
//    public double getMo() {
//        return Mo;
//    }
//
//    public void setMo(double Mo) {
//        this.Mo = Mo;
//    }
//
//    public double getCu() {
//        return Cu;
//    }
//
//    public void setCu(double Cu) {
//        this.Cu = Cu;
//    }
//
//    public double getMn() {
//        return Mn;
//    }
//
//    public void setMn(double Mn) {
//        this.Mn = Mn;
//    }
//
//    public double getCo() {
//        return Co;
//    }
//
//    public void setCo(double Co) {
//        this.Co = Co;
//    }
//
//    public double getB() {
//        return B;
//    }
//
//    public void setB(double B) {
//        this.B = B;
//    }
//
//    public double getFe() {
//        return Fe;
//    }
//
//    public void setFe(double Fe) {
//        this.Fe = Fe;
//    }
}
