package com.example.bionintelligence.presentation.splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.bionintelligence.R;
import com.example.bionintelligence.data.repositories.PutDatabaseRepositoryImpl;
import com.example.bionintelligence.data.source.PutDatabaseSourceImpl;
import com.example.bionintelligence.presentation.main.MainActivity;

import java.lang.ref.WeakReference;

public class SplashActivity extends AppCompatActivity implements SplashView {
    private SplashPresenter splashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splashPresenter = new SplashPresenterImpl(new PutDatabaseRepositoryImpl(
                new PutDatabaseSourceImpl(new WeakReference<>(getApplication()))));
        splashPresenter.attachView(this);
        splashPresenter.checkLocalData();
    }

    @Override
    public void goToMainActivity() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        splashPresenter.detachView();
        super.onDestroy();
    }
}