package com.example.bionintelligence.presentation.calculator;

import com.example.bionintelligence.data.model.CalculatorModel;
import com.example.bionintelligence.data.model.PhasesImgModel;
import com.example.bionintelligence.data.model.ProductiveInfoModel;
import com.example.bionintelligence.data.model.PhasesModel;
import com.example.bionintelligence.data.model.TestCultureModel;
import com.example.bionintelligence.data.model.TestPhasesModel;
import com.example.bionintelligence.domain.entities.CalculatorParams;

import java.util.List;

public interface CalculatorView {

    void displayCalculatorData(CalculatorModel calculatorModel);

    void displayCultureData(TestCultureModel cultureModel);

    void displayPhasesData(TestPhasesModel phasesModel);

    void displayNewPhasesData(TestPhasesModel phasesModel);

    void displayNewProductive(Integer newProductive);

    void displayRefreshData(int productive);
}
