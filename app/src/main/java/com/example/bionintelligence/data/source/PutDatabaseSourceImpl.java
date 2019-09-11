package com.example.bionintelligence.data.source;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.bionintelligence.App;
import com.example.bionintelligence.R;
import com.example.bionintelligence.data.database.dao.CalculatorDao;
import com.example.bionintelligence.data.database.dao.CultureDao;
import com.example.bionintelligence.data.database.dao.KUsvDao;
import com.example.bionintelligence.data.database.dao.MethodsK2ODao;
import com.example.bionintelligence.data.database.dao.MethodsNDao;
import com.example.bionintelligence.data.database.dao.MethodsP2O5Dao;
import com.example.bionintelligence.data.database.dao.PHDao;
import com.example.bionintelligence.data.database.dao.PrecipitationRequirementsDao;
import com.example.bionintelligence.data.database.dao.SoilFactorsDao;
import com.example.bionintelligence.data.database.dao.SoilFactorsLimitsDao;
import com.example.bionintelligence.data.database.dao.TestCultureDao;
import com.example.bionintelligence.data.database.dao.VinosDao;
import com.example.bionintelligence.data.database.dao.WaterConsumptionDao;
import com.example.bionintelligence.data.model.CalculatorModel;
import com.example.bionintelligence.data.model.CultureModel;
import com.example.bionintelligence.data.model.KUsvModel;
import com.example.bionintelligence.data.model.MethodsK2OModel;
import com.example.bionintelligence.data.model.MethodsNModel;
import com.example.bionintelligence.data.model.MethodsP2O5Model;
import com.example.bionintelligence.data.model.PHModel;
import com.example.bionintelligence.data.model.PrecipitationRequirementsModel;
import com.example.bionintelligence.data.model.SoilFactorsLimitsModel;
import com.example.bionintelligence.data.model.SoilFactorsModel;
import com.example.bionintelligence.data.model.TestCultureModel;
import com.example.bionintelligence.data.model.TestPhasesImgModel;
import com.example.bionintelligence.data.model.TestPhasesModel;
import com.example.bionintelligence.data.model.VinosModel;
import com.example.bionintelligence.data.model.WaterConsumptionModel;
import com.example.bionintelligence.data.pojo.MinMaxListPojo;
import com.example.bionintelligence.data.pojo.MinMaxPojo;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;

public class PutDatabaseSourceImpl implements PutDatabaseSource {
    private static final String LOCAL_DATABASE_VERSION = "local_database_version";
    private static final String APP_SETTINGS = "app_settings";
    private SharedPreferences sharedPreferences;

