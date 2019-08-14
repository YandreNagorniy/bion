package com.example.bionintelligence.presentation.culture;

//todo сделать главный презентер с повторяющимеся методами
public interface CulturePresenter {
    void attachView(CultureView cultureView);

    void detachView();

    void getCultureData();
}
