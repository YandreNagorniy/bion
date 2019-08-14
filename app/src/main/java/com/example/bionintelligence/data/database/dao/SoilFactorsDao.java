package com.example.bionintelligence.data.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.bionintelligence.data.model.SoilFactorsModel;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface SoilFactorsDao {

    @Query("SELECT * FROM SoilFactorsModel")
    Flowable<List<SoilFactorsModel>> getList();

    @Query("SELECT * FROM SoilFactorsModel WHERE id IS :id")
    Single<SoilFactorsModel> getById(int id);

    @Query("SELECT * FROM SoilFactorsModel")
    Single<SoilFactorsModel> getSoilFactorsModel();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(SoilFactorsModel soilFactorsModel);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertList(List<SoilFactorsModel> soilFactorsModelList);

    @Query("DELETE FROM SoilFactorsModel WHERE id IS :id")
    void deleteById(int id);
}
