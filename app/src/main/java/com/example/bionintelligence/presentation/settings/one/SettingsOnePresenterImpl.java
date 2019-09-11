package com.example.bionintelligence.presentation.settings.one;

import android.util.Log;

import com.example.bionintelligence.data.model.SoilFactorsModel;
import com.example.bionintelligence.data.repositories.CalculatorRepositoryImpl;
import com.example.bionintelligence.data.source.LocalSourceImpl;
import com.example.bionintelligence.domain.repositories.CalculatorRepository;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class SettingsOnePresenterImpl implements SettingsOnePresenter {
    private SettingsOneView settingsOneView;
    private CompositeDisposable compositeDisposable;
    private CalculatorRepository calculatorRepository;

    SettingsOnePresenterImpl(CalculatorRepository calculatorRepository) {
        this.calculatorRepository = calculatorRepository;
        this.compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onStart() {
        compositeDisposable.add(CalculatorRepositoryImpl.afPublishSubject
                .throttleFirst(100, TimeUnit.MILLISECONDS)
                .subscribe(analyticalFactors -> settingsOneView.refresh(analyticalFactors),
                        Throwable::printStackTrace));
    }

    @Override
    public void attachView(SettingsOneView settingsOneView) {
        this.settingsOneView = settingsOneView;
    }

    @Override
    public void getSoilFactorsData() {
        compositeDisposable.add(calculatorRepository.getSoilFactorsModel()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(soilFactorsModel -> settingsOneView.displaySoilFactors(soilFactorsModel),
                        throwable -> Log.d("throwable", String.valueOf(throwable))));
    }

    @Override
    public void getSoilFactorsLimits() {
        compositeDisposable.add(calculatorRepository.getSoilFactorsLimitsModel()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(soilFactorsLimitsModel -> settingsOneView.receiveSoilFactorsLimits(soilFactorsLimitsModel),
                        Throwable::printStackTrace));
    }

    @Override
    public void getAnalyticalFactors() {
        compositeDisposable.add(calculatorRepository.getAnalyticalFactors()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(analyticalFactors -> getSoilFactorsLimits())
                .subscribe(analyticalFactors -> settingsOneView.receiveAnalyticalFactors(analyticalFactors),
                        Throwable::printStackTrace));
    }

    //put new value into Db
    @Override
    public void setSoilFactorsData(SoilFactorsModel soilFactorsModel) {
        calculatorRepository.setSoilFactorsModel(soilFactorsModel);
    }

    @Override
    public void detachView() {
        compositeDisposable.dispose();
        settingsOneView = null;
    }
}
