package com.bionagro.bionintelligence.data.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.bionagro.bionintelligence.data.model.WaterConsumptionModel;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface WaterConsumptionDao {

    @Query("SELECT * FROM WaterConsumptionModel")
    Flowable<List<WaterConsumptionModel>> getList();

    @Query("SELECT * FROM WaterConsumptionModel WHERE id IS :id")
    Single<WaterConsumptionModel> getById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(WaterConsumptionModel waterConsumptionModel);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertList(List<WaterConsumptionModel> waterConsumptionModelList);

    @Query("DELETE FROM WaterConsumptionModel WHERE id IS :id")
    void deleteById(int id);

    @Query("DELETE FROM WaterConsumptionModel")
    void deleteTable();
}