    public PutDatabaseSourceImpl(WeakReference<Context> context) {
        sharedPreferences = context.get().getApplicationContext().getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE);
    }

    @Override
    public int getLocalDataBaseVersion() {
        return sharedPreferences.getInt(LOCAL_DATABASE_VERSION, 0);
    }

    @Override
    public void setLocalDataBaseVersion(int isFull) {
        sharedPreferences.edit().putInt(LOCAL_DATABASE_VERSION, isFull).apply();
    }

    @Override
    public Completable fillDataInDB() {
        return Completable.concatArray(
                setCultureData(),
                setCalculatorData(),
                setPrecipitationRequirementsData(),
                setMethodsK20(),
                setMethodsP2O5(),
                setMethodsN(),
                setPHData(),
                setPrecipitationRequirementsData(),
                setSoilFactorsData(),
                setVinosData(),
                setWaterConsumptionData(),
                setKUsvData(),
                setTestCultureModel(),
                setSoilFactorsLimit());
    }

    //добавить
    private Completable setSoilFactorsLimit() {

        SoilFactorsLimitsDao soilFactorsLimitsDao = App.getInstance().getDatabase().soilFactorsLimitDao();

        List<MinMaxPojo> listNkimits = new ArrayList<>();
        listNkimits.add(new MinMaxPojo(0.1, 100));
        listNkimits.add(new MinMaxPojo(0.1, 500));
        listNkimits.add(new MinMaxPojo(0.1, 250));

        List<MinMaxPojo> listP2Okimits = new ArrayList<>();
        listP2Okimits.add(new MinMaxPojo(0.1, 100));
        listP2Okimits.add(new MinMaxPojo(0.1, 350));
        listP2Okimits.add(new MinMaxPojo(0.1, 450));

        List<MinMaxPojo> listK2Okimits = new ArrayList<>();
        listK2Okimits.add(new MinMaxPojo(0.1, 1000));
        listK2Okimits.add(new MinMaxPojo(0.1, 300));
        listK2Okimits.add(new MinMaxPojo(0.1, 450));

        SoilFactorsLimitsModel soilFactorsLimitsModel = new SoilFactorsLimitsModel(
                new MinMaxListPojo(listNkimits), new MinMaxListPojo(listP2Okimits), new MinMaxListPojo(listK2Okimits),
                new MinMaxPojo(0.1, 50),
                new MinMaxPojo(0.1, 10), new MinMaxPojo(0.1, 100), new MinMaxPojo(0.01, 10),
                new MinMaxPojo(0.1, 5), new MinMaxPojo(0.1, 30), new MinMaxPojo(0.1, 5),
                new MinMaxPojo(0.01, 5), new MinMaxPojo(0.01, 10), new MinMaxPojo(0.01, 15),
                new MinMaxPojo(0.5, 10), new MinMaxPojo(4, 10), new MinMaxPojo(10, 300));

        return Completable.fromAction(() -> soilFactorsLimitsDao.insert(soilFactorsLimitsModel));
    }

    private Completable setSoilFactorsData() {
        SoilFactorsDao soilFactorsDao = App.getInstance().getDatabase().soilFactorsDao();

        List<SoilFactorsModel> soilFactorsList = new ArrayList<>();
        soilFactorsList.add(new SoilFactorsModel(15, 30, 350, 25, 3, 3,
                0.25, 0.17, 6.05, 0.02, 0.1, 0.43, 7.18, 3.5, 7, 145));

        return Completable.fromAction(() -> soilFactorsDao.insertList(soilFactorsList));
    }

    private Completable setKUsvData() {
        KUsvDao kUsvDao =
                App.getInstance().getDatabase().kUsvDao();

        List<KUsvModel> kUsvList = new ArrayList<>();
        kUsvList.add(new KUsvModel(1, 0.6, 0.25, 0.13, 0.06, 0.125, 0.5));
        kUsvList.add(new KUsvModel(2, 0.6, 0.27, 0.2, 0.06, 0.13, 0.6));
        kUsvList.add(new KUsvModel(3, 0.5, 0.2, 0.14, 0.17, 0.3, 0.35));
        kUsvList.add(new KUsvModel(4, 0.65, 0.25, 0.13, 0.06, 0.1, 0.35));
        kUsvList.add(new KUsvModel(5, 0.5, 0.2, 0.13, 0.12, 0.23, 0.5));
        kUsvList.add(new KUsvModel(6, 0.6, 0.25, 0.13, 0.07, 0.01, 0.5));
        kUsvList.add(new KUsvModel(7, 0.45, 0.27, 0.13, 0.06, 0.13, 0.69));
        kUsvList.add(new KUsvModel(8, 0.45, 0.27, 0.13, 0.06, 0.13, 0.69));
        kUsvList.add(new KUsvModel(9, 0.6, 0.25, 0.13, 0.06, 0.125, 0.6));
        kUsvList.add(new KUsvModel(10, 0.65, 0.25, 0.13, 0.06, 0.1, 0.75));

        return Completable.fromAction(() -> kUsvDao.insertList(kUsvList));
    }

    private Completable setMethodsK20() {
        MethodsK2ODao methodsK2ODao =
                App.getInstance().getDatabase().methodsK2ODao();

        List<MethodsK2OModel> methodsK2OList = new ArrayList<>();
        methodsK2OList.add(new MethodsK2OModel("Очень низкое", 1, 40, 1, 20, 1, 100, 2.295, 4.985));
        methodsK2OList.add(new MethodsK2OModel("Низкое", 41, 80, 21, 40, 101, 200, 2.474, 4.812));
        methodsK2OList.add(new MethodsK2OModel("Среднее", 81, 120, 41, 80, 201, 300, 2.468, 4.702));
        methodsK2OList.add(new MethodsK2OModel("Повышенное", 121, 170, 81, 120, 301, 400, 2.577, 3.404));
        methodsK2OList.add(new MethodsK2OModel("Высокое", 171, 250, 121, 180, 401, 600, 2.338, 3.31));
        methodsK2OList.add(new MethodsK2OModel("Очень высокое", 251, 1000, 181, 1000, 601, 1000, 2.4, 3.333));

        return Completable.fromAction(() -> methodsK2ODao.insertList(methodsK2OList));
    }

    private Completable setMethodsP2O5() {
        MethodsP2O5Dao methodsP2O5Dao =
                App.getInstance().getDatabase().methodsP2O5Dao();

        List<MethodsP2O5Model> methodsP2O5List = new ArrayList<>();
        methodsP2O5List.add(new MethodsP2O5Model("Очень низкое", 0, 25, 0, 20, 0, 10, 0.4, 0.526));
        methodsP2O5List.add(new MethodsP2O5Model("Низкое", 26, 50, 21, 50, 11, 15, 0.353, 0.372));
        methodsP2O5List.add(new MethodsP2O5Model("Среднее", 51, 100, 51, 100, 16, 30, 0.305, 0.341));
        methodsP2O5List.add(new MethodsP2O5Model("Повышенное", 101, 150, 101, 150, 31, 45, 0.303, 0.315));
        methodsP2O5List.add(new MethodsP2O5Model("Высокое", 151, 250, 151, 200, 46, 60, 0.265, 0.305));
        methodsP2O5List.add(new MethodsP2O5Model("Очень высокое", 251, 1000, 201, 1000, 61, 1000, 0.243, 0.3));

        return Completable.fromAction(() -> methodsP2O5Dao.insertList(methodsP2O5List));
    }

    private Completable setMethodsN() {
        MethodsNDao methodsNDao =
                App.getInstance().getDatabase().methodsNDao();

        List<MethodsNModel> methodsNList = new ArrayList<>();
        methodsNList.add(new MethodsNModel("Очень низкое", 0, 30, 0, 100, 0, 5, 0.167, 0.05));
        methodsNList.add(new MethodsNModel("Низкое", 31, 40, 101, 150, 6, 8, 0.197, 0.056));
        methodsNList.add(new MethodsNModel("Среднее", 41, 50, 151, 200, 9, 15, 0.26, 0.067));
        methodsNList.add(new MethodsNModel("Повышенное", 51, 70, 201, 300, 16, 30, 0.371, 0.09));
        methodsNList.add(new MethodsNModel("Высокое", 71, 100, 301, 500, 31, 60, 0.518, 0.111));
        methodsNList.add(new MethodsNModel("Очень высокое", 101, 999, 501, 999, 61, 999, 0.604, 0.122));

        return Completable.fromAction(() -> methodsNDao.insertList(methodsNList));
    }


    private Completable setPHData() {
        PHDao phDao =
                App.getInstance().getDatabase().phDao();

        List<PHModel> phList = new ArrayList<>();
        phList.add(new PHModel(4.0, 0.37, 0.3, 0.3, 0.2, 0.4, 0.3, 0.2, 0.2, 0.2, 0.2, 0.2, 0.18, 1));
        phList.add(new PHModel(4.1, 0.45, 0.31, 0.4, 0.22, 0.45, 0.4, 0.25, 0.23, 0.25, 0.22, 0.25, 0.19, 1));
        phList.add(new PHModel(4.2, 0.48, 0.32, 0.5, 0.25, 0.48, 0.5, 0.3, 0.25, 0.3, 0.25, 0.3, 0.2, 1));
        phList.add(new PHModel(4.3, 0.52, 0.33, 0.6, 0.27, 0.51, 0.6, 0.35, 0.27, 0.35, 0.3, 0.35, 0.25, 1));
        phList.add(new PHModel(4.4, 0.54, 0.34, 0.65, 0.33, 0.54, 0.65, 0.4, 0.3, 0.4, 0.35, 0.4, 0.3, 1));
        phList.add(new PHModel(4.5, 0.6, 0.35, 0.68, 0.35, 0.57, 0.68, 0.5, 0.32, 0.5, 0.4, 0.5, 0.35, 1));
        phList.add(new PHModel(4.6, 0.6, 0.36, 0.7, 0.39, 0.6, 0.7, 0.6, 0.35, 0.6, 0.45, 0.6, 0.4, 1));
        phList.add(new PHModel(4.7, 0.63, 0.37, 0.72, 0.40, 0.62, 0.72, 0.7, 0.37, 0.7, 0.5, 0.7, 0.41, 1));
        phList.add(new PHModel(4.8, 0.65, 0.38, 0.74, 0.42, 0.64, 0.74, 0.8, 0.39, 0.8, 0.55, 0.8, 0.42, 1));
        phList.add(new PHModel(4.9, 0.75, 0.39, 0.75, 0.44, 0.66, 0.75, 0.9, 0.41, 0.9, 0.6, 0.9, 0.43, 1));
        phList.add(new PHModel(5.0, 0.77, 0.39, 0.76, 0.47, 0.68, 0.76, 1, 0.43, 1, 0.65, 1, 0.44, 1));
        phList.add(new PHModel(5.1, 0.79, 0.39, 0.77, 0.5, 0.71, 0.77, 0.75, 0.45, 0.75, 0.75, 0.75, 0.45, 0.98));
        phList.add(new PHModel(5.2, 0.8, 0.39, 0.78, 0.52, 0.73, 0.78, 0.8, 0.47, 0.8, 0.8, 0.8, 0.46, 0.96));
        phList.add(new PHModel(5.3, 0.81, 0.4, 0.79, 0.54, 0.75, 0.79, 0.95, 0.48, 0.95, 0.95, 0.95, 0.47, 0.94));
        phList.add(new PHModel(5.4, 0.83, 0.43, 0.81, 0.58, 0.78, 0.80, 1, 0.49, 1, 1, 0.97, 0.48, 0.92));
        phList.add(new PHModel(5.5, 0.85, 0.45, 0.84, 0.6, 0.8, 0.81, 1, 0.5, 1, 1, 0.99, 0.49, 0.9));
        phList.add(new PHModel(5.6, 0.87, 0.48, 0.87, 0.64, 0.83, 0.82, 1, 0.53, 1, 1, 1, 0.52, 0.88));
        phList.add(new PHModel(5.7, 0.9, 0.5, 0.9, 0.68, 0.86, 0.83, 1, 0.55, 1, 1, 1, 0.5, 0.86));
        phList.add(new PHModel(5.8, 0.93, 0.53, 0.93, 0.72, 0.89, 0.84, 1, 0.6, 1, 1, 1, 0.51, 0.84));
        phList.add(new PHModel(5.9, 0.98, 0.55, 0.95, 0.74, 0.92, 0.85, 1, 0.65, 1, 1, 1, 0.52, 0.82));
        phList.add(new PHModel(6, 1, 0.6, 0.98, 0.76, 0.93, 0.86, 1, 0.67, 1, 1, 1, 0.53, 0.8));
        phList.add(new PHModel(6.1, 1, 0.68, 1, 0.82, 0.94, 0.9, 1, 0.73, 1, 0.98, 1, 0.54, 0.78));
        phList.add(new PHModel(6.2, 1, 0.72, 1, 0.85, 0.95, 0.95, 1, 0.77, 1, 0.97, 1, 0.55, 0.76));
        phList.add(new PHModel(6.3, 1, 0.78, 1, 0.89, 0.96, 1, 1, 0.82, 1, 0.95, 1, 0.65, 0.74));
        phList.add(new PHModel(6.4, 1, 0.88, 1, 0.92, 0.97, 1, 1, 0.86, 1, 0.93, 1, 0.7, 0.72));
        phList.add(new PHModel(6.5, 1, 0.95, 1, 0.94, 0.98, 1, 1, 0.9, 1, 0.86, 1, 0.75, 0.70));
        phList.add(new PHModel(6.6, 1, 1, 1, 0.95, 0.99, 1, 1, 0.94, 1, 0.83, 1, 0.8, 0.68));
        phList.add(new PHModel(6.7, 1, 1, 1, 0.97, 1, 1, 1, 0.98, 1, 0.78, 1, 0.85, 0.66));
        phList.add(new PHModel(6.8, 1, 1, 1, 0.99, 1, 1, 1, 1, 1, 0.74, 1, 0.9, 0.64));
        phList.add(new PHModel(6.9, 1, 1, 1, 0.99, 1, 1, 1, 1, 1, 0.7, 1, 0.95, 0.62));
        phList.add(new PHModel(7, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0.68, 1, 1, 0.6));
        phList.add(new PHModel(7.1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0.66, 1, 1, 0.58));
        phList.add(new PHModel(7.2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0.64, 0.98, 1, 0.56));
        phList.add(new PHModel(7.3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0.62, 0.95, 1, 0.54));
        phList.add(new PHModel(7.4, 1, 1, 1, 1, 1, 1, 0.95, 1, 0.95, 0.60, 0.93, 0.95, 0.52));
        phList.add(new PHModel(7.5, 1, 1, 1, 1, 1, 1, 0.9, 1, 0.9, 0.58, 0.9, 0.9, 0.5));
        phList.add(new PHModel(7.6, 1, 1, 1, 0.95, 1, 1, 0.85, 1, 0.85, 0.56, 0.85, 0.85, 0.48));
        phList.add(new PHModel(7.7, 1, 0.87, 1, 0.92, 0.98, 1, 0.85, 1, 0.85, 0.54, 0.85, 0.80, 0.46));
        phList.add(new PHModel(7.8, 1, 0.85, 1, 0.9, 0.95, 1, 0.8, 1, 0.8, 0.52, 0.8, 0.75, 0.44));
        phList.add(new PHModel(7.9, 1, 0.7, 1, 0.88, 0.91, 1, 0.75, 1, 0.75, 0.5, 0.75, 0.7, 0.42));
        phList.add(new PHModel(8, 1, 0.65, 1, 0.87, 0.89, 1, 0.7, 1, 0.7, 0.5, 0.7, 0.65, 0.4));
        phList.add(new PHModel(8.1, 0.95, 0.6, 1, 0.85, 0.86, 1, 0.68, 1, 0.68, 0.48, 0.68, 0.6, 0.38));
        phList.add(new PHModel(8.2, 0.9, 0.55, 1, 0.83, 0.83, 1, 0.65, 1, 0.65, 0.46, 0.65, 0.58, 0.36));
        phList.add(new PHModel(8.3, 0.85, 0.55, 1, 0.82, 0.82, 1, 0.68, 1, 0.68, 0.44, 0.68, 0.55, 0.34));
        phList.add(new PHModel(8.4, 0.85, 0.55, 1, 0.8, 0.8, 1, 0.65, 1, 0.65, 0.4, 0.65, 0.53, 0.33));
        phList.add(new PHModel(8.5, 0.75, 0.5, 1, 0.79, 0.79, 1, 0.63, 1, 0.63, 0.38, 0.63, 0.5, 0.32));
        phList.add(new PHModel(8.6, 0.7, 0.6, 1, 0.77, 0.77, 1, 0.6, 1, 0.6, 0.35, 0.6, 0.56, 0.31));
        phList.add(new PHModel(8.7, 0.65, 0.7, 1, 0.75, 0.75, 1, 0.58, 1, 0.58, 0.31, 0.58, 0.68, 0.30));
        phList.add(new PHModel(8.8, 0.65, 0.8, 1, 0.74, 0.74, 1, 0.55, 1, 0.55, 0.28, 0.55, 0.75, 0.29));
        phList.add(new PHModel(8.9, 0.6, 0.9, 1, 0.73, 0.73, 1, 0.53, 1, 0.53, 0.27, 0.53, 0.8, 0.28));
        phList.add(new PHModel(9, 0.55, 0.95, 1, 0.7, 0.7, 1, 0.5, 1, 0.5, 0.25, 0.5, 0.9, 0.27));
        phList.add(new PHModel(9.1, 0.5, 1, 1, 0.69, 0.69, 1, 0.48, 1, 0.48, 0.25, 0.48, 0.95, 0.27));
        phList.add(new PHModel(9.2, 0.45, 1, 1, 0.67, 0.67, 1, 0.46, 1, 0.46, 0.25, 0.46, 1, 0.27));
        phList.add(new PHModel(9.3, 0.4, 1, 1, 0.67, 0.67, 1, 0.44, 1, 0.44, 0.24, 0.44, 1, 0.27));
        phList.add(new PHModel(9.4, 0.45, 1, 1, 0.65, 0.65, 1, 0.41, 1, 0.41, 0.23, 0.41, 1, 0.27));
        phList.add(new PHModel(9.5, 0.35, 1, 1, 0.62, 0.62, 1, 0.38, 1, 0.38, 0.23, 0.38, 1, 0.27));
        phList.add(new PHModel(9.6, 0.35, 1, 1, 0.59, 0.59, 1, 0.36, 1, 0.36, 0.22, 0.36, 1, 0.27));
        phList.add(new PHModel(9.7, 0.3, 1, 1, 0.57, 0.57, 1, 0.35, 1, 0.35, 0.22, 0.35, 1, 0.27));
        phList.add(new PHModel(9.8, 0.25, 1, 1, 0.55, 0.55, 1, 0.35, 1, 0.35, 0.21, 0.35, 1, 0.27));
        phList.add(new PHModel(9.9, 0.25, 1, 1, 0.52, 0.52, 1, 0.34, 1, 0.34, 0.2, 0.34, 1, 0.27));
        phList.add(new PHModel(10, 0.2, 1, 1, 0.49, 0.49, 1, 0.33, 1, 0.33, 0.2, 0.33, 1, 0.27));

        return Completable.fromAction(() -> phDao.insertList(phList));
    }

    //нужно?
    private Completable setPrecipitationRequirementsData() {
        PrecipitationRequirementsDao precipitationRequirementsDao =
                App.getInstance().getDatabase().precipitationRequirementsDao();

        List<PrecipitationRequirementsModel> precipitationRequirementsModelList = new ArrayList<>();
        precipitationRequirementsModelList.add(new PrecipitationRequirementsModel(1, 261));
        precipitationRequirementsModelList.add(new PrecipitationRequirementsModel(2, 170));
        precipitationRequirementsModelList.add(new PrecipitationRequirementsModel(3, -96));
        precipitationRequirementsModelList.add(new PrecipitationRequirementsModel(4, 698));
        precipitationRequirementsModelList.add(new PrecipitationRequirementsModel(5, -55.75));
        precipitationRequirementsModelList.add(new PrecipitationRequirementsModel(6, 442));
        precipitationRequirementsModelList.add(new PrecipitationRequirementsModel(7, 592.45));
        precipitationRequirementsModelList.add(new PrecipitationRequirementsModel(8, 396.8));
        precipitationRequirementsModelList.add(new PrecipitationRequirementsModel(9, 255));
        precipitationRequirementsModelList.add(new PrecipitationRequirementsModel(10, 638));

        return Completable.fromAction(() -> precipitationRequirementsDao.insertList(precipitationRequirementsModelList));
    }

    private Completable setVinosData() {
        VinosDao vinosDao =
                App.getInstance().getDatabase().vinosDao();

        List<VinosModel> vinosList = new ArrayList<>();
        vinosList.add(new VinosModel(1, 3.13, 0.9, 2.15, 0.67, 0.35, 0.55));
        vinosList.add(new VinosModel(2, 2.21, 0.81, 2.2, 0.6, 0.3, 0.35));
        vinosList.add(new VinosModel(3, 0.37, 0.25, 0.68, 0.35, 0.1, 0.14));
        vinosList.add(new VinosModel(4, 3.85, 1.82, 3.9, 1, 0.5, 0.71));
        vinosList.add(new VinosModel(5, 0.5, 0.39, 1.1, 0.3, 0.05, 0.09));
        vinosList.add(new VinosModel(6, 5.1, 2.1, 7, 0.5, 0.1, 0.6));
        vinosList.add(new VinosModel(7, 5.85, 2.71, 4.9, 1.24, 0.68, 1.08));
        vinosList.add(new VinosModel(8, 5.7, 2.75, 5, 1.24, 0.68, 0.82));
        vinosList.add(new VinosModel(9, 3.44, 1.08, 2.38, 0.57, 0.26, 0.44));
        vinosList.add(new VinosModel(10, 4.33, 3.5, 5.8, 2, 0.7, 1.15));

        return Completable.fromAction(() -> vinosDao.insertList(vinosList));
    }

    private Completable setWaterConsumptionData() {
        WaterConsumptionDao waterConsumptionDao =
                App.getInstance().getDatabase().waterConsumptionDao();

        List<WaterConsumptionModel> waterConsumptionModelList = new ArrayList<>();
        waterConsumptionModelList.add(new WaterConsumptionModel(1, 135));
        waterConsumptionModelList.add(new WaterConsumptionModel(2, 450));
        waterConsumptionModelList.add(new WaterConsumptionModel(3, 70));
        waterConsumptionModelList.add(new WaterConsumptionModel(4, 280));
        waterConsumptionModelList.add(new WaterConsumptionModel(5, 375));
        waterConsumptionModelList.add(new WaterConsumptionModel(6, 195));
        waterConsumptionModelList.add(new WaterConsumptionModel(7, 245));
        waterConsumptionModelList.add(new WaterConsumptionModel(8, 180));
        waterConsumptionModelList.add(new WaterConsumptionModel(9, 133));
        waterConsumptionModelList.add(new WaterConsumptionModel(10, 260));

        return Completable.fromAction(() -> waterConsumptionDao.insertList(waterConsumptionModelList));
    }

    private Completable setCalculatorData() {
        CalculatorDao calculatorDao =
                App.getInstance().getDatabase().calculatorDao();
        CalculatorModel calculatorModel =
                new CalculatorModel(0, 0, 0, 0, 0, 0, 0);

        return Completable.fromAction(() -> calculatorDao.insert(calculatorModel));
    }


    private Completable setCultureData() {
        CultureDao cultureDao = App.getInstance().getDatabase().cultureDao();

        List<CultureModel> cultureList = new ArrayList<>();
        cultureList.add(new CultureModel("Озимая пшеница", R.drawable.img_winter_wheat));
        cultureList.add(new CultureModel("Кукуруза", R.drawable.img_corn));
        cultureList.add(new CultureModel("Сахарная свекла", R.drawable.img_sugar_beet));
        cultureList.add(new CultureModel("Соя", R.drawable.img_soy));
        cultureList.add(new CultureModel("Картофель", R.drawable.img_potatoes));
        cultureList.add(new CultureModel("Подсолнечник", R.drawable.img_sun_flower));
        cultureList.add(new CultureModel("Озимый рапс", R.drawable.img_rape));
        cultureList.add(new CultureModel("Яровой рапс", R.drawable.img_rape));
        cultureList.add(new CultureModel("Яровая пшеница", R.drawable.img_winter_wheat));
        cultureList.add(new CultureModel("Нут", R.drawable.img_chickpea));

        return Completable.fromAction(() -> cultureDao.insertList(cultureList));
    }

    private Completable setTestCultureModel() {

        List<TestPhasesModel> winterWheatPhasesList = new ArrayList<>();
        winterWheatPhasesList.add(new TestPhasesModel(30, 0, 0, 0, 0, 0, -1));
        winterWheatPhasesList.add(new TestPhasesModel(40, 1, 0.2, 0.2, 0.3, 0.2, -1));
        winterWheatPhasesList.add(new TestPhasesModel(50, 1, 0.4, 0.4, 0.6, 0.4, -1));
        winterWheatPhasesList.add(new TestPhasesModel(60, 1, 0.8, 0.8, 1.2, 0.8, -1));
        winterWheatPhasesList.add(new TestPhasesModel(70, 1, 1, 1, 1.5, 1, -1));
        winterWheatPhasesList.add(new TestPhasesModel(80, 1, 1.3, 1.3, 2, 1.3, -1));
        winterWheatPhasesList.add(new TestPhasesModel(90, 1, 1.8, 1.8, 2.7, 1.8, -1));
        winterWheatPhasesList.add(new TestPhasesModel(100, 1, 2.4, 2.4, 3.6, 2.4, -1));
        winterWheatPhasesList.add(new TestPhasesModel(110, 1, 3.1, 3.1, 4.6, 3.1, -1));
        winterWheatPhasesList.add(new TestPhasesModel(120, 1, 4, 4, 6, 4, -1));

        List<TestPhasesModel> cornPhasesList = new ArrayList<>();
        cornPhasesList.add(new TestPhasesModel(40, 0, 0, 0, -1, -1, -1));
        cornPhasesList.add(new TestPhasesModel(50, 0.4, 0.4, 0.4, -1, -1, -1));
        cornPhasesList.add(new TestPhasesModel(60, 0.8, 0.8, 0.8, -1, -1, -1));
        cornPhasesList.add(new TestPhasesModel(70, 1.2, 1.2, 1.2, -1, -1, -1));
        cornPhasesList.add(new TestPhasesModel(80, 1.5, 1.5, 1.5, -1, -1, -1));
        cornPhasesList.add(new TestPhasesModel(90, 2, 2, 2, -1, -1, -1));
        cornPhasesList.add(new TestPhasesModel(100, 2.4, 2.4, 2.4, -1, -1, -1));
        cornPhasesList.add(new TestPhasesModel(110, 2.8, 2.8, 2.8, -1, -1, -1));
        cornPhasesList.add(new TestPhasesModel(120, 3.3, 3.3, 3.3, -1, -1, -1));
        cornPhasesList.add(new TestPhasesModel(130, 4, 4, 4, -1, -1, -1));
        cornPhasesList.add(new TestPhasesModel(140, 4.3, 4.3, 4.3, -1, -1, -1));
        cornPhasesList.add(new TestPhasesModel(150, 4.9, 4.9, 4.9, -1, -1, -1));

        List<TestPhasesModel> sugarBeetPhasesList = new ArrayList<>();
        sugarBeetPhasesList.add(new TestPhasesModel(250, 0, 0, 0, 0, 0, -1));
        sugarBeetPhasesList.add(new TestPhasesModel(300, 0.2, 0.4, 0.6, 0.6, 0.3, -1));
        sugarBeetPhasesList.add(new TestPhasesModel(350, 0.3, 0.7, 1, 1, 0.5, -1));
        sugarBeetPhasesList.add(new TestPhasesModel(400, 0.7, 1.3, 1.8, 1.8, 1, -1));
        sugarBeetPhasesList.add(new TestPhasesModel(450, 1, 2, 2.5, 2.5, 1.5, -1));
        sugarBeetPhasesList.add(new TestPhasesModel(500, 1.3, 2.7, 3.3, 3.3, 2, -1));
        sugarBeetPhasesList.add(new TestPhasesModel(550, 1.7, 3.3, 4.3, 4.3, 2.5, -1));
        sugarBeetPhasesList.add(new TestPhasesModel(600, 2, 4, 5, 5, 3, -1));
        sugarBeetPhasesList.add(new TestPhasesModel(650, 2.3, 4.9, 5.6, 5.6, 3.7, -1));
        sugarBeetPhasesList.add(new TestPhasesModel(700, 2.7, 5.6, 6.3, 6.3, 4.2, -1));
        sugarBeetPhasesList.add(new TestPhasesModel(750, 3, 6.4, 6.9, 6.9, 4.8, -1));
        sugarBeetPhasesList.add(new TestPhasesModel(800, 3.3, 7.3, 7.5, 7.5, 5.5, -1));

        List<TestPhasesModel> soyPhasesList = new ArrayList<>();
        soyPhasesList.add(new TestPhasesModel(10, 0, 0, 0, -1, -1, -1));
        soyPhasesList.add(new TestPhasesModel(15, 0.4, 0.6, 0.4, -1, -1, -1));
        soyPhasesList.add(new TestPhasesModel(20, 0.8, 1.3, 0.8, -1, -1, -1));
        soyPhasesList.add(new TestPhasesModel(25, 1.5, 2, 1.5, -1, -1, -1));
        soyPhasesList.add(new TestPhasesModel(30, 2, 3, 2, -1, -1, -1));
        soyPhasesList.add(new TestPhasesModel(35, 2.7, 4, 2.7, -1, -1, -1));
        soyPhasesList.add(new TestPhasesModel(40, 3.5, 5, 3.5, -1, -1, -1));
        soyPhasesList.add(new TestPhasesModel(45, 4.4, 6, 4.4, -1, -1, -1));
        soyPhasesList.add(new TestPhasesModel(50, 5.3, 7, 5.3, -1, -1, -1));

        List<TestPhasesModel> potatoesPhasesList = new ArrayList<>();
        potatoesPhasesList.add(new TestPhasesModel(250, 0, 0, 0, 0, 0, 0));
        potatoesPhasesList.add(new TestPhasesModel(300, 0.6, 0.8, 0.8, 1.1, 0.8, 0.6));
        potatoesPhasesList.add(new TestPhasesModel(350, 1.2, 1.6, 1.6, 2.2, 1.6, 1.2));
        potatoesPhasesList.add(new TestPhasesModel(400, 1.8, 2.4, 2.4, 3.2, 2.4, 1.8));
        potatoesPhasesList.add(new TestPhasesModel(450, 2.4, 3.2, 3.1, 4.1, 3.2, 2.4));
        potatoesPhasesList.add(new TestPhasesModel(500, 3, 4, 4, 5, 4, 3));
        potatoesPhasesList.add(new TestPhasesModel(550, 3.7, 4.7, 4.7, 5.7, 4.7, 3.7));
        potatoesPhasesList.add(new TestPhasesModel(600, 4.3, 5.4, 5.4, 6.4, 5.4, 4.3));
        potatoesPhasesList.add(new TestPhasesModel(650, 5, 6, 6, 7, 6, 5));
        potatoesPhasesList.add(new TestPhasesModel(700, 5.6, 6.4, 6.4, 7.6, 6.4, 5.6));
        potatoesPhasesList.add(new TestPhasesModel(750, 6.3, 7.3, 7.3, 8.1, 7.3, 6.3));
        potatoesPhasesList.add(new TestPhasesModel(800, 7, 8, 8, 8.5, 8, 7));

        List<TestPhasesModel> sunFlowerPhasesList = new ArrayList<>();
        sunFlowerPhasesList.add(new TestPhasesModel(15, 0, 0, 0, -1, -1, -1));
        sunFlowerPhasesList.add(new TestPhasesModel(20, 0.3, 0.5, 0.3, -1, -1, -1));
        sunFlowerPhasesList.add(new TestPhasesModel(25, 1, 1.4, 1, -1, -1, -1));
        sunFlowerPhasesList.add(new TestPhasesModel(30, 1.5, 2, 1.5, -1, -1, -1));
        sunFlowerPhasesList.add(new TestPhasesModel(35, 2, 3, 2, -1, -1, -1));
        sunFlowerPhasesList.add(new TestPhasesModel(40, 3.1, 4.5, 3.1, -1, -1, -1));
        sunFlowerPhasesList.add(new TestPhasesModel(45, 4.2, 6.5, 4.2, -1, -1, -1));
        sunFlowerPhasesList.add(new TestPhasesModel(50, 5.5, 8.3, 5.5, -1, -1, -1));

        List<TestPhasesModel> winterRapePhasesList = new ArrayList<>();
        winterRapePhasesList.add(new TestPhasesModel(20, 0, 0, 0, 0, -1, -1));
        winterRapePhasesList.add(new TestPhasesModel(30, 1, 1, 1.3, 1, -1, -1));
        winterRapePhasesList.add(new TestPhasesModel(40, 2, 2, 3, 2, -1, -1));
        winterRapePhasesList.add(new TestPhasesModel(50, 3.2, 3.2, 4.6, 3.2, -1, -1));
        winterRapePhasesList.add(new TestPhasesModel(60, 4.3, 4.3, 6.3, 4.3, -1, -1));
        winterRapePhasesList.add(new TestPhasesModel(70, 5.4, 5.4, 8.1, 5.4, -1, -1));

        List<TestPhasesModel> springRapePhasesList = new ArrayList<>();
        springRapePhasesList.add(new TestPhasesModel(15, 0.4, 0.8, 0.4, -1, -1, -1));
        springRapePhasesList.add(new TestPhasesModel(20, 0.8, 1.3, 0.8, -1, -1, -1));
        springRapePhasesList.add(new TestPhasesModel(25, 1.5, 2.0, 1.5, -1, -1, -1));
        springRapePhasesList.add(new TestPhasesModel(30, 2, 3, 2, -1, -1, -1));
        springRapePhasesList.add(new TestPhasesModel(35, 2.7, 4, 2.7, -1, -1, -1));
        springRapePhasesList.add(new TestPhasesModel(40, 3.5, 5, 3.5, -1, -1, -1));
        springRapePhasesList.add(new TestPhasesModel(45, 4.4, 6, 4.4, -1, -1, -1));
        springRapePhasesList.add(new TestPhasesModel(50, 5.3, 7, 5.3, -1, -1, -1));

        List<TestPhasesModel> springWheatPhasesList = new ArrayList<>();
        springWheatPhasesList.add(new TestPhasesModel(20, 0, 0, 0, 0, -1, -1));
        springWheatPhasesList.add(new TestPhasesModel(30, 0.2, 0.2, 0.2, 0.2, -1, -1));
        springWheatPhasesList.add(new TestPhasesModel(40, 0.4, 0.4, 0.4, 0.4, -1, -1));
        springWheatPhasesList.add(new TestPhasesModel(50, 0.8, 0.8, 0.8, 0.8, -1, -1));
        springWheatPhasesList.add(new TestPhasesModel(60, 1, 1, 1, 1, -1, -1));
        springWheatPhasesList.add(new TestPhasesModel(70, 1.3, 1.3, 1.3, 1.3, -1, -1));
        springWheatPhasesList.add(new TestPhasesModel(80, 1.8, 1.8, 1.8, 1.8, -1, -1));
        springWheatPhasesList.add(new TestPhasesModel(90, 2.4, 2.4, 2.4, 2.4, -1, -1));
        springWheatPhasesList.add(new TestPhasesModel(100, 3.1, 3.1, 3.1, 3.1, -1, -1));

        List<TestPhasesModel> chickpeaPhasesList = new ArrayList<>();
        chickpeaPhasesList.add(new TestPhasesModel(5, 0, 0, 0, -1, -1, -1));
        chickpeaPhasesList.add(new TestPhasesModel(10, 0.4, 0.6, 0.4, -1, -1, -1));
        chickpeaPhasesList.add(new TestPhasesModel(15, 0.8, 1.3, 0.8, -1, -1, -1));
        chickpeaPhasesList.add(new TestPhasesModel(20, 1.5, 2, 1.5, -1, -1, -1));
        chickpeaPhasesList.add(new TestPhasesModel(25, 2, 3, 2, -1, -1, -1));
        chickpeaPhasesList.add(new TestPhasesModel(30, 2.5, 4, 2.5, -1, -1, -1));
        chickpeaPhasesList.add(new TestPhasesModel(35, 3.3, 5, 3.3, -1, -1, -1));
        chickpeaPhasesList.add(new TestPhasesModel(40, 4, 6, 4, -1, -1, -1));
        chickpeaPhasesList.add(new TestPhasesModel(45, 5, 7, 5, -1, -1, -1));

        List<TestPhasesImgModel> phasesImgList = new ArrayList<>();
        phasesImgList.add(new TestPhasesImgModel(R.drawable.phase_winter_wheat1, R.drawable.phase_winter_wheat2, R.drawable.phase_winter_wheat3, R.drawable.phase_winter_wheat4, R.drawable.phase_winter_wheat5, 0));
        phasesImgList.add(new TestPhasesImgModel(R.drawable.phase_corn1, R.drawable.phase_corn2, R.drawable.phase_corn3, 0, 0, 0));
        phasesImgList.add(new TestPhasesImgModel(R.drawable.phase_sugar_beet1, R.drawable.phase_sugar_beet2, R.drawable.phase_sugar_beet3, R.drawable.phase_sugar_beet4, R.drawable.phase_sugar_beet5, 0));
        phasesImgList.add(new TestPhasesImgModel(R.drawable.phase_soy1, R.drawable.phase_soy2, R.drawable.phase_soy3, 0, 0, 0));
        phasesImgList.add(new TestPhasesImgModel(R.drawable.phase_potatoes1, R.drawable.phase_potatoes2, R.drawable.phase_potatoes3, R.drawable.phase_potatoes4, R.drawable.phase_potatoes5, R.drawable.phase_potatoes6));
        phasesImgList.add(new TestPhasesImgModel(R.drawable.phase_sun_flower1, R.drawable.phase_sun_flower2, R.drawable.phase_sun_flower3, 0, 0, 0));
        phasesImgList.add(new TestPhasesImgModel(R.drawable.phase_winter_rape1, R.drawable.phase_winter_rape2, R.drawable.phase_winter_rape3, R.drawable.phase_winter_rape4, 0, 0));
        phasesImgList.add(new TestPhasesImgModel(R.drawable.phase_spring_rape1, R.drawable.phase_spring_rape2, R.drawable.phase_spring_rape3, 0, 0, 0));
        phasesImgList.add(new TestPhasesImgModel(R.drawable.phase_spring_wheat1, R.drawable.phase_spring_wheat2, R.drawable.phase_spring_wheat3, R.drawable.phase_spring_wheat4, 0, 0));
        phasesImgList.add(new TestPhasesImgModel(R.drawable.phase_chickpea1, R.drawable.phase_chickpea2, R.drawable.phase_chickpea3, 0, 0, 0));

        List<TestCultureModel> cultureList = new ArrayList<>();
        cultureList.add(new TestCultureModel(1, "Озимая пшеница", 30, 70, 120, 10, R.drawable.img_winter_wheat, winterWheatPhasesList, phasesImgList.get(0)));
        cultureList.add(new TestCultureModel(2, "Кукуруза", 40, 90, 150, 10, R.drawable.img_corn, cornPhasesList, phasesImgList.get(1)));
        cultureList.add(new TestCultureModel(3, "Сахарная свекла", 250, 600, 800, 50, R.drawable.img_sugar_beet, sugarBeetPhasesList, phasesImgList.get(2)));
        cultureList.add(new TestCultureModel(4, "Соя", 10, 30, 50, 5, R.drawable.img_soy, soyPhasesList, phasesImgList.get(3)));
        cultureList.add(new TestCultureModel(5, "Картофель", 250, 500, 800, 50, R.drawable.img_potatoes, potatoesPhasesList, phasesImgList.get(4)));
        cultureList.add(new TestCultureModel(6, "Подсолнечник", 15, 35, 50, 5, R.drawable.img_sun_flower, sunFlowerPhasesList, phasesImgList.get(5)));
        cultureList.add(new TestCultureModel(7, "Озимый рапс", 20, 40, 70, 10, R.drawable.img_rape, winterRapePhasesList, phasesImgList.get(6)));
        cultureList.add(new TestCultureModel(8, "Яровой рапс", 15, 25, 50, 5, R.drawable.img_rape, springRapePhasesList, phasesImgList.get(7)));
        cultureList.add(new TestCultureModel(9, "Яровой пшеница", 20, 60, 100, 10, R.drawable.img_winter_wheat, springWheatPhasesList, phasesImgList.get(8)));
        cultureList.add(new TestCultureModel(10, "Нут", 10, 25, 45, 5, R.drawable.img_chickpea, chickpeaPhasesList, phasesImgList.get(9)));

        TestCultureDao testCultureDao = App.getInstance().getDatabase().testCultureDao();

        return Completable.fromAction(() -> testCultureDao.insertList(cultureList));
    }
}
