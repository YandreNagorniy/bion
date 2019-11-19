package com.bionagro.bionintelligence.presentation.calculator;

import com.bionagro.bionintelligence.data.model.CalculatorModel;
import com.bionagro.bionintelligence.data.model.TestCultureModel;
import com.bionagro.bionintelligence.data.model.TestPhasesModel;

public interface CalculatorView {

    void displayCalculatorData(CalculatorModel calculatorModel);

    void displayCultureData(TestCultureModel cultureModel);

    void displayPhasesData(TestPhasesModel phasesModel);

    void displayNewPhasesData(TestPhasesModel phasesModel);

    void displayNewProductive(Integer newProductive);

    void displayRefreshData(int productive);
}
