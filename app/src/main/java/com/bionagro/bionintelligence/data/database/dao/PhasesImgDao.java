package com.bionagro.bionintelligence.data.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.bionagro.bionintelligence.data.model.PhasesImgModel;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface PhasesImgDao {

    @Query("SELECT * FROM PhasesImgModel WHERE cultureId IS :cultureId")
    Single<PhasesImgModel> getPhaseImgByCultureId(int cultureId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(PhasesImgModel phasesImgModel);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertList(List<PhasesImgModel> phasesImgModels);
}
