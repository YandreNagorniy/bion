package com.example.bionintelligence.domain.repositories;

import io.reactivex.Completable;

public interface PutDatabaseRepository {
    int getLocalData();

    Completable addStartDataFromDb(int databaseVersion);
}
