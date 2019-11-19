package com.bionagro.bionintelligence.data.source;

import android.content.Context;
import android.content.SharedPreferences;

import java.lang.ref.WeakReference;

import io.reactivex.subjects.PublishSubject;

public class LocalSourceImpl implements LocalSource {
    private static final String APP_SETTINGS = "app_settings";
    private static final String CULTURE_ID = "culture_id";
    private static final String CULTURE_PRODUCTIVE = "culture_productive";
    private static final String ANALYTICAL_FACTORS_N = "analytical_factors_N";
    private static final String ANALYTICAL_FACTORS_P2O5 = "analytical_factors_P2O5";
    private static final String ANALYTICAL_FACTORS_K2O = "analytical_factors_K2O";
    private SharedPreferences sharedPreferences;

    public LocalSourceImpl(WeakReference<Context> context) {
        sharedPreferences = context.get().getApplicationContext().getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE);
    }

    public static PublishSubject<Integer> subject = PublishSubject.create();

    @Override
    public int getSettingsCultureId() {
        return sharedPreferences.getInt(CULTURE_ID, 1);
    }

    @Override
    public void setSettingsCultureId(int cultureId) {
        sharedPreferences.edit().putInt(CULTURE_ID, cultureId).apply();
    }

    @Override
    public int getSettingsCultureProductive() {
        return sharedPreferences.getInt(CULTURE_PRODUCTIVE, 70);
    }

    @Override
    public void setSettingsCultureProductive(int culture_productive) {
        sharedPreferences.edit().putInt(CULTURE_PRODUCTIVE, culture_productive).apply();
    }

    @Override
    public int getAnalyticalFactorsN() {
        return sharedPreferences.getInt(ANALYTICAL_FACTORS_N, 1);
    }

    @Override
    public void setAnalyticalFactorsN(int analyticalFactors1) {
        sharedPreferences.edit().putInt(ANALYTICAL_FACTORS_N, analyticalFactors1).apply();
//        subject.onNext(analyticalFactors1);
    }

    @Override
    public int getAnalyticalFactorsP2O5() {
        return sharedPreferences.getInt(ANALYTICAL_FACTORS_P2O5, 1);
    }

    @Override
    public void setAnalyticalFactorsP2O5(int analyticalFactors2) {
        sharedPreferences.edit().putInt(ANALYTICAL_FACTORS_P2O5, analyticalFactors2).apply();
//        subject.onNext(analyticalFactors2);
    }

    @Override
    public int getAnalyticalFactorsK2O() {
        return sharedPreferences.getInt(ANALYTICAL_FACTORS_K2O, 1);
    }

    @Override
    public void setAnalyticalFactorsK2O(int analyticalFactors3) {
        sharedPreferences.edit().putInt(ANALYTICAL_FACTORS_K2O, analyticalFactors3).apply();
//        subject.onNext(analyticalFactors3);
    }
}
