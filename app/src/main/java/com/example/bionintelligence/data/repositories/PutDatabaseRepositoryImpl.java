package com.example.bionintelligence.data.repositories;

import com.example.bionintelligence.data.source.PutDatabaseSource;
import com.example.bionintelligence.domain.repositories.PutDatabaseRepository;

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
    public Completable addStartDataFromDb(int databaseVersion) {
        return putDatabaseSource.fillDataInDB()
                .doOnError(Throwable::printStackTrace)
                .doOnComplete(() -> putDatabaseSource.setLocalDataBaseVersion(databaseVersion));
    }
}
