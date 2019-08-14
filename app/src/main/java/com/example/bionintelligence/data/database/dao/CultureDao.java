package com.example.bionintelligence.data.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.bionintelligence.data.model.CultureModel;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface CultureDao {

    @Query("SELECT * FROM CultureModel")
    Flowable<List<CultureModel>> getList();

    @Query("SELECT * FROM CultureModel WHERE id IS :id")
    Single<CultureModel> getBuId(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CultureModel cultureModel);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertList(List<CultureModel> cultureModelList);

    @Query("DELETE FROM CultureModel WHERE id IS :id")
    void deleteById(int id);
}
