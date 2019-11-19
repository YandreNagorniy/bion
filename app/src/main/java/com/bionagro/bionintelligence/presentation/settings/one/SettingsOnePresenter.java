package com.bionagro.bionintelligence.presentation.settings.one;

import com.bionagro.bionintelligence.data.model.SoilFactorsModel;

public interface SettingsOnePresenter {

    void onStart();

    void attachView(SettingsOneView settingsOneView);

    void setSoilFactorsData(SoilFactorsModel soilFactorsModel);

    void detachView();

    void getSoilFactorsData();

    void getSoilFactorsLimits();

    void getAnalyticalFactors();
}
