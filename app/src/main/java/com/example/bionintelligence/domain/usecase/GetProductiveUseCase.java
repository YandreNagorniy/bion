package com.example.bionintelligence.domain.usecase;

import com.example.bionintelligence.data.repositories.CalculatorRepositoryImpl;
import com.example.bionintelligence.domain.entities.CalculateCaOEntity;
import com.example.bionintelligence.domain.entities.CalculateH2OEntity;
import com.example.bionintelligence.domain.entities.CalculateK2OEntity;
import com.example.bionintelligence.domain.entities.CalculateMgOEntity;
import com.example.bionintelligence.domain.entities.CalculateNEntity;
import com.example.bionintelligence.domain.entities.CalculateP2O5Entity;
import com.example.bionintelligence.domain.entities.CalculateSEntity;
import com.example.bionintelligence.domain.entities.CalculatorParams;
import com.example.bionintelligence.domain.entities.ProductiveParams;
import com.example.bionintelligence.domain.repositories.CalculatorRepository;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class GetProductiveUseCase extends ProductiveUseCase<ProductiveParams, Integer> {
    private CalculatorRepository calculatorRepository;
    private CalculatorParams params;

    public GetProductiveUseCase(CalculatorRepositoryImpl repository) {
        this.calculatorRepository = repository;
    }

    @Override
    public Single<Integer> execute(ProductiveParams productiveParams) {
        switch (productiveParams.getItemName()) {
            case "N":
                return getProductiveN(productiveParams.getCultureId(), productiveParams.getNewValue());
            case "P2O5":
                return getProductiveP2O5(productiveParams.getCultureId(), productiveParams.getNewValue());
            case "K2O":
                return getProductiveK2O(productiveParams.getCultureId(), productiveParams.getNewValue());
            case "S":
                return getProductiveS(productiveParams.getCultureId(), productiveParams.getNewValue());
            case "CaO":
                return getProductiveCaO(productiveParams.getCultureId(), productiveParams.getNewValue());
            case "MgO":
                return getProductiveMgO(productiveParams.getCultureId(), productiveParams.getNewValue());
            default:
                return getProductiveH2O(productiveParams.getCultureId(), productiveParams.getNewValue(), productiveParams.getProductive());
        }
    }

    private Single<Integer> getProductiveN(int cultureId, int newValue) {
        return calculatorRepository.getDataN(cultureId)
                .subscribeOn(Schedulers.computation())
                .map(doubleCalculateNEntityPair -> calculateProductiveN(doubleCalculateNEntityPair.first,
                        doubleCalculateNEntityPair.second, newValue));
    }

    private Single<Integer> getProductiveP2O5(int cultureId, int newValue) {
        return calculatorRepository.getDataP2O5(cultureId)
                .subscribeOn(Schedulers.computation())
                .map(doubleCalculateP2O5EntityPair -> calculateProductiveP2O5(doubleCalculateP2O5EntityPair.first,
                        doubleCalculateP2O5EntityPair.second, newValue));
    }

    private Single<Integer> getProductiveK2O(int cultureId, int newValue) {
        return calculatorRepository.getDataK2O(cultureId)
                .subscribeOn(Schedulers.computation())
                .map(doubleCalculateK2OEntityPair -> calculateProductiveK2O(doubleCalculateK2OEntityPair.first,
                        doubleCalculateK2OEntityPair.second, newValue));
    }

    private Single<Integer> getProductiveS(int cultureId, int newValue) {
        return calculatorRepository.getDataS(cultureId)
                .subscribeOn(Schedulers.computation())
                .map(doubleCalculateSEntityPair -> calculateProductiveS(doubleCalculateSEntityPair.first,
                        doubleCalculateSEntityPair.second, newValue));
    }

    private Single<Integer> getProductiveCaO(int cultureId, int newValue) {
        return calculatorRepository.getDataCaO(cultureId)
                .subscribeOn(Schedulers.computation())
                .map(doubleCalculateCaOEntityPair -> calculateProductiveCaO(doubleCalculateCaOEntityPair.first,
                        doubleCalculateCaOEntityPair.second, newValue));
    }

    private Single<Integer> getProductiveMgO(int cultureId, int newValue) {
        return calculatorRepository.getDataMgO(cultureId)
                .subscribeOn(Schedulers.computation())
                .map(doubleCalculateMgOEntityPair -> calculateProductiveMgO(doubleCalculateMgOEntityPair.first,
                        doubleCalculateMgOEntityPair.second, newValue));
    }

    private Single<Integer> getProductiveH2O(int cultureId, int newValue, int productive) {
        return calculatorRepository.getDataH2O(cultureId)
                .subscribeOn(Schedulers.computation())
                .map(calculateH2OEntity -> calculateProductiveH2O(cultureId, calculateH2OEntity, newValue));
    }

    private int calculateProductiveN(Double pHN, CalculateNEntity calculateNEntity, int newValue) {
        double sf_N = calculateNEntity.sf_N;
        double sf_G = calculateNEntity.sf_G;
        double kusv_N = calculateNEntity.kusv_N;
        double vinos_n = calculateNEntity.vinos_N;
        double x;
        double n;

        if (sf_G > 4) {
            x = 64 + sf_G * 0.16;
        } else {
            x = sf_G * 16;
        }
        n = (newValue + sf_N * 3.96 * kusv_N * pHN + x) / vinos_n;

        return (int) Math.round(n);
    }

    private Integer calculateProductiveP2O5(Double pHP2O5, CalculateP2O5Entity calculateP2O5Entity, int newValue) {
        double vinos_P2O5 = calculateP2O5Entity.vinos_P2O5;
        double sf_P2O5 = calculateP2O5Entity.sf_P2O5;
        double kusv_P2O5 = calculateP2O5Entity.kusv_P2O5;
        double p2O5 = (newValue + sf_P2O5 * kusv_P2O5 * 3.96 * pHP2O5) / vinos_P2O5;

        return (int) Math.round(p2O5);
    }

    private Integer calculateProductiveK2O(Double pHK2O, CalculateK2OEntity calculateK2OEntity, int newValue) {
        double vinos_K2O = calculateK2OEntity.vinos_K2O;
        double sf_K2O = calculateK2OEntity.sf_K2O;
        double kusv_K2O = calculateK2OEntity.kusv_K2O;
        double k2O = (newValue + sf_K2O * kusv_K2O * 3.96 * pHK2O) / vinos_K2O;

        return (int) Math.round(k2O);
    }

    private Integer calculateProductiveS(Double pHS, CalculateSEntity calculateSEntity, int newValue) {
        double vinos_S = calculateSEntity.vinos_S;
        double sf_S = calculateSEntity.sf_S;
        double kusv_S = calculateSEntity.kusv_S;
        double s = (newValue + sf_S * kusv_S * 3.96 * pHS) / vinos_S;

        return (int) Math.round(s);
    }

    private Integer calculateProductiveCaO(Double pHCaO, CalculateCaOEntity calculateCaOEntity, int newValue) {
        double vinos_CaO = calculateCaOEntity.vinos_CaO;
        double sf_CaO = calculateCaOEntity.sf_CaO;
        double kusv_CaO = calculateCaOEntity.kusv_CaO;
        double caO = (newValue + sf_CaO * 3.96 * kusv_CaO * 20 * pHCaO) / vinos_CaO;

        return (int) Math.round(caO);
    }

    private Integer calculateProductiveMgO(Double phMgO, CalculateMgOEntity calculateMgOEntity, int newValue) {
        double vinos_MgO = calculateMgOEntity.vinos_MgO;
        double sf_MgO = calculateMgOEntity.sf_MgO;
        double kusv_MgO = calculateMgOEntity.kusv_MgO;
        double mgO = (newValue + sf_MgO * 3.96 * kusv_MgO * 12 * phMgO) / vinos_MgO;

        return (int) Math.round(mgO);
    }

    private Integer calculateProductiveH2O(int cultureId, CalculateH2OEntity calculateH2OEntity, int newValue) {
        double waterConsumption_value = calculateH2OEntity.waterConsumption_value;
        double sf_zpv = calculateH2OEntity.sf_zpv;
        double newProductive;

        if (cultureId == 2 || cultureId == 3) {
            newProductive = (newValue + sf_zpv) / (waterConsumption_value / 100);

        } else {
            if (cultureId == 5) {
                newProductive = (newValue + sf_zpv) / (waterConsumption_value * 0.34 / 100);

            } else {
                newProductive = (newValue * 0.5 + 0.5 * sf_zpv) / (waterConsumption_value * 2.15 / 100);

            }
        }
        return (int) Math.round(newProductive);
    }
}
