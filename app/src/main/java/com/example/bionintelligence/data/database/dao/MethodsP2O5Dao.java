package com.example.bionintelligence.data.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.bionintelligence.data.model.MethodsP2O5Model;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface MethodsP2O5Dao {

    @Query("SELECT * FROM MethodsP2O5Model")
    Flowable<List<MethodsP2O5Model>> getList();

    @Query("SELECT * FROM MethodsP2O5Model WHERE id IS :id")
    Single<MethodsP2O5Model> getById(int id);

    @Query("Select indexKirsanov From MethodsP2O5Model where :valueP2O5 Between kirsanovMin and kirsanovMax")
    Single<Double> getKirsanovIndex(double valueP2O5);

    @Query("Select indexChirikov From MethodsP2O5Model where :valueP2O5 Between chirikovMin and chirikovMax")
    Single<Double> getChirikovIndex(double valueP2O5);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(MethodsP2O5Model methodsP2O5);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertList(List<MethodsP2O5Model> methodsP2O5List);

    @Query("DELETE FROM MethodsP2O5Model WHERE id IS :id")
    void deleteById(int id);

//        @Update
//    void update(SettingsRepository user);
}
