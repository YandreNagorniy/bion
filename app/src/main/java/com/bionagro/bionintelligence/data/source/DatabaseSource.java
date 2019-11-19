package com.bionagro.bionintelligence.data.source;

import com.bionagro.bionintelligence.data.model.CultureModel;
import com.bionagro.bionintelligence.data.model.SoilFactorsLimitsModel;
import com.bionagro.bionintelligence.data.model.SoilFactorsModel;
import com.bionagro.bionintelligence.data.model.TestCultureModel;
import com.bionagro.bionintelligence.domain.entities.CalculateCaOEntity;
import com.bionagro.bionintelligence.domain.entities.CalculateH2OEntity;
import com.bionagro.bionintelligence.domain.entities.CalculateK2OEntity;
import com.bionagro.bionintelligence.domain.entities.CalculateMgOEntity;
import com.bionagro.bionintelligence.domain.entities.CalculateNEntity;
import com.bionagro.bionintelligence.domain.entities.CalculateP2O5Entity;
import com.bionagro.bionintelligence.domain.entities.CalculateSEntity;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

public interface DatabaseSource {

    Single<CalculateNEntity> getDataN(int id);

    Single<Double> getPhN(double settingsPH);

    Single<CalculateP2O5Entity> getDataP2O5(int id);

    Single<Double> getPhP2O5(double sf_pH);

    Single<CalculateK2OEntity> getDataK2O(int id);

    Single<Double> getPhK2O(double sf_pH);

    Single<CalculateCaOEntity> getDataCaO(int id);

    Single<Double> getPhCaO(double sf_pH);

    Single<CalculateMgOEntity> getDataMgO(int id);

    Single<Double> getPhMgO(double sf_pH);

    Single<CalculateSEntity> getDataS(int id);

    Single<Double> getPhS(double sf_pH);

    Single<CalculateH2OEntity> getDataH2O(int id);

    Single<Double> getTyrinIndexN(double valueN);

    Single<Double> getKornfildIndexN(double valueN);

    Single<Double> getChirikovIndexP2O5(double valueP2O5);

    Single<Double> getKirsanovIndexP2O5(double valueP2O5);

    Single<Double> getChirikovIndexK2O(double valueP2O5);

    Single<Double> getKirsanovIndexK2O(double valueP2O5);

    Flowable<List<CultureModel>> getCultureList();

    Single<TestCultureModel> getTestCultureModel(int cultureId);

    Single<SoilFactorsModel> getSoilFactorsModel();

    void setSoilFactorsModel(SoilFactorsModel soilFactorsModel);

    Single<SoilFactorsLimitsModel> getSoilFactorsLimitsModel();
}
