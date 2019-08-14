package com.example.bionintelligence.data.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.bionintelligence.data.model.PrecipitationRequirementsModel;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface PrecipitationRequirementsDao {

    @Query("SELECT * FROM PrecipitationRequirementsModel")
    Flowable<List<PrecipitationRequirementsModel>> getList();

    @Query("SELECT * FROM PrecipitationRequirementsModel WHERE id IS :id")
    Single<PrecipitationRequirementsModel> getById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(PrecipitationRequirementsModel precipitationRequirementsModel);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertList(List<PrecipitationRequirementsModel> precipitationRequirementsList);

    @Query("DELETE FROM PrecipitationRequirementsModel WHERE id IS :id")
    void deleteById(int id);
}
