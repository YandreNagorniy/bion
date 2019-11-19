package com.bionagro.bionintelligence.domain.repositories;

import com.bionagro.bionintelligence.data.model.CultureModel;

import java.util.List;

import io.reactivex.Flowable;

public interface CultureRepository {
    Flowable<List<CultureModel>> getCultureData();
}
