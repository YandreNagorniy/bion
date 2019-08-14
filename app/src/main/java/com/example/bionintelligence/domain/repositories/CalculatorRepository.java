package com.example.bionintelligence.domain.repositories;

import android.util.Pair;

import com.example.bionintelligence.data.model.PhasesImgModel;
import com.example.bionintelligence.data.model.ProductiveInfoModel;
import com.example.bionintelligence.data.model.PhasesModel;
import com.example.bionintelligence.data.model.SoilFactorsModel;
import com.example.bionintelligence.data.model.TestCultureModel;
import com.example.bionintelligence.data.pojo.AnalyticalFactors;
import com.example.bionintelligence.domain.entities.CalculateCaOEntity;
import com.example.bionintelligence.domain.entities.CalculateH2OEntity;
import com.example.bionintelligence.domain.entities.CalculateK2OEntity;
import com.example.bionintelligence.domain.entities.CalculateMgOEntity;
import com.example.bionintelligence.domain.entities.CalculateNEntity;
import com.example.bionintelligence.domain.entities.CalculateP2O5Entity;
import com.example.bionintelligence.domain.entities.CalculateSEntity;
import com.example.bionintelligence.domain.entities.CalculatorParams;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface CalculatorRepository {

    Single<CalculatorParams> getCalculatorParams();

    void setCalculatorParams(CalculatorParams params);

    Single<AnalyticalFactors> getAnalyticalFactors();

    void setAnalyticalFactors(AnalyticalFactors analyticalFactors);

    Single<Pair<Double, CalculateNEntity>> getDataN(int id);

    Single<Pair<Double, CalculateP2O5Entity>> getDataP2O5(int id);

    Single<Pair<Double, CalculateK2OEntity>> getDataK2O(int id);

    Single<Pair<Double, CalculateCaOEntity>> getDataCaO(int id);

    Single<Pair<Double, CalculateMgOEntity>> getDataMgO(int id);

    Single<Pair<Double, CalculateSEntity>> getDataS(int id);

    Single<CalculateH2OEntity> getDataH2O(int id);

    Single<Double> getTyrinIndexN(double valueN);

    Single<Double> getKornfildIndexN(double valueN);

    Single<Double> getChirikovIndexP2O5(double valueP2O5);

    Single<Double> getKirsanovIndexP2O5(double valueP2O5);

    Single<Double> getChirikovIndexK2O(double valueK2O);

    Single<Double> getKirsanovIndexK2O(double valueK2O);

    Single<TestCultureModel> getTestCultureModel(int cultureId);

    Single<SoilFactorsModel> getSoilFactorsModel();

    void setSoilFactorsModel(SoilFactorsModel soilFactorsModel);
}
