package com.example.bionintelligence.data.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.bionintelligence.data.model.ProductiveInfoModel;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface ProductiveInfoDao {
    @Query("SELECT * FROM ProductiveInfoModel WHERE cultureId IS :cultureId")
    Single<ProductiveInfoModel> getPhasesInfo(int cultureId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ProductiveInfoModel productiveInfoModel);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertList(List<ProductiveInfoModel> productiveInfoModelList);
}
