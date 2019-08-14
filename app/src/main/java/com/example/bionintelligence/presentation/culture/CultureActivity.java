package com.example.bionintelligence.presentation.culture;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.example.bionintelligence.R;
import com.example.bionintelligence.data.model.CultureModel;
import com.example.bionintelligence.data.repositories.CultureRepositoryImpl;
import com.example.bionintelligence.data.source.DatabaseSourceImpl;
import com.example.bionintelligence.databinding.ActivityCultureBinding;

import java.util.List;
import java.util.Objects;

public class CultureActivity extends AppCompatActivity implements CultureView {
    private CulturePresenter culturePresenter;
    private ActivityCultureBinding binding;
    private Intent intent;
    private int selectcultureId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_culture);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_culture);
        binding.rvCulture.setLayoutManager(new LinearLayoutManager(this));

        handleToolbar();

        culturePresenter = new CulturePresenterImpl(new CultureRepositoryImpl(new DatabaseSourceImpl()));
        culturePresenter.attachView(this);
        culturePresenter.getCultureData();
        intent = getIntent();
        selectcultureId = intent.getIntExtra(getString(R.string.cultureId), 0);
    }

    @Override
    public void displayData(List<CultureModel> cultureModelList) {
        CultureRvAdapter adapter = new CultureRvAdapter(cultureModelList, selectcultureId);
        binding.rvCulture.setAdapter(adapter);

        adapter.setOnItemClickListener((position, v) -> {
            //todo вынеси стринги в статические переменные
            intent.putExtra("cultureId", cultureModelList.get(position).getId());
            intent.putExtra("cultureName", cultureModelList.get(position).getCultureName());
            setResult(RESULT_OK, intent);
            finish();
        });

    }

    private void handleToolbar() {
        //todo исправить
        setSupportActionBar(binding.cultureToolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Objects.requireNonNull(binding.cultureToolbar.getNavigationIcon()).setColorFilter(
                getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onDestroy() {
        culturePresenter.detachView();
        super.onDestroy();
    }
}
