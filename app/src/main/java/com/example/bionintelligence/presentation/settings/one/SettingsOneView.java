package com.example.bionintelligence.presentation.settings.one;

import com.example.bionintelligence.data.model.SoilFactorsLimitsModel;
import com.example.bionintelligence.data.model.SoilFactorsModel;
import com.example.bionintelligence.data.pojo.AnalyticalFactors;

public interface SettingsOneView {
    void displaySoilFactors(SoilFactorsModel soilFactors);

    void receiveSoilFactorsLimits(SoilFactorsLimitsModel soilFactorsLimitsModel);

    void receiveAnalyticalFactors(AnalyticalFactors analyticalFactors);
}
