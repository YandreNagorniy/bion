package com.bionagro.bionintelligence.data.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.bionagro.bionintelligence.data.model.SoilFactorsLimitsModel;

import io.reactivex.Single;

@Dao
public interface SoilFactorsLimitsDao {

    @Query("SELECT * FROM SoilFactorsLimitsModel")
    Single<SoilFactorsLimitsModel> getSoilFactorsLimit();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(SoilFactorsLimitsModel soilFactorsLimitsModel);

    @Query("DELETE FROM SoilFactorsLimitsModel")
    void deleteTable();
}
