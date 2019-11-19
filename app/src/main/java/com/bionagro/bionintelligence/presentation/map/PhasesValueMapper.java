package com.bionagro.bionintelligence.presentation.map;

import com.bionagro.bionintelligence.data.model.TestPhasesModel;

import java.util.List;

public class PhasesValueMapper {
    public static TestPhasesModel mapPhasesByProductive(List<TestPhasesModel> phasesModelList, int productive) {
        for (TestPhasesModel phasesModel : phasesModelList) {
            if (phasesModel.getProductive() == productive) {
                return phasesModel;
            }
        }
        return null;
    }
}
