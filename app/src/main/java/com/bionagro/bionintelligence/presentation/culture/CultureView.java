package com.bionagro.bionintelligence.presentation.culture;

import com.bionagro.bionintelligence.data.model.CultureModel;

import java.util.List;

public interface CultureView {
    void displayData(List<CultureModel> cultureModelList);
}
