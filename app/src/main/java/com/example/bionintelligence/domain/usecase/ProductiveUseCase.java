package com.example.bionintelligence.domain.usecase;

import io.reactivex.Single;

public abstract class ProductiveUseCase<PARAMS, VALUE> {

    public abstract Single<Integer> execute(PARAMS params);

}
