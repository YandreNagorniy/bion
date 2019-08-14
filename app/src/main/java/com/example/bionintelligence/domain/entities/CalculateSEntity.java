package com.example.bionintelligence.domain.entities;

import android.arch.persistence.room.ColumnInfo;

public class CalculateSEntity {
    @ColumnInfo(name = "vinos_S")
    public double vinos_S;
    @ColumnInfo(name = "sf_S")
    public double sf_S;
    @ColumnInfo(name = "sf_pH")
    public double sf_pH;
    @ColumnInfo(name = "kusv_S")
    public double kusv_S;

}