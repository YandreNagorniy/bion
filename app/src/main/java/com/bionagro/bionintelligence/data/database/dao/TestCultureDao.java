package com.bionagro.bionintelligence.data.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.bionagro.bionintelligence.data.model.TestCultureModel;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface TestCultureDao {

//    @Query("SELECT * FROM TestCultureModel")
//    Flowable<List<TestCultureModel>> getList();

    @Query("SELECT * FROM TestCultureModel WHERE cultureId IS :id")
    Single<TestCultureModel> getById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(TestCultureModel testCultureModel);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertList(List<TestCultureModel> testCultureModelList);

    @Query("DELETE FROM TestCultureModel WHERE cultureId IS :id")
    void deleteById(int id);

    @Query("DELETE FROM TestCultureModel")
    void deleteTable();
}
