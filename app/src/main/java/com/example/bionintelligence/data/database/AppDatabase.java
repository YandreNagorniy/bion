package com.example.bionintelligence.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.bionintelligence.App;
import com.example.bionintelligence.data.database.dao.CalculatorDao;
import com.example.bionintelligence.data.database.dao.CultureDao;
import com.example.bionintelligence.data.database.dao.KUsvDao;
import com.example.bionintelligence.data.database.dao.MethodsK2ODao;
import com.example.bionintelligence.data.database.dao.MethodsNDao;
import com.example.bionintelligence.data.database.dao.MethodsP2O5Dao;
import com.example.bionintelligence.data.database.dao.PHDao;
import com.example.bionintelligence.data.database.dao.ProductiveInfoDao;
import com.example.bionintelligence.data.database.dao.PhasesDao;
import com.example.bionintelligence.data.database.dao.PhasesImgDao;
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
import com.example.bionintelligence.data.model.PhasesImgModel;
import com.example.bionintelligence.data.model.ProductiveInfoModel;
import com.example.bionintelligence.data.model.PhasesModel;
import com.example.bionintelligence.data.model.PrecipitationRequirementsModel;
import com.example.bionintelligence.data.model.SoilFactorsLimitsModel;
import com.example.bionintelligence.data.model.SoilFactorsModel;
import com.example.bionintelligence.data.model.TestCultureModel;
import com.example.bionintelligence.data.model.VinosModel;
import com.example.bionintelligence.data.model.WaterConsumptionModel;

@Database(entities = {CalculatorModel.class, KUsvModel.class, MethodsK2OModel.class, MethodsNModel.class,
        MethodsP2O5Model.class, PHModel.class, PrecipitationRequirementsModel.class, SoilFactorsModel.class, VinosModel.class,
        WaterConsumptionModel.class, CultureModel.class, PhasesImgModel.class, PhasesModel.class, ProductiveInfoModel.class,
        TestCultureModel.class, SoilFactorsLimitsModel.class},
        version = App.DATABASE_VERSION)
public abstract class AppDatabase extends RoomDatabase {

    public abstract CalculatorDao calculatorDao();
    public abstract KUsvDao kUsvDao();
    public abstract MethodsK2ODao methodsK2ODao();
    public abstract MethodsNDao methodsNDao();
    public abstract MethodsP2O5Dao methodsP2O5Dao();
    public abstract PHDao phDao();
    public abstract PrecipitationRequirementsDao precipitationRequirementsDao();
    public abstract SoilFactorsDao soilFactorsDao();
    public abstract VinosDao vinosDao();
    public abstract WaterConsumptionDao waterConsumptionDao();
    public abstract CultureDao cultureDao();
    public abstract PhasesImgDao phasesImgDao();
    public abstract PhasesDao phasesDao();
    public abstract ProductiveInfoDao phaseInfoDao();
    public abstract TestCultureDao testCultureDao();
    public abstract SoilFactorsLimitsDao soilFactorsLimitDao();
}
