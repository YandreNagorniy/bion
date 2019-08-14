package com.example.bionintelligence.data.map;

import com.example.bionintelligence.data.model.CalculatorModel;
import com.example.bionintelligence.domain.entities.ElementModelEntity;

import java.util.List;

public class ElementMapper {
    public static CalculatorModel mapToCalculatorModel(List<ElementModelEntity> entityList) {
        CalculatorModel calculatorModel = new CalculatorModel();
        for (ElementModelEntity element : entityList) {
            switch (element.getElement()) {
                case N:
                    calculatorModel.setN(element.getValue());
                    break;
                case P2O5:
                    calculatorModel.setP2O5(element.getValue());
                    break;
                case K2O:
                    calculatorModel.setK2O(element.getValue());
                    break;
                case CaO:
                    calculatorModel.setCaO(element.getValue());
                    break;
                case MgO:
                    calculatorModel.setMgO(element.getValue());
                    break;
                case S:
                    calculatorModel.setS(element.getValue());
                    break;
                case H2O:
                    calculatorModel.setH2O(element.getValue());
                    break;
            }
        }
        return calculatorModel;
    }
}
