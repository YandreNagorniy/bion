package com.bionagro.bionintelligence.data.repositories;

import com.bionagro.bionintelligence.data.model.CultureModel;
import com.bionagro.bionintelligence.data.source.DatabaseSource;
import com.bionagro.bionintelligence.data.source.DatabaseSourceImpl;
import com.bionagro.bionintelligence.domain.repositories.CultureRepository;

import java.util.List;

import io.reactivex.Flowable;

public class CultureRepositoryImpl implements CultureRepository {
    DatabaseSource databaseSource;

    public CultureRepositoryImpl(DatabaseSourceImpl databaseSource) {
        this.databaseSource = databaseSource;
    }

    @Override
    public Flowable<List<CultureModel>> getCultureData() {
        return databaseSource.getCultureList();
    }
}
