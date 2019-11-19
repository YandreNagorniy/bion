package com.bionagro.bionintelligence.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.bionagro.bionintelligence.App;
import com.bionagro.bionintelligence.data.database.dao.CalculatorDao;
import com.bionagro.bionintelligence.data.database.dao.CultureDao;
import com.bionagro.bionintelligence.data.database.dao.KUsvDao;
import com.bionagro.bionintelligence.data.database.dao.MethodsK2ODao;
import com.bionagro.bionintelligence.data.database.dao.MethodsNDao;
import com.bionagro.bionintelligence.data.database.dao.MethodsP2O5Dao;
import com.bionagro.bionintelligence.data.database.dao.PHDao;
import com.bionagro.bionintelligence.data.database.dao.ProductiveInfoDao;
import com.bionagro.bionintelligence.data.database.dao.PhasesDao;
import com.bionagro.bionintelligence.data.database.dao.PhasesImgDao;
import com.bionagro.bionintelligence.data.database.dao.PrecipitationRequirementsDao;
import com.bionagro.bionintelligence.data.database.dao.SoilFactorsDao;
import com.bionagro.bionintelligence.data.database.dao.SoilFactorsLimitsDao;
import com.bionagro.bionintelligence.data.database.dao.TestCultureDao;
import com.bionagro.bionintelligence.data.database.dao.VinosDao;
import com.bionagro.bionintelligence.data.database.dao.WaterConsumptionDao;
import com.bionagro.bionintelligence.data.model.CalculatorModel;
import com.bionagro.bionintelligence.data.model.CultureModel;
import com.bionagro.bionintelligence.data.model.KUsvModel;
import com.bionagro.bionintelligence.data.model.MethodsK2OModel;
import com.bionagro.bionintelligence.data.model.MethodsNModel;
import com.bionagro.bionintelligence.data.model.MethodsP2O5Model;
import com.bionagro.bionintelligence.data.model.PHModel;
import com.bionagro.bionintelligence.data.model.PhasesImgModel;
import com.bionagro.bionintelligence.data.model.ProductiveInfoModel;
import com.bionagro.bionintelligence.data.model.PhasesModel;
import com.bionagro.bionintelligence.data.model.PrecipitationRequirementsModel;
import com.bionagro.bionintelligence.data.model.SoilFactorsLimitsModel;
import com.bionagro.bionintelligence.data.model.SoilFactorsModel;
import com.bionagro.bionintelligence.data.model.TestCultureModel;
import com.bionagro.bionintelligence.data.model.VinosModel;
import com.bionagro.bionintelligence.data.model.WaterConsumptionModel;

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
