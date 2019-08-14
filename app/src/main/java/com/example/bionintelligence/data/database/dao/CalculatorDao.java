package com.example.bionintelligence.data.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.bionintelligence.data.model.CalculatorModel;
import com.example.bionintelligence.domain.entities.CalculateCaOEntity;
import com.example.bionintelligence.domain.entities.CalculateH2OEntity;
import com.example.bionintelligence.domain.entities.CalculateK2OEntity;
import com.example.bionintelligence.domain.entities.CalculateMgOEntity;
import com.example.bionintelligence.domain.entities.CalculateNEntity;
import com.example.bionintelligence.domain.entities.CalculateP2O5Entity;
import com.example.bionintelligence.domain.entities.CalculateSEntity;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface CalculatorDao {

    @Query("SELECT * FROM CalculatorModel")
    Flowable<List<CalculatorModel>> getAllCalculatorDate();

    @Query("SELECT * FROM CalculatorModel WHERE id IS :id")
    Single<CalculatorModel> getCalculatorDate(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CalculatorModel calculatorModel);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertList(List<CalculatorModel> calculatorModelList);

    @Query("SELECT v.vinos_N as vinos_N, s.sf_N as sf_N, s.sf_pH as sf_pH, s.sf_G as sf_G, k.kusv_N as kusv_N " +
            "FROM VinosModel v, SoilFactorsModel s, KUsvModel k  " +
            "WHERE v.id = :id and k.id = :id")
    Single<CalculateNEntity> getDataN(int id);

    @Query("SELECT v.vinos_P2O5, s.sf_P2O5, s.sf_pH, k.kusv_P2O5 " +
            "FROM VinosModel v, SoilFactorsModel s, KUsvModel k  " +
            "WHERE v.id = :id and k.id = :id")
    Single<CalculateP2O5Entity> getDataP2O5(int id);

    @Query("SELECT v.vinos_K2O, s.sf_K2O, s.sf_pH, k.kusv_K2O " +
            "FROM VinosModel v, SoilFactorsModel s, KUsvModel k  " +
            "WHERE v.id = :id and k.id = :id")
    Single<CalculateK2OEntity> getDataK2O(int id);

    @Query("SELECT v.vinos_CaO, s.sf_CaO, s.sf_pH, k.kusv_CaO " +
            "FROM VinosModel v, SoilFactorsModel s, KUsvModel k  " +
            "WHERE v.id = :id and k.id = :id")
    Single<CalculateCaOEntity> getDataCaO(int id);

    @Query("SELECT v.vinos_MgO, s.sf_MgO, s.sf_pH, k.kusv_MgO " +
            "FROM VinosModel v, SoilFactorsModel s, KUsvModel k  " +
            "WHERE v.id = :id and k.id = :id")
    Single<CalculateMgOEntity> getDataMgO(int id);

    @Query("SELECT v.vinos_S, s.sf_S, s.sf_pH, k.kusv_S " +
            "FROM VinosModel v, SoilFactorsModel s, KUsvModel k  " +
            "WHERE v.id = :id and k.id = :id")
    Single<CalculateSEntity> getDataS(int id);

    @Query("SELECT s.sf_zpv, w.value as waterConsumption_value " +
            "FROM SoilFactorsModel s, WaterConsumptionModel w " +
            "Where w.id = :id")
    Single<CalculateH2OEntity> getDataH2O(int id);


    @Query("Select pH_N From PHModel where pH_pH is :sf_pH")
    Single<Double> getPhN(double sf_pH);

    @Query("Select pH_P2O5 From PHModel where pH_pH is :sf_pH")
    Single<Double> getPhP2O5(double sf_pH);

    @Query("Select pH_K2O From PHModel where pH_pH is :sf_pH")
    Single<Double> getPhK2O(double sf_pH);

    @Query("Select pH_CaO From PHModel where pH_pH is :sf_pH")
    Single<Double> getPhCaO(double sf_pH);

    @Query("Select pH_MgO From PHModel where pH_pH is :sf_pH")
    Single<Double> getPhMgO(double sf_pH);

    @Query("Select pH_S From PHModel where pH_pH is :sf_pH")
    Single<Double> getPhS(double sf_pH);

}
