package com.example.bionintelligence.data.repositories;

import android.util.Pair;

import com.example.bionintelligence.data.model.PhasesImgModel;
import com.example.bionintelligence.data.model.ProductiveInfoModel;
import com.example.bionintelligence.data.model.PhasesModel;
import com.example.bionintelligence.data.model.SoilFactorsModel;
import com.example.bionintelligence.data.model.TestCultureModel;
import com.example.bionintelligence.data.pojo.AnalyticalFactors;
import com.example.bionintelligence.data.source.DatabaseSource;
import com.example.bionintelligence.data.source.LocalSource;
import com.example.bionintelligence.data.source.PutDatabaseSource;
import com.example.bionintelligence.domain.entities.CalculateCaOEntity;
import com.example.bionintelligence.domain.entities.CalculateH2OEntity;
import com.example.bionintelligence.domain.entities.CalculateK2OEntity;
import com.example.bionintelligence.domain.entities.CalculateMgOEntity;
import com.example.bionintelligence.domain.entities.CalculateNEntity;
import com.example.bionintelligence.domain.entities.CalculateP2O5Entity;
import com.example.bionintelligence.domain.entities.CalculateSEntity;
import com.example.bionintelligence.domain.entities.CalculatorParams;
import com.example.bionintelligence.domain.repositories.CalculatorRepository;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class CalculatorRepositoryImpl implements CalculatorRepository {
    private LocalSource localSource;
    private DatabaseSource databaseSource;
    private PutDatabaseSource putDatabaseSource;

    public CalculatorRepositoryImpl(LocalSource localSource, DatabaseSource databaseSource) {
        this.localSource = localSource;
        this.databaseSource = databaseSource;
    }

    @Override
    public Single<CalculatorParams> getCalculatorParams() {
        return Single.just(new CalculatorParams(localSource.getSettingsCultureProductive(), localSource.getSettingsCultureId()));
    }

    @Override
    public void setCalculatorParams(CalculatorParams params) {
        localSource.setSettingsCultureProductive(params.getProductive());
        localSource.setSettingsCultureId(params.getCultureId());
    }

    @Override
    public Single<AnalyticalFactors> getAnalyticalFactors() {
        return Single.just(new AnalyticalFactors(localSource.getAnalyticalFactorsN(), localSource.getAnalyticalFactorsP2O5(),
                localSource.getAnalyticalFactorsK2O()));
    }

    @Override
    public void setAnalyticalFactors(AnalyticalFactors analyticalFactors) {
        localSource.setAnalyticalFactorsN(analyticalFactors.getAfN());
        localSource.setAnalyticalFactorsP2O5(analyticalFactors.getAfP2O5());
        localSource.setAnalyticalFactorsK2O(analyticalFactors.getAfK2O());
    }


    //Данные калькулятора
    @Override
    public Single<Pair<Double, CalculateNEntity>> getDataN(int id) {
        return databaseSource.getDataN(id)
                .subscribeOn(Schedulers.io())
                .flatMap(calculateNEntity -> databaseSource.getPhN(calculateNEntity.sf_pH)
                        .zipWith(Single.just(calculateNEntity), Pair::new));
    }

    @Override
    public Single<Pair<Double, CalculateP2O5Entity>> getDataP2O5(int id) {
        return databaseSource.getDataP2O5(id)
                .subscribeOn(Schedulers.io())
                .flatMap(calculateP2O5Entity -> databaseSource.getPhP2O5(calculateP2O5Entity.sf_pH)
                        .zipWith(Single.just(calculateP2O5Entity), Pair::new));
    }

    @Override
    public Single<Pair<Double, CalculateK2OEntity>> getDataK2O(int id) {
        return databaseSource.getDataK2O(id)
                .subscribeOn(Schedulers.io())
                .flatMap(calculateK2OEntity -> databaseSource.getPhK2O(calculateK2OEntity.sf_pH)
                        .zipWith(Single.just(calculateK2OEntity), Pair::new));
    }

    @Override
    public Single<Pair<Double, CalculateCaOEntity>> getDataCaO(int id) {
        return databaseSource.getDataCaO(id)
                .subscribeOn(Schedulers.io())
                .flatMap(calculateCaOEntity -> databaseSource.getPhCaO(calculateCaOEntity.sf_pH)
                        .zipWith(Single.just(calculateCaOEntity), Pair::new));
    }

    @Override
    public Single<Pair<Double, CalculateMgOEntity>> getDataMgO(int id) {
        return databaseSource.getDataMgO(id)
                .subscribeOn(Schedulers.io())
                .flatMap(calculateMgOEntity -> databaseSource.getPhMgO(calculateMgOEntity.sf_pH)
                        .zipWith(Single.just(calculateMgOEntity), Pair::new));
    }

    @Override
    public Single<Pair<Double, CalculateSEntity>> getDataS(int id) {
        return databaseSource.getDataS(id)
                .subscribeOn(Schedulers.io())
                .flatMap(calculateSEntity -> databaseSource.getPhS(calculateSEntity.sf_pH)
                        .zipWith(Single.just(calculateSEntity), Pair::new));
    }

    @Override
    public Single<CalculateH2OEntity> getDataH2O(int id) {
        return databaseSource.getDataH2O(id);
    }


    @Override
    public Single<Double> getTyrinIndexN(double valueN) {
        return databaseSource.getTyrinIndexN(valueN);
    }

    @Override
    public Single<Double> getKornfildIndexN(double valueN) {
        return databaseSource.getKornfildIndexN(valueN);
    }

    @Override
    public Single<Double> getChirikovIndexP2O5(double valueP2O5) {
        return databaseSource.getChirikovIndexP2O5(valueP2O5);
    }

    @Override
    public Single<Double> getKirsanovIndexP2O5(double valueP2O5) {
        return databaseSource.getKirsanovIndexP2O5(valueP2O5);
    }

    @Override
    public Single<Double> getChirikovIndexK2O(double valueK2O) {
        return databaseSource.getChirikovIndexK2O(valueK2O);
    }

    @Override
    public Single<Double> getKirsanovIndexK2O(double valueK2O) {
        return databaseSource.getKirsanovIndexK2O(valueK2O);
    }

    @Override
    public Single<TestCultureModel> getTestCultureModel(int cultureId) {
        return databaseSource.getTestCultureModel(cultureId);
    }

    @Override
    public Single<SoilFactorsModel> getSoilFactorsModel() {
        return databaseSource.getSoilFactorsModel();
    }

    @Override
    public void setSoilFactorsModel(SoilFactorsModel soilFactorsModel) {
        databaseSource.setSoilFactorsModel(soilFactorsModel);
    }
}
