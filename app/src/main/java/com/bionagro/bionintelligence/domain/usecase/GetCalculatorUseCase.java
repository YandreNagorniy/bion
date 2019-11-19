package com.bionagro.bionintelligence.domain.usecase;

import com.bionagro.bionintelligence.data.pojo.AnalyticalFactors;
import com.bionagro.bionintelligence.data.repositories.CalculatorRepositoryImpl;
import com.bionagro.bionintelligence.domain.entities.CalculateCaOEntity;
import com.bionagro.bionintelligence.domain.entities.CalculateH2OEntity;
import com.bionagro.bionintelligence.domain.entities.CalculateK2OEntity;
import com.bionagro.bionintelligence.domain.entities.CalculateMgOEntity;
import com.bionagro.bionintelligence.domain.entities.CalculateNEntity;
import com.bionagro.bionintelligence.domain.entities.CalculateP2O5Entity;
import com.bionagro.bionintelligence.domain.entities.CalculateSEntity;
import com.bionagro.bionintelligence.domain.entities.CalculatorParams;
import com.bionagro.bionintelligence.domain.entities.ElementModelEntity;
import com.bionagro.bionintelligence.domain.entities.TypeElementEntity;
import com.bionagro.bionintelligence.domain.repositories.CalculatorRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class GetCalculatorUseCase extends FlowableUseCase<CalculatorParams, List<ElementModelEntity>> {
    private CalculatorRepository calculatorRepository;
    private CalculatorParams params;

    public GetCalculatorUseCase(CalculatorRepositoryImpl repository) {
        this.calculatorRepository = repository;
    }

    @Override
    public Flowable<List<ElementModelEntity>> execute(CalculatorParams params) {
        this.params = params;
        List<Single<ElementModelEntity>> list = new ArrayList<>();
        list.add(getDataN(params.getCultureId()));
        list.add(getDataP2O5(params.getCultureId()));
        list.add(getDataK2O(params.getCultureId()));
        list.add(getDataCaO(params.getCultureId()));
        list.add(getDataMgO(params.getCultureId()));
        list.add(getDataS(params.getCultureId()));
        list.add(getDataH20(params.getCultureId()));

        return Single.concat(list)
                .buffer(7)
                .cache();
    }

    private Single<ElementModelEntity> getDataN(int cultureId) {
        return calculatorRepository.getDataN(cultureId)
                .subscribeOn(Schedulers.computation())
                .flatMap(pairN -> getMethodN()
                        .flatMap(method -> getIndexN(pairN.second.sf_N, method))
                        .map(index -> {
                            pairN.second.sf_N *= index;
                            return pairN;
                        })
                ).map(pairN -> calculateN(pairN.first, pairN.second))
                .map(n -> new ElementModelEntity(TypeElementEntity.N, n));
    }

    private Single<Integer> getMethodN() {
        return calculatorRepository.getAnalyticalFactors()
                .subscribeOn(Schedulers.io())
                .map(AnalyticalFactors::getAfN);
    }

    private Single<Double> getIndexN(double n, int method) {
        if (n > 0) {
            switch (method) {
                case 2: {
                    return calculatorRepository.getKornfildIndexN(n);
                }
                case 3:
                    return calculatorRepository.getTyrinIndexN(n);
                default:
                    return Single.just(1.0);
            }
        } else {
            return Single.just(1.0);
        }
    }

    private Single<ElementModelEntity> getDataP2O5(int cultureId) {
        return calculatorRepository.getDataP2O5(cultureId)
                .subscribeOn(Schedulers.computation())
                .flatMap(pairP2O5 -> getMethodP2O5().flatMap(method -> getIndexP2O5(pairP2O5.second.sf_P2O5, method)).map(index -> {
                            pairP2O5.second.sf_P2O5 *= index;
                            return pairP2O5;
                        })
                ).map(pairP2O5 -> calculateP2O5(pairP2O5.first, pairP2O5.second))
                .map(p2O5 -> new ElementModelEntity(TypeElementEntity.P2O5, p2O5));
    }

    private Single<Integer> getMethodP2O5() {
        return calculatorRepository.getAnalyticalFactors()
                .subscribeOn(Schedulers.io())
                .map(AnalyticalFactors::getAfP2O5);
    }

    private Single<Double> getIndexP2O5(double sfP2O5, int method) {
        if (sfP2O5 > 0) {
            switch (method) {
                case 2: {
                    return calculatorRepository.getChirikovIndexP2O5(sfP2O5);
                }
                case 3:
                    return calculatorRepository.getKirsanovIndexP2O5(sfP2O5);
                default:
                    return Single.just(1.0);
            }
        } else {
            return Single.just(1.0);
        }
    }

    private Single<ElementModelEntity> getDataK2O(int cultureId) {
        return calculatorRepository.getDataK2O(cultureId)
                .subscribeOn(Schedulers.computation())
                .flatMap(pairK2O -> getMethodK2O().flatMap(method -> getIndexK2O(pairK2O.second.sf_K2O, method)).map(index -> {
                            pairK2O.second.sf_K2O *= index;
                            return pairK2O;
                        })
                ).map(pairK2O -> calculateK2O(pairK2O.first, pairK2O.second))
                .map(k2O -> new ElementModelEntity(TypeElementEntity.K2O, k2O));
    }

    private Single<Integer> getMethodK2O() {
        return calculatorRepository.getAnalyticalFactors()
                .subscribeOn(Schedulers.io())
                .map(AnalyticalFactors::getAfK2O);
    }

    private Single<Double> getIndexK2O(double k2O, int method) {
        if (k2O > 0) {
            switch (method) {
                case 2: {
                    return calculatorRepository.getChirikovIndexK2O(k2O);
                }
                case 3:
                    return calculatorRepository.getKirsanovIndexK2O(k2O);
                default:
                    return Single.just(1.0);
            }
        } else {
            return Single.just(1.0);
        }
    }

    private Single<ElementModelEntity> getDataCaO(int cultureId) {
        return calculatorRepository.getDataCaO(cultureId)
                .subscribeOn(Schedulers.computation())
                .map(doubleCalculateCaOPair -> calculateCaO(doubleCalculateCaOPair.first, doubleCalculateCaOPair.second))
                .map(caO -> new ElementModelEntity(TypeElementEntity.CaO, caO));
    }

    private Single<ElementModelEntity> getDataMgO(int cultureId) {
        return calculatorRepository.getDataMgO(cultureId)
                .subscribeOn(Schedulers.computation())
                .map(doubleCalculateMgOPair -> calculateMgO(doubleCalculateMgOPair.first, doubleCalculateMgOPair.second))
                .map(mgO -> new ElementModelEntity(TypeElementEntity.MgO, mgO));
    }

    private Single<ElementModelEntity> getDataS(int cultureId) {
        return calculatorRepository.getDataS(cultureId)
                .subscribeOn(Schedulers.computation())
                .map(doubleCalculateSPair -> calculateS(doubleCalculateSPair.first, doubleCalculateSPair.second))
                .map(s -> new ElementModelEntity(TypeElementEntity.S, s));
    }

    private Single<ElementModelEntity> getDataH20(int cultureId) {
        return calculatorRepository.getDataH2O(cultureId)
                .subscribeOn(Schedulers.computation())
                .map(calculateH2OEntity -> calculateH2O(calculateH2OEntity, cultureId))
                .map(h2O -> new ElementModelEntity(TypeElementEntity.H2O, h2O));
    }

    private int calculateN(Double phN, CalculateNEntity calculateNEntity) {
        double sf_N = calculateNEntity.sf_N;
        double sf_G = calculateNEntity.sf_G;
        double kusv_N = calculateNEntity.kusv_N;
        double vinos_N = calculateNEntity.vinos_N;
        double x;
        double n;

        if (sf_G > 4) {
            x = 64 + sf_G * 0.16;
        } else {
            x = sf_G * 16;
        }

        n = vinos_N * params.getProductive() - (sf_N * 3.96 * kusv_N * phN + x);

        return n < 0 ? 0 : (int) Math.round(n);
    }

    private int calculateP2O5(Double phP2O5, CalculateP2O5Entity calculateP2O5Entity) {
        double vinos_P2O5 = calculateP2O5Entity.vinos_P2O5;
        double sf_P2O5 = calculateP2O5Entity.sf_P2O5;
        double kusv_P2O5 = calculateP2O5Entity.kusv_P2O5;
        double p2O5 = vinos_P2O5 * params.getProductive() - sf_P2O5 * kusv_P2O5 * 3.96 * phP2O5;

        return p2O5 < 0 ? 0 : (int) Math.round(p2O5);
    }

    private int calculateK2O(Double phK2O, CalculateK2OEntity calculateK2OEntity) {
        double vinos_K2O = calculateK2OEntity.vinos_K2O;
        double sf_K2O = calculateK2OEntity.sf_K2O;
        double kusv_K2O = calculateK2OEntity.kusv_K2O;
        double k2O = vinos_K2O * params.getProductive() - sf_K2O * kusv_K2O * 3.96 * phK2O;

        return k2O < 0 ? 0 : (int) Math.round(k2O);
    }

    private int calculateCaO(Double phCaO, CalculateCaOEntity calculateCaOEntity) {
        double vinos_CaO = calculateCaOEntity.vinos_CaO;
        double sf_CaO = calculateCaOEntity.sf_CaO;
        double kusv_CaO = calculateCaOEntity.kusv_CaO;
        double caO = vinos_CaO * params.getProductive() - sf_CaO * kusv_CaO * 3.96 * 20 * phCaO;

        return caO < 0 ? 0 : (int) Math.round(caO);
    }

    private int calculateMgO(Double phMgO, CalculateMgOEntity calculateMgOEntity) {
        double vinos_MgO = calculateMgOEntity.vinos_MgO;
        double sf_MgO = calculateMgOEntity.sf_MgO;
        double kusv_MgO = calculateMgOEntity.kusv_MgO;
        double mgO = vinos_MgO * params.getProductive() - sf_MgO * kusv_MgO * 3.96 * 12 * phMgO;

        return mgO < 0 ? 0 : (int) Math.round(mgO);
    }

    private int calculateS(Double phS, CalculateSEntity calculateSEntity) {
        double vinos_S = calculateSEntity.vinos_S;
        double sf_S = calculateSEntity.sf_S;
        double kusv_S = calculateSEntity.kusv_S;
        double s = vinos_S * params.getProductive() - sf_S * kusv_S * 3.96 * phS;

        return s < 0 ? 0 : (int) Math.round(s);
    }

    private int calculateH2O(CalculateH2OEntity calculateH2OEntity, int cultureId) {
        double waterConsumption_value = calculateH2OEntity.waterConsumption_value;
        double sf_zpv = calculateH2OEntity.sf_zpv;
        double h2O;

        if (cultureId == 2 || cultureId == 3) {
            h2O = (params.getProductive() * waterConsumption_value) / 100 - sf_zpv;

        } else {
            if (cultureId == 5) {
                h2O = (params.getProductive() * waterConsumption_value * 0.34) / 100 - sf_zpv;

            } else {
                h2O = (params.getProductive() * waterConsumption_value * 2.15 / 100 - 0.5 * sf_zpv) / 0.5;
                if (h2O < 0) {
                    h2O = 0;
                }
            }
        }
        return (int) Math.round(h2O);
    }
}
