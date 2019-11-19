package com.bionagro.bionintelligence.data.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.bionagro.bionintelligence.data.model.PhasesModel;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface PhasesDao {

    @Query("SELECT * FROM PhasesModel WHERE cultureId IS :cultureId AND productive is :productive")
    Single<PhasesModel> getPhases(int cultureId, int productive);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(PhasesModel phasesModel);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertList(List<PhasesModel> phasesModelList);
}
