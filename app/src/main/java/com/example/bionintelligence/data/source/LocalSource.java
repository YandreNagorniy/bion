package com.example.bionintelligence.data.source;

public interface LocalSource {

    int getSettingsCultureId();

//    String getSettingsCultureName();

    void setSettingsCultureId(int cultureId);

//    void setSettingsCultureName(String cultureName);

    int getSettingsCultureProductive();

    void setSettingsCultureProductive(int culture_productive);

    int getAnalyticalFactorsN();

    void setAnalyticalFactorsN(int analyticalFactors1);

    int getAnalyticalFactorsP2O5();

    void setAnalyticalFactorsP2O5(int analyticalFactors2);

    int getAnalyticalFactorsK2O();

    void setAnalyticalFactorsK2O(int analyticalFactors3);


//    int getSettingsProductiveMax();
//
//
//    void setSettingsProductiveMax(int productiveMax);
//
//    int getSettingsProductiveMin();
//
//    void setSettingsProductiveMin(int productiveMin);
//
//    int getSettingsProductiveStep();
//
//    void setSettingsProductiveStep(int productiveStep);
}
