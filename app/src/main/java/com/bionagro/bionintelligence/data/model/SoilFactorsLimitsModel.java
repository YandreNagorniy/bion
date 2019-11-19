package com.bionagro.bionintelligence.data.model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.bionagro.bionintelligence.data.database.converters.MinMaxListLimitConverters;
import com.bionagro.bionintelligence.data.pojo.MinMaxListPojo;
import com.bionagro.bionintelligence.data.pojo.MinMaxPojo;

@Entity
public class SoilFactorsLimitsModel {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @TypeConverters(MinMaxListLimitConverters.class)
    private MinMaxListPojo limitN;
    @TypeConverters(MinMaxListLimitConverters.class)
    private MinMaxListPojo limitP2O5;
    @TypeConverters(MinMaxListLimitConverters.class)
    private MinMaxListPojo limitK2O;
    @Embedded(prefix = "cao")
    private MinMaxPojo limitCaO;
    @Embedded(prefix = "mgo")
    private MinMaxPojo limitMgO;
    @Embedded(prefix = "s")
    private MinMaxPojo limitS;
    @Embedded(prefix = "zn")
    private MinMaxPojo limitZn;
    @Embedded(prefix = "cu")
    private MinMaxPojo limitCu;
    @Embedded(prefix = "mn")
    private MinMaxPojo limitMn;
    @Embedded(prefix = "co")
    private MinMaxPojo limitCo;
    @Embedded(prefix = "mo")
    private MinMaxPojo limitMo;
    @Embedded(prefix = "b")
    private MinMaxPojo limitB;
    @Embedded(prefix = "fe")
    private MinMaxPojo limitFe;
    @Embedded(prefix = "g")
    private MinMaxPojo limitG;
    @Embedded(prefix = "ph")
    private MinMaxPojo limitPH;
    @Embedded(prefix = "zpv")
    private MinMaxPojo limitzpv;

    public SoilFactorsLimitsModel(MinMaxListPojo limitN, MinMaxListPojo limitP2O5, MinMaxListPojo limitK2O,
                                  MinMaxPojo limitCaO, MinMaxPojo limitMgO, MinMaxPojo limitS,
                                  MinMaxPojo limitZn, MinMaxPojo limitCu, MinMaxPojo limitMn,
                                  MinMaxPojo limitCo, MinMaxPojo limitMo, MinMaxPojo limitB, MinMaxPojo limitFe,
                                  MinMaxPojo limitG, MinMaxPojo limitPH, MinMaxPojo limitzpv) {
        this.limitN = limitN;
        this.limitP2O5 = limitP2O5;
        this.limitK2O = limitK2O;
        this.limitCaO = limitCaO;
        this.limitMgO = limitMgO;
        this.limitS = limitS;
        this.limitZn = limitZn;
        this.limitCu = limitCu;
        this.limitMn = limitMn;
        this.limitCo = limitCo;
        this.limitMo = limitMo;
        this.limitB = limitB;
        this.limitFe = limitFe;
        this.limitG = limitG;
        this.limitPH = limitPH;
        this.limitzpv = limitzpv;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MinMaxListPojo getLimitN() {
        return limitN;
    }

    public void setLimitN(MinMaxListPojo limitN) {
        this.limitN = limitN;
    }

    public MinMaxListPojo getLimitP2O5() {
        return limitP2O5;
    }

    public void setLimitP2O5(MinMaxListPojo limitP2O5) {
        this.limitP2O5 = limitP2O5;
    }

    public MinMaxListPojo getLimitK2O() {
        return limitK2O;
    }

    public void setLimitK2O(MinMaxListPojo limitK2O) {
        this.limitK2O = limitK2O;
    }

    public MinMaxPojo getLimitCaO() {
        return limitCaO;
    }

    public void setLimitCaO(MinMaxPojo limitCaO) {
        this.limitCaO = limitCaO;
    }

    public MinMaxPojo getLimitMgO() {
        return limitMgO;
    }

    public void setLimitMgO(MinMaxPojo limitMgO) {
        this.limitMgO = limitMgO;
    }

    public MinMaxPojo getLimitS() {
        return limitS;
    }

    public void setLimitS(MinMaxPojo limitS) {
        this.limitS = limitS;
    }

    public MinMaxPojo getLimitZn() {
        return limitZn;
    }

    public void setLimitZn(MinMaxPojo limitZn) {
        this.limitZn = limitZn;
    }

    public MinMaxPojo getLimitCu() {
        return limitCu;
    }

    public void setLimitCu(MinMaxPojo limitCu) {
        this.limitCu = limitCu;
    }

    public MinMaxPojo getLimitMn() {
        return limitMn;
    }

    public void setLimitMn(MinMaxPojo limitMn) {
        this.limitMn = limitMn;
    }

    public MinMaxPojo getLimitCo() {
        return limitCo;
    }

    public void setLimitCo(MinMaxPojo limitCo) {
        this.limitCo = limitCo;
    }

    public MinMaxPojo getLimitMo() {
        return limitMo;
    }

    public void setLimitMo(MinMaxPojo limitMo) {
        this.limitMo = limitMo;
    }

    public MinMaxPojo getLimitB() {
        return limitB;
    }

    public void setLimitB(MinMaxPojo limitB) {
        this.limitB = limitB;
    }

    public MinMaxPojo getLimitFe() {
        return limitFe;
    }

    public void setLimitFe(MinMaxPojo limitFe) {
        this.limitFe = limitFe;
    }

    public MinMaxPojo getLimitG() {
        return limitG;
    }

    public void setLimitG(MinMaxPojo limitG) {
        this.limitG = limitG;
    }

    public MinMaxPojo getLimitPH() {
        return limitPH;
    }

    public void setLimitPH(MinMaxPojo limitPH) {
        this.limitPH = limitPH;
    }

    public MinMaxPojo getLimitzpv() {
        return limitzpv;
    }

    public void setLimitzpv(MinMaxPojo limitzpv) {
        this.limitzpv = limitzpv;
    }
}
