package com.example.bionintelligence.domain.entities;

import android.arch.persistence.room.ColumnInfo;

public class CalculateK2OEntity {
    @ColumnInfo(name = "vinos_K2O")
    public double vinos_K2O;
    @ColumnInfo(name = "sf_K2O")
    public double sf_K2O;
    @ColumnInfo(name = "sf_pH")
    public double sf_pH;
    @ColumnInfo(name = "kusv_K2O")
    public double kusv_K2O;

}