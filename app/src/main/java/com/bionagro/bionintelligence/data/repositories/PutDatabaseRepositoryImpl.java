package com.bionagro.bionintelligence.data.repositories;

import com.bionagro.bionintelligence.data.source.PutDatabaseSource;
import com.bionagro.bionintelligence.domain.repositories.PutDatabaseRepository;

import io.reactivex.Completable;

public class PutDatabaseRepositoryImpl implements PutDatabaseRepository {
    private PutDatabaseSource putDatabaseSource;

    public PutDatabaseRepositoryImpl(PutDatabaseSource putDatabaseSource) {
        this.putDatabaseSource = putDatabaseSource;
    }

    @Override
    public int getLocalData() {
        return putDatabaseSource.getLocalDataBaseVersion();
    }

    @Override
    public String getLastTimeVisitedApp() {
        return putDatabaseSource.getLastTimeVisitedApp();
    }

    @Override
    public void setLastTimeVisitedApp(String lastTimeVisitedApp) {
        putDatabaseSource.setLastTimeVisitedApp(lastTimeVisitedApp);
    }

    @Override
    public Completable addStartDataFromDb(int databaseVersion) {
        return putDatabaseSource.fillDataInDB()
                .doOnError(Throwable::printStackTrace)
                .doOnComplete(() -> putDatabaseSource.setLocalDataBaseVersion(databaseVersion));
    }
}
