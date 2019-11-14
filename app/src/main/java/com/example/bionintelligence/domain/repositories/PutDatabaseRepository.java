package com.example.bionintelligence.domain.repositories;

import io.reactivex.Completable;

public interface PutDatabaseRepository {
    int getLocalData();

    String getLastTimeVisitedApp();

    void setLastTimeVisitedApp(String lastTimeVisitedApp);

    Completable addStartDataFromDb(int databaseVersion);
}
