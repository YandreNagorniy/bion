package com.example.bionintelligence.domain.entities;

import android.arch.persistence.room.ColumnInfo;

public class CalculateP2O5Entity {
    @ColumnInfo(name = "vinos_P2O5")
    public double vinos_P2O5;
    @ColumnInfo(name = "sf_P2O5")
    public double sf_P2O5;
    @ColumnInfo(name = "sf_pH")
    public double sf_pH;
    @ColumnInfo(name = "kusv_P2O5")
    public double kusv_P2O5;

}