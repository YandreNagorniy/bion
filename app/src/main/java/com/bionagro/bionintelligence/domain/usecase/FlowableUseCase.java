package com.bionagro.bionintelligence.domain.usecase;

import io.reactivex.Flowable;

public abstract class FlowableUseCase<PARAMS, VALUE> {

    public abstract Flowable<VALUE> execute(PARAMS params);

}
