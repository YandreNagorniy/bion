package com.bionagro.bionintelligence.presentation.splash;

public interface SplashPresenter {

    void attachView(SplashView splashView);

    void checkLocalData();

    void detachView();
}
