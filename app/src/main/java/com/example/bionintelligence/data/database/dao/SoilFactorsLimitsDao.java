package com.example.bionintelligence.data.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.bionintelligence.data.model.SoilFactorsLimitsModel;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface SoilFactorsLimitsDao {

    @Query("SELECT * FROM SoilFactorsLimitsModel")
    Single<SoilFactorsLimitsModel> getSoilFactorsLimit();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(SoilFactorsLimitsModel soilFactorsLimitsModel);

}
