package com.bionagro.bionintelligence.data.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.bionagro.bionintelligence.data.model.KUsvModel;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface KUsvDao {

    @Query("SELECT * FROM KUsvModel")
    Flowable<List<KUsvModel>> getList();

    @Query("SELECT * FROM KUsvModel WHERE id IS :id")
    Single<KUsvModel> getById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(KUsvModel kUsvModel);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertList(List<KUsvModel> kUsvModelList);

    @Query("DELETE FROM KUsvModel WHERE id IS :id")
    void deleteById(int id);

    @Query("DELETE FROM KUsvModel")
    void deleteTable();
}
