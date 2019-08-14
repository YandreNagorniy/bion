package com.example.bionintelligence.data.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.bionintelligence.data.model.MethodsNModel;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface MethodsNDao {

    @Query("SELECT * FROM MethodsNModel")
    Flowable<List<MethodsNModel>> getList();

    @Query("SELECT * FROM MethodsNModel WHERE id IS :id")
    Single<MethodsNModel> getById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(MethodsNModel methodsN);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertList(List<MethodsNModel> methodsNList);

    @Query("Select indexTyrin From MethodsNModel where :valueN Between tyrinMin and tyrinMax")
    Single<Double> getTyrinIndex(double valueN);

    @Query("Select indexKornfild From MethodsNModel where :valueN Between kornfildMin and kornfildMax")
    Single<Double> getKornfildIndex(double valueN);

    @Query("DELETE FROM MethodsNModel WHERE id IS :id")
    void deleteById(int id);
}
