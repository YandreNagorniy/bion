package com.example.bionintelligence.presentation.calculator;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;

import com.example.bionintelligence.R;
import com.example.bionintelligence.data.model.CalculatorModel;
import com.example.bionintelligence.data.model.TestCultureModel;
import com.example.bionintelligence.data.model.TestPhasesModel;
import com.example.bionintelligence.data.repositories.CalculatorRepositoryImpl;
import com.example.bionintelligence.data.source.DatabaseSourceImpl;
import com.example.bionintelligence.data.source.LocalSourceImpl;
import com.example.bionintelligence.databinding.FragmentCalculatorBinding;
import com.example.bionintelligence.domain.entities.CalculatorParams;
import com.example.bionintelligence.domain.usecase.GetCalculatorUseCase;
import com.example.bionintelligence.domain.usecase.GetProductiveUseCase;
import com.example.bionintelligence.presentation.culture.CultureActivity;
import com.example.bionintelligence.presentation.custom.ChemistryView;

import java.lang.ref.WeakReference;
import java.util.Objects;

import static android.app.Activity.RESULT_OK;


public class CalculatorFragment extends Fragment implements CalculatorView, View.OnFocusChangeListener {
    private FragmentCalculatorBinding binding;
    private CalculatorPresenter calculatorPresenter;
    private TestCultureModel cultureModel;
    private InputMethodManager inputMethodManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_calculator, container, false);
        calculatorPresenter = new CalculatorPresenterImpl(new GetCalculatorUseCase(
                new CalculatorRepositoryImpl(new LocalSourceImpl(new WeakReference<>(getActivity())), new DatabaseSourceImpl())),
                new CalculatorRepositoryImpl(new LocalSourceImpl(new WeakReference<>(getActivity())), new DatabaseSourceImpl()),
                new GetProductiveUseCase(new CalculatorRepositoryImpl(new LocalSourceImpl(new WeakReference<>(getActivity())), new DatabaseSourceImpl())));
        inputMethodManager = (InputMethodManager) Objects.requireNonNull(getActivity()).getSystemService(Context.INPUT_METHOD_SERVICE);

        changeTextSize();
        calculatorPresenter.attachView(this);
        calculatorPresenter.getStartData();

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.numberPicker.setValueChangedListener((value, action) -> {
            binding.etNewPrdouctive.setVisibility(View.GONE);
            calculatorPresenter.getCalculatorData(value, cultureModel.getCultureId());
            calculatorPresenter.getPhasesData(cultureModel.getPhasesModelList(), value);
        });

        binding.clSelectCulture.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), CultureActivity.class);
            intent.putExtra(getString(R.string.cultureId), cultureModel.getCultureId());                                  //для выделения цвета
            startActivityForResult(intent, 1);
        });

        keyboardCustomListener(binding.chvN);
        keyboardCustomListener(binding.chvP2O5);
        keyboardCustomListener(binding.chvK2O);
        keyboardCustomListener(binding.chvS);
        keyboardCustomListener(binding.chvCaO);
        keyboardCustomListener(binding.chvMgO);
        keyboardCustomListener(binding.chvH2O);
    }

    @Override //data about select culture from CultureActivity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            calculatorPresenter.getCultureModel(data.getIntExtra("cultureId", 1));
            //todo CultureActivity.CULTURE_ID
        }
    }

    private void keyboardCustomListener(ChemistryView view) {
        view.getEtItemValue().setOnFocusChangeListener(this);                                                   //слушатель фокуса
        view.setOnClickListener(v -> {                                                                  //слушатель нажатия на плитку (хим.элемент)
            view.getEtItemValue().requestFocus();
            view.getEtItemValue().setSelection(view.getEtItemValue().getText().length());               //курсор справа
            inputMethodManager.showSoftInput(view.getEtItemValue(), InputMethodManager.SHOW_IMPLICIT);
        });

        view.etItemValue.setOnEditorActionListener((v, actionId, event) -> {                            //слушатель нажатия keyboard "done"
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                int newValue;
                if (TextUtils.isEmpty(v.getText().toString())) {
                    newValue = 0;
                    view.etItemValue.setText("0");
                } else {
                    newValue = Integer.parseInt(v.getText().toString());
                }
                calculatorPresenter.calculateProductive(
                        cultureModel.getCultureId(), view.getTvItemName(), newValue, binding.numberPicker.getValue());
                return false;
            }
            return false;
        });
    }

    @Override
    public void displayCalculatorData(CalculatorModel calculatorModel) {
        binding.setElement(calculatorModel);
    }

    @Override
    public void displayCultureData(TestCultureModel cultureModel) {
        this.cultureModel = cultureModel;
        binding.setCultureModel(cultureModel);
        binding.numberPicker.setMax(cultureModel.getProductiveMax());
        binding.numberPicker.setMin(cultureModel.getProductiveMin());
        binding.numberPicker.setUnit(cultureModel.getProductiveStep());
        binding.numberPicker.setValue(cultureModel.getProductive());
        calculatorPresenter.getCalculatorData(cultureModel.getProductive(), cultureModel.getCultureId());
        calculatorPresenter.getPhasesData(cultureModel.getPhasesModelList(), cultureModel.getProductive());
    }

    @Override
    public void displayPhasesData(TestPhasesModel phasesModel) {
        binding.setPhaseValue(phasesModel);
    }

    @Override
    public void displayNewPhasesData(TestPhasesModel phasesModel) {
        binding.setPhaseValue(phasesModel);
        binding.numberPicker.setValue(phasesModel.getProductive());
    }

    @Override
    public void displayNewProductive(Integer newProductive) {
        binding.etNewPrdouctive.setVisibility(View.VISIBLE);
        if (newProductive > cultureModel.getProductiveMax()) {
            newProductive = cultureModel.getProductiveMax();
        }
        if (newProductive < cultureModel.getProductiveMin()) {
            newProductive = cultureModel.getProductiveMin();
        }
        binding.etNewPrdouctive.setText(String.valueOf(newProductive));
        calculatorPresenter.getCalculatorData(newProductive, cultureModel.getCultureId());
        calculatorPresenter.getNewPhasesData(cultureModel.getPhasesModelList(), newProductive);
    }

    // изменение размера текста
    private void changeTextSize() {
        binding.tvNecessaryBion2.setText(Html.fromHtml(getString(R.string.text_calculator_necessary_bion2)));
    }

    public void refresh() {
        calculatorPresenter.setParamsData(new CalculatorParams(binding.numberPicker.getValue(), cultureModel.getCultureId()));
        calculatorPresenter.getRestartData();
    }

    @Override
    public void displayRefreshData(int productive) {
        binding.numberPicker.setValue(productive);
        calculatorPresenter.getCalculatorData(productive, cultureModel.getCultureId());
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (!hasFocus) {                                                                               //если теряем фокус
          if(Objects.requireNonNull(((AppCompatEditText) v).getText()).toString().equals(""))          //если фокус был снят и текст остался пустым
            ((AppCompatEditText) v).setText("0");
        }
    }

    @Override
    public void onDestroy() {
        calculatorPresenter.setParamsData(new CalculatorParams(binding.numberPicker.getValue(), cultureModel.getCultureId()));
        calculatorPresenter.detachView();
        super.onDestroy();
    }
}
