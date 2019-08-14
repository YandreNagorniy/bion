package com.example.bionintelligence.data.repositories;

import com.example.bionintelligence.data.model.CultureModel;
import com.example.bionintelligence.data.source.DatabaseSource;
import com.example.bionintelligence.data.source.DatabaseSourceImpl;
import com.example.bionintelligence.domain.repositories.CultureRepository;

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
