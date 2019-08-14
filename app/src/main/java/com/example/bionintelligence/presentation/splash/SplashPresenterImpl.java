package com.example.bionintelligence.presentation.splash;

import com.example.bionintelligence.App;
import com.example.bionintelligence.domain.repositories.PutDatabaseRepository;

import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class SplashPresenterImpl implements SplashPresenter {
    private PutDatabaseRepository putDatabaseRepository;
    private SplashView splashView;
    private CompositeDisposable compositeDisposable;

    SplashPresenterImpl(PutDatabaseRepository putDatabaseRepository) {
        this.putDatabaseRepository = putDatabaseRepository;
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void attachView(SplashView splashView) {
        this.splashView = splashView;
    }

    @Override
    public void checkLocalData() {
        int localDatabaseVersion = putDatabaseRepository.getLocalData();
        int currentDatabaseVersion = App.DATABASE_VERSION;

        compositeDisposable.add(Single.just(localDatabaseVersion)
                .flatMapCompletable(localDbVersion ->
                        localDbVersion == currentDatabaseVersion ?
                        Completable.complete() :
                        putDatabaseRepository.addStartDataFromDb(currentDatabaseVersion))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .delay(1500, TimeUnit.MILLISECONDS)
                .subscribe(() -> splashView.goToMainActivity(), Throwable::printStackTrace));
    }

    @Override
    public void detachView() {
        splashView = null;
        compositeDisposable.dispose();
    }
}
