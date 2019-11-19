package com.bionagro.bionintelligence.presentation.settings.two;

import com.bionagro.bionintelligence.data.pojo.AnalyticalFactors;

public interface SettingsTwoPresenter {
    void attachView(SettingsFragmentTwo settingsFragmentTwo);

    void getAnalyticalFactors();

    void saveAnalyticalFactors(AnalyticalFactors analyticalFactors);

    void detachView();

}
