package com.example.bionintelligence.presentation.settings.two;

import com.example.bionintelligence.data.pojo.AnalyticalFactors;

public interface SettingsTwoPresenter {
    void attachView(SettingsFragmentTwo settingsFragmentTwo);

    void getAnalyticalFactors();

    void saveAnalyticalFactors(AnalyticalFactors analyticalFactors);

    void detachView();

}
