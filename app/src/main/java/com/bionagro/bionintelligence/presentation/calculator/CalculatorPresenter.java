package com.bionagro.bionintelligence.presentation.calculator;

import com.bionagro.bionintelligence.data.model.TestPhasesModel;
import com.bionagro.bionintelligence.domain.entities.CalculatorParams;

import java.util.List;

public interface CalculatorPresenter {
    void attachView(CalculatorView calculatorView);

    void setParamsData(CalculatorParams params);

    void getCalculatorData(int productive, int cultureId);

    void getStartData();

    void getCultureModel(int cultureId);

    void calculateProductive(int cultureId, String tvItemName, int parseInt, int productive);

    void getPhasesData(List<TestPhasesModel> phasesModelList, int productive);

    void getNewPhasesData(List<TestPhasesModel> phasesModelList, int productive);

    void getRestartData();

    void detachView();

}
