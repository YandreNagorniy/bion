package com.example.bionintelligence.presentation.settings.one;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class SettingsFragmentOne extends Fragment implements SettingsOneView {
    private FragmentSettings1Binding binding;
    private SettingsOnePresenter settingsOnePresenter;
    private InputMethodManager inputMethodManager;
    private SoilFactorsLimitsModel soilFactorsLimitsModel;
    private AnalyticalFactors analyticalFactors;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings1, container, false);
        settingsOnePresenter = new SettingsOnePresenterImpl(new CalculatorRepositoryImpl(
                new LocalSourceImpl(new WeakReference<>(getActivity())), new DatabaseSourceImpl()));
        settingsOnePresenter.attachView(this);
        settingsOnePresenter.getSoilFactorsData();
//        settingsOnePresenter.getSoilFactorsLimits();
        settingsOnePresenter.getAnalyticalFactors();
        inputMethodManager = (InputMethodManager) Objects.requireNonNull(getActivity()).getSystemService(Context.INPUT_METHOD_SERVICE);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
    }

    @Override
    public void receiveSoilFactorsLimits(SoilFactorsLimitsModel soilFactorsLimitsModel) {
        this.soilFactorsLimitsModel = soilFactorsLimitsModel;
        binding.setLimits(soilFactorsLimitsModel);
    }

    @Override
    public void receiveAnalyticalFactors(AnalyticalFactors analyticalFactors) {
        this.analyticalFactors = analyticalFactors;
    }

    private void soilFactorsClickListener(SoilFactorView view) {
        view.setOnClickListener(v -> {                                                                          //слушатель нажатия на плитку (хим.элемент)
            view.getEtItemValue().requestFocus();
            view.getEtItemValue().setSelection(view.getEtItemValue().getText().length());                       //курсор справа
            inputMethodManager.showSoftInput(view.getEtItemValue(), InputMethodManager.SHOW_IMPLICIT);
        });

        view.setOnEditorActionListener((v, actionId, event) -> {                                                //обработчик нажатия кнопки клавиатуры "Done" для SoilFactorView
            if (actionId == EditorInfo.IME_ACTION_DONE) {                                                       //если на клавиатуре выбрали DONE
                String text = view.getEtItemValue().getText().toString();

                view.getEtItemValue().setText(text);                                                            // добавляем новое значение во view
                binding.setSoilFactor(binding.getSoilFactor());                                                 // обновляем данные из вью
                settingsOnePresenter.setSoilFactorsData(binding.getSoilFactor());                               //отдаем презентеру модель SoilFactor с новым значением
            }
            return false;
        });
    }

    private void soilFactorsClickListener(EditText view) {
        view.setOnEditorActionListener((v, actionId, event) -> {
            //если была нажата кнопка "Done"
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                //получаем значение view и при небходимости форматируем текст
                String text = view.getText().toString();

                view.setText(text);
//                if (TextUtils.isEmpty(text) || text.equals(".")) {
//                    view.setText("0");
//                }

//                //пропускаем через double чтобы отформатировать строку
//                double value = Double.valueOf(view.getText().toString());
//                if (view.getId() == R.id.sf_ZPV) {
//                    view.setText(String.valueOf((int) value));
//                } else {
//                    view.setText(String.valueOf(value));
//                }
//
//                //ставим ограничения на значения
//                if (view.getId() == R.id.sf_pH) {
//                    if (value < 4) {
//                        view.setText("4");
//                    }
//                    if (value > 10) {
//                        view.setText("10");
//                    }
//                }
                settingsOnePresenter.setSoilFactorsData(binding.getSoilFactor());
            }
            return false;
        });
    }

    @Override
    public void onDestroy() {
        settingsOnePresenter.detachView();
        super.onDestroy();
    }
}
