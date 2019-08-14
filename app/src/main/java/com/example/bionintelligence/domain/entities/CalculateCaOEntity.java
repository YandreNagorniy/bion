package com.example.bionintelligence.domain.entities;

import android.arch.persistence.room.ColumnInfo;

public class CalculateCaOEntity {
    @ColumnInfo(name = "vinos_CaO")
    public double vinos_CaO;
    @ColumnInfo(name = "sf_CaO")
    public double sf_CaO;
    @ColumnInfo(name = "sf_pH")
    public double sf_pH;
    @ColumnInfo(name = "kusv_CaO")
    public double kusv_CaO;

}