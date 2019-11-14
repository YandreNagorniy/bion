package com.example.bionintelligence.presentation.splash;

import android.annotation.SuppressLint;

import com.example.bionintelligence.App;
import com.example.bionintelligence.domain.repositories.PutDatabaseRepository;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

        compositeDisposable.add(getCurrentDatabaseVersion()
                .flatMapCompletable(dbVersion ->
                        dbVersion < 0 ?
                                Completable.complete() :
                                putDatabaseRepository.addStartDataFromDb(dbVersion))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .delay(1000, TimeUnit.MILLISECONDS)
                .subscribe(() -> splashView.goToMainActivity(), Throwable::printStackTrace));
    }

    private Single<Integer> getCurrentDatabaseVersion() {
        int localDatabaseVersion = putDatabaseRepository.getLocalData();
        int currentDatabaseVersion = App.DATABASE_VERSION;

        Date currentDate = Calendar.getInstance().getTime();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String stringCurrentDate = df.format(currentDate);
        String lastTimeVisitedApp = putDatabaseRepository.getLastTimeVisitedApp();

        if (stringCurrentDate.equals(lastTimeVisitedApp)) {
            if (currentDatabaseVersion == localDatabaseVersion)
                currentDatabaseVersion = -1;
        } else
            putDatabaseRepository.setLastTimeVisitedApp(stringCurrentDate);

        return Single.just(currentDatabaseVersion);
    }

    @Override
    public void detachView() {
        splashView = null;
        compositeDisposable.dispose();
    }
}
