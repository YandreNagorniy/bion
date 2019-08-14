package com.example.bionintelligence.domain.entities;

import android.arch.persistence.room.ColumnInfo;

public class CalculateMgOEntity {
    @ColumnInfo(name = "vinos_MgO")
    public double vinos_MgO;
    @ColumnInfo(name = "sf_MgO")
    public double sf_MgO;
    @ColumnInfo(name = "sf_pH")
    public double sf_pH;
    @ColumnInfo(name = "kusv_MgO")
    public double kusv_MgO;

}