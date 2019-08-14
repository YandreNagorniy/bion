package com.example.bionintelligence.data.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.bionintelligence.data.model.PHModel;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface PHDao {

    @Query("SELECT * FROM PHModel")
    Flowable<List<PHModel>> getList();

    @Query("SELECT * FROM PHModel WHERE id IS :id")
    Single<PHModel> getById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(PHModel phModel);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertList(List<PHModel> phModelList);

    @Query("DELETE FROM PHModel WHERE id IS :id")
    void deleteById(int id);


//        @Update
//    void update(SettingsRepository user);
}
