package com.example.bionintelligence.domain.repositories;

import com.example.bionintelligence.data.model.CultureModel;

import java.util.List;

import io.reactivex.Flowable;

public interface CultureRepository {
    Flowable<List<CultureModel>> getCultureData();
}
