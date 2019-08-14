package com.example.bionintelligence.data.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.bionintelligence.data.model.MethodsK2OModel;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface MethodsK2ODao {

    @Query("SELECT * FROM MethodsK2OModel")
    Flowable<List<MethodsK2OModel>> getList();

    @Query("SELECT * FROM MethodsK2OModel WHERE id IS :id")
    Single<MethodsK2OModel> getById(int id);

    @Query("Select indexKirsanov From MethodsK2OModel where :valueK2O Between kirsanovMin and kirsanovMax")
    Single<Double> getKirsanovIndex(double valueK2O);

    @Query("Select indexChirikov From MethodsK2OModel where :valueK2O Between chirikovMin and chirikovMax")
    Single<Double> getChirikovIndex(double valueK2O);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(MethodsK2OModel methodsK2O);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertList(List<MethodsK2OModel> methodsK2OList);

    @Query("DELETE FROM MethodsK2OModel WHERE id IS :id")
    void deleteById(int id);

}
