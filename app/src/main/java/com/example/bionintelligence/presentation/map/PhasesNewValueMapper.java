package com.example.bionintelligence.presentation.map;

import com.example.bionintelligence.data.model.TestPhasesModel;

import java.util.List;

public class PhasesNewValueMapper {
    public static TestPhasesModel mapPhasesByProductive(List<TestPhasesModel> phasesModelList, int newProductive) {
        int value;
        int index = 0;
        int min = 999;
        for (int i = 0; i < phasesModelList.size(); i++) {
            value = newProductive - phasesModelList.get(i).getProductive();
            if (value < 0) {
                value *= -1;
            }
            if (value < min) {
                index = i;
                min = value;
            }
        }
        return phasesModelList.get(index);
    }
}
