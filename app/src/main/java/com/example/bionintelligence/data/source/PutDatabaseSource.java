package com.example.bionintelligence.data.source;

import io.reactivex.Completable;

public interface PutDatabaseSource {
    int getLocalDataBaseVersion();

    void setLocalDataBaseVersion(int dataBaseVersion);

    Completable fillDataInDB();
}
