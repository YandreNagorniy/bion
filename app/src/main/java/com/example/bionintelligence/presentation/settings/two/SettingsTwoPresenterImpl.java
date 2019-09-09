package com.example.bionintelligence.presentation.settings.two;

import android.util.Log;

import com.example.bionintelligence.data.pojo.AnalyticalFactors;
import com.example.bionintelligence.data.repositories.CalculatorRepositoryImpl;
import com.example.bionintelligence.domain.repositories.CalculatorRepository;
import com.example.bionintelligence.presentation.settings.one.SettingsFragmentOne;
import com.example.bionintelligence.presentation.settings.one.SettingsOneView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class SettingsTwoPresenterImpl implements SettingsTwoPresenter {
    private SettingsTwoView settingsTwoView;
    private CalculatorRepository calculatorRepository;
    private CompositeDisposable compositeDisposable;
    private SettingsOneView settingsOneView;

    SettingsTwoPresenterImpl(CalculatorRepositoryImpl calculatorRepository) {
        this.calculatorRepository = calculatorRepository;
        this.compositeDisposable = new CompositeDisposable();
        settingsOneView = new SettingsFragmentOne();
    }

    @Override
    public void attachView(SettingsFragmentTwo settingsTwoView) {
        this.settingsTwoView = settingsTwoView;
    }

    @Override
    public void getAnalyticalFactors() {
        compositeDisposable.add(calculatorRepository.getAnalyticalFactors()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(analyticalFactors -> settingsTwoView.displayAnalyticalFactors(analyticalFactors),
                        throwable -> Log.d("throwable", String.valueOf(throwable))));
    }

    @Override
    public void saveAnalyticalFactors(AnalyticalFactors analyticalFactors) {
        calculatorRepository.setAnalyticalFactors(analyticalFactors);
        settingsOneView.refresh();
    }

    @Override
    public void detachView() {
        settingsTwoView = null;
        compositeDisposable.dispose();
    }
}
