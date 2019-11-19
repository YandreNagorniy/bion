package com.bionagro.bionintelligence.presentation.culture;

import com.bionagro.bionintelligence.data.repositories.CultureRepositoryImpl;
import com.bionagro.bionintelligence.domain.repositories.CultureRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class CulturePresenterImpl implements CulturePresenter {
    private CultureView cultureView;
    private CultureRepository cultureRepository;
    private CompositeDisposable compositeDisposable;

    CulturePresenterImpl(CultureRepositoryImpl cultureRepository) {
        this.cultureRepository = cultureRepository;
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void attachView(CultureView cultureView) {
        this.cultureView = cultureView;
    }

    @Override
    public void getCultureData() {
        compositeDisposable.add(cultureRepository.getCultureData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(cultureModelList ->
                                cultureView.displayData(cultureModelList),
                        Throwable::printStackTrace));
    }

    @Override
    public void detachView() {
        compositeDisposable.clear();
        cultureView = null;
    }
}
