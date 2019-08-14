package com.example.bionintelligence.presentation.calculator;

import com.example.bionintelligence.data.map.ElementMapper;
import com.example.bionintelligence.data.model.TestCultureModel;
import com.example.bionintelligence.data.model.TestPhasesModel;
import com.example.bionintelligence.domain.entities.CalculatorParams;
import com.example.bionintelligence.domain.entities.ElementModelEntity;
import com.example.bionintelligence.domain.entities.ProductiveParams;
import com.example.bionintelligence.domain.repositories.CalculatorRepository;
import com.example.bionintelligence.domain.usecase.FlowableUseCase;
import com.example.bionintelligence.domain.usecase.ProductiveUseCase;
import com.example.bionintelligence.presentation.map.PhasesNewValueMapper;
import com.example.bionintelligence.presentation.map.PhasesValueMapper;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class CalculatorPresenterImpl implements CalculatorPresenter {
    private FlowableUseCase<CalculatorParams, List<ElementModelEntity>> getCalculatorUseCase;
    private ProductiveUseCase<ProductiveParams, Integer> getProductiveUseCase;
    private CompositeDisposable compositeDisposable;
    private CalculatorRepository calculatorRepository;
    private CalculatorView calculatorView;

    CalculatorPresenterImpl(FlowableUseCase<CalculatorParams, List<ElementModelEntity>> getCalculatorUseCase,
                            CalculatorRepository calculatorRepository, ProductiveUseCase<ProductiveParams, Integer> getProductiveUseCase) {
        this.getCalculatorUseCase = getCalculatorUseCase;
        this.getProductiveUseCase = getProductiveUseCase;
        this.calculatorRepository = calculatorRepository;
        this.compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void attachView(CalculatorView calculatorView) {
        this.calculatorView = calculatorView;
    }

    @Override
    public void setParamsData(CalculatorParams params) {
        calculatorRepository.setCalculatorParams(params);
    }

    @Override
    public void getStartData() {
        compositeDisposable.add(calculatorRepository.getCalculatorParams()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(params -> getCultureModel(params.getCultureId())));
    }

    @Override
    public void getCalculatorData(int productive, int cultureId) {
        compositeDisposable.add(getCalculatorUseCase.execute(new CalculatorParams(productive, cultureId))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map(ElementMapper::mapToCalculatorModel)
                .subscribe(calculatorModel ->
                        calculatorView.displayCalculatorData(calculatorModel)));
    }

    @Override
    public void getCultureModel(int cultureId) {
        compositeDisposable.add(calculatorRepository.getTestCultureModel(cultureId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(testCultureModel ->
                        calculatorView.displayCultureData(testCultureModel), Throwable::printStackTrace));
    }

    @Override
    public void calculateProductive(int cultureId, String itemName, int newValue, int productive) {
        compositeDisposable.add(getProductiveUseCase.execute(new ProductiveParams(cultureId, itemName, newValue, productive))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(newProductive -> calculatorView.displayNewProductive(newProductive)));
    }

    @Override
    public void getPhasesData(List<TestPhasesModel> phasesModelList, int productive) {
        compositeDisposable.add(Single.just(phasesModelList)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map(testPhasesModels -> PhasesValueMapper.mapPhasesByProductive(testPhasesModels, productive))
                .subscribe(phasesModel -> calculatorView.displayPhasesData(phasesModel))
        );
    }

    @Override
    public void getNewPhasesData(List<TestPhasesModel> phasesModelList, int newProductive) {
        compositeDisposable.add(Single.just(phasesModelList)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(testPhasesModels -> PhasesNewValueMapper.mapPhasesByProductive(testPhasesModels, newProductive))
                .subscribe(phasesModel -> calculatorView.displayNewPhasesData(phasesModel))
        );
    }

    @Override
    public void getRestartData() {
        compositeDisposable.add(calculatorRepository.getCalculatorParams()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(params -> calculatorView.displayRefreshData(params.getProductive())));
    }

    @Override
    public void detachView() {
        compositeDisposable.dispose();
        calculatorView = null;
    }
}
