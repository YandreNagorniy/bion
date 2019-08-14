package com.example.bionintelligence.data.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.bionintelligence.data.model.CalculatorModel;
import com.example.bionintelligence.data.model.SoilFactorsLimit;
import com.example.bionintelligence.domain.entities.CalculateCaOEntity;
import com.example.bionintelligence.domain.entities.CalculateH2OEntity;
import com.example.bionintelligence.domain.entities.CalculateK2OEntity;
import com.example.bionintelligence.domain.entities.CalculateMgOEntity;
import com.example.bionintelligence.domain.entities.CalculateNEntity;
import com.example.bionintelligence.domain.entities.CalculateP2O5Entity;
import com.example.bionintelligence.domain.entities.CalculateSEntity;
import com.example.bionintelligence.domain.entities.SfMethodsLimitsEntity;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface SoilFactorsLimitDao {

    @Query("SELECT * FROM SoilFactorsLimit")
    Flowable<SoilFactorsLimit> getSoilFactorsLimit();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(SoilFactorsLimit soilFactorsLimit);

}
