package com.example.bionintelligence.presentation.settings.one;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.bionintelligence.R;
import com.example.bionintelligence.data.model.SoilFactorsLimitsModel;
import com.example.bionintelligence.data.model.SoilFactorsModel;
import com.example.bionintelligence.data.pojo.AnalyticalFactors;
import com.example.bionintelligence.data.repositories.CalculatorRepositoryImpl;
import com.example.bionintelligence.data.source.DatabaseSourceImpl;
import com.example.bionintelligence.data.source.LocalSourceImpl;
import com.example.bionintelligence.databinding.FragmentSettings1Binding;
import com.example.bionintelligence.presentation.custom.SoilFactorView;

import java.lang.ref.WeakReference;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class SettingsFragmentOne extends Fragment implements SettingsOneView, View.OnFocusChangeListener {
    private FragmentSettings1Binding binding;
    private SettingsOnePresenter settingsOnePresenter;
    private InputMethodManager inputMethodManager;
    public AnalyticalFactors analyticalFactors;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings1, container, false);
        settingsOnePresenter = new SettingsOnePresenterImpl(new CalculatorRepositoryImpl(
                new LocalSourceImpl(new WeakReference<>(getActivity())), new DatabaseSourceImpl()));

        settingsOnePresenter.attachView(this);
        settingsOnePresenter.onStart();
        settingsOnePresenter.getAnalyticalFactors();

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inputMethodManager = (InputMethodManager) Objects.requireNonNull(getActivity()).getSystemService(Context.INPUT_METHOD_SERVICE);
        soilFactorsClickListener(binding.sfvN);
        soilFactorsClickListener(binding.sfvP2O5);
        soilFactorsClickListener(binding.sfvK2O);
        soilFactorsClickListener(binding.sfvCaO);
        soilFactorsClickListener(binding.sfvMgO);
        soilFactorsClickListener(binding.sfvS);
        soilFactorsClickListener(binding.sfvZn);
        soilFactorsClickListener(binding.sfvCu);
        soilFactorsClickListener(binding.sfvMn);
        soilFactorsClickListener(binding.sfvCo);
        soilFactorsClickListener(binding.sfvMo);
        soilFactorsClickListener(binding.sfvB);
        soilFactorsClickListener(binding.sfvFe);
        soilFactorsClickListener(binding.sfG);
        soilFactorsClickListener(binding.sfPH);
        soilFactorsClickListener(binding.sfZPV);
    }

    @Override
    public void displaySoilFactors(SoilFactorsModel soilFactors) {
        binding.setSoilFactor(soilFactors);
        Completable.complete()
                .delay(100, TimeUnit.MILLISECONDS)
                .doOnComplete(() -> settingsOnePresenter.setSoilFactorsData(binding.getSoilFactor()))
                .subscribe();
    }

    @Override
    public void receiveSoilFactorsLimits(SoilFactorsLimitsModel soilFactorsLimitsModel) {
        binding.setLimits(soilFactorsLimitsModel);
    }

    @Override
    public void receiveAnalyticalFactors(AnalyticalFactors analyticalFactors) {
        binding.setAnalyticalFactors(analyticalFactors);
        settingsOnePresenter.getSoilFactorsData();
    }

    private void soilFactorsClickListener(SoilFactorView view) {
        view.getEtItemValue().setOnFocusChangeListener(this);                                                   //слушатель фокуса
        view.setOnClickListener(v -> {                                                                          //слушатель нажатия на плитку (хим.элемент)
            view.getEtItemValue().requestFocus();
            view.getEtItemValue().setSelection(view.getEtItemValue().getText().length());                       //курсор справа
            inputMethodManager.showSoftInput(view.getEtItemValue(), InputMethodManager.SHOW_IMPLICIT);
        });

        view.setOnEditorActionListener((v, actionId, event) -> {                                                //обработчик нажатия кнопки клавиатуры "Done" для SoilFactorView
            if (actionId == EditorInfo.IME_ACTION_DONE) {                                                       //если на клавиатуре выбрали DONE
                onFocusChange(view, false);
            }
            return false;
        });
    }

    private void soilFactorsClickListener(EditText view) {
        view.setOnFocusChangeListener(this);
        view.setOnEditorActionListener((v, actionId, event) -> {
            //если была нажата кнопка "Done"
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                onFocusChange(view, false);
            }
            return false;
        });
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (!hasFocus) {                                                                               //если теряем фокус
            binding.setSoilFactor(binding.getSoilFactor());
            Completable.complete()
                    .delay(100, TimeUnit.MILLISECONDS)
                    .doOnComplete(() -> settingsOnePresenter.setSoilFactorsData(binding.getSoilFactor()))
                    .subscribe();
        }
    }

    @Override
    public void refresh(AnalyticalFactors analyticalFactors) {
        receiveAnalyticalFactors(analyticalFactors);
    }

    @Override
    public void onDestroy() {
        settingsOnePresenter.detachView();
        super.onDestroy();
    }
}
