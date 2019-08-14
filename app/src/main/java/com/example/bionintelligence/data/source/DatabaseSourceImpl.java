package com.example.bionintelligence.data.source;

import com.example.bionintelligence.App;
import com.example.bionintelligence.data.database.dao.CalculatorDao;
import com.example.bionintelligence.data.database.dao.CultureDao;
import com.example.bionintelligence.data.database.dao.MethodsK2ODao;
import com.example.bionintelligence.data.database.dao.MethodsNDao;
import com.example.bionintelligence.data.database.dao.MethodsP2O5Dao;
import com.example.bionintelligence.data.database.dao.SoilFactorsDao;
import com.example.bionintelligence.data.database.dao.TestCultureDao;
import com.example.bionintelligence.data.model.CultureModel;
import com.example.bionintelligence.data.model.SoilFactorsModel;
import com.example.bionintelligence.data.model.TestCultureModel;
import com.example.bionintelligence.domain.entities.CalculateCaOEntity;
import com.example.bionintelligence.domain.entities.CalculateH2OEntity;
import com.example.bionintelligence.domain.entities.CalculateK2OEntity;
import com.example.bionintelligence.domain.entities.CalculateMgOEntity;
import com.example.bionintelligence.domain.entities.CalculateNEntity;
import com.example.bionintelligence.domain.entities.CalculateP2O5Entity;
import com.example.bionintelligence.domain.entities.CalculateSEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class DatabaseSourceImpl implements DatabaseSource {
    private CalculatorDao calculatorDao;
    private CultureDao cultureDao;
    private TestCultureDao testCultureDao;
    private SoilFactorsDao soilFactorsDao;
    private MethodsNDao methodsNDao;
    private MethodsP2O5Dao methodsP2O5Dao;
    private MethodsK2ODao methodsK2ODao;

    public DatabaseSourceImpl() {
        calculatorDao = App.getInstance().getDatabase().calculatorDao();
        cultureDao = App.getInstance().getDatabase().cultureDao();
        testCultureDao = App.getInstance().getDatabase().testCultureDao();
        soilFactorsDao = App.getInstance().getDatabase().soilFactorsDao();
        methodsNDao = App.getInstance().getDatabase().methodsNDao();
        methodsP2O5Dao = App.getInstance().getDatabase().methodsP2O5Dao();
        methodsK2ODao = App.getInstance().getDatabase().methodsK2ODao();
    }

    @Override
    public Single<CalculateNEntity> getDataN(int id) {
        return calculatorDao.getDataN(id);
    }

    @Override
    public Single<Double> getPhN(double sf_pH) {
        return calculatorDao.getPhN(sf_pH);
    }

    @Override
    public Single<CalculateP2O5Entity> getDataP2O5(int id) {
        return calculatorDao.getDataP2O5(id);
    }

    @Override
    public Single<Double> getPhP2O5(double sf_pH) {
        return calculatorDao.getPhP2O5(sf_pH);
    }

    @Override
    public Single<CalculateK2OEntity> getDataK2O(int id) {
        return calculatorDao.getDataK2O(id);
    }

    @Override
    public Single<Double> getPhK2O(double sf_pH) {
        return calculatorDao.getPhK2O(sf_pH);
    }

    @Override
    public Single<CalculateCaOEntity> getDataCaO(int id) {
        return calculatorDao.getDataCaO(id);
    }

    @Override
    public Single<Double> getPhCaO(double sf_pH) {
        return calculatorDao.getPhCaO(sf_pH);
    }

    @Override
    public Single<CalculateMgOEntity> getDataMgO(int id) {
        return calculatorDao.getDataMgO(id);
    }

    @Override
    public Single<Double> getPhMgO(double sf_pH) {
        return calculatorDao.getPhMgO(sf_pH);
    }

    @Override
    public Single<CalculateSEntity> getDataS(int id) {
        return calculatorDao.getDataS(id);
    }

    @Override
    public Single<Double> getPhS(double sf_pH) {
        return calculatorDao.getPhS(sf_pH);
    }

    @Override
    public Single<CalculateH2OEntity> getDataH2O(int id) {
        return calculatorDao.getDataH2O(id);
    }

    @Override
    public Single<Double> getTyrinIndexN(double valueN) {
        return methodsNDao.getTyrinIndex(valueN);
    }

    @Override
    public Single<Double> getKornfildIndexN(double valueN) {
        return methodsNDao.getKornfildIndex(valueN);
    }

    @Override
    public Single<Double> getChirikovIndexP2O5(double valueP2O5) {
        return methodsP2O5Dao.getChirikovIndex(valueP2O5);
    }

    @Override
    public Single<Double> getKirsanovIndexP2O5(double valueP2O5) {
        return methodsP2O5Dao.getKirsanovIndex(valueP2O5);
    }

    @Override
    public Single<Double> getChirikovIndexK2O(double valueP2O5) {
        return methodsK2ODao.getChirikovIndex(valueP2O5);
    }

    @Override
    public Single<Double> getKirsanovIndexK2O(double valueP2O5) {
        return methodsK2ODao.getKirsanovIndex(valueP2O5);
    }

    @Override
    public Flowable<List<CultureModel>> getCultureList() {
        return cultureDao.getList();
    }
//
//    @Override
//    public Single<PhasesImgModel> getPhaseImg(int cultureId) {
//        return phasesImgDao.getPhaseImgByCultureId(cultureId);
//    }
//
//    @Override
//    public Single<PhasesModel> getPhases(int cultureId, int productive) {
//        return phasesDao.getPhases(cultureId, productive);
//    }
//
//    @Override
//    public Single<ProductiveInfoModel> getPhasesInfo(int cultureId) {
//        return productiveInfoDao.getPhasesInfo(cultureId);
//    }

    @Override
    public Single<TestCultureModel> getTestCultureModel(int cultureId) {
        return testCultureDao.getById(cultureId);
    }

    @Override
    public Single<SoilFactorsModel> getSoilFactorsModel() {
        return soilFactorsDao.getSoilFactorsModel();
    }

    @Override
    public void setSoilFactorsModel(SoilFactorsModel soilFactorsModel) {
        Completable.fromAction(() -> soilFactorsDao.insert(soilFactorsModel))
                .subscribeOn(Schedulers.io())
                .subscribe();
    }
}
