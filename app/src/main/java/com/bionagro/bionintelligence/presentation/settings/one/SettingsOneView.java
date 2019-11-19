package com.bionagro.bionintelligence.presentation.settings.one;

import com.bionagro.bionintelligence.data.model.SoilFactorsLimitsModel;
import com.bionagro.bionintelligence.data.model.SoilFactorsModel;
import com.bionagro.bionintelligence.data.pojo.AnalyticalFactors;

public interface SettingsOneView {
    void displaySoilFactors(SoilFactorsModel soilFactors);

    void receiveSoilFactorsLimits(SoilFactorsLimitsModel soilFactorsLimitsModel);

    void receiveAnalyticalFactors(AnalyticalFactors analyticalFactors);

    void refresh(AnalyticalFactors analyticalFactors);
}
