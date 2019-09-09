package com.example.bionintelligence.presentation.settings.two;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bionintelligence.R;
import com.example.bionintelligence.data.pojo.AnalyticalFactors;
import com.example.bionintelligence.data.repositories.CalculatorRepositoryImpl;
import com.example.bionintelligence.data.source.DatabaseSourceImpl;
import com.example.bionintelligence.data.source.LocalSourceImpl;
import com.example.bionintelligence.databinding.FragmentSettings2Binding;

import java.lang.ref.WeakReference;

public class SettingsFragmentTwo extends Fragment implements SettingsTwoView {
    private FragmentSettings2Binding binding;
    private SettingsTwoPresenter settingsTwoPresenter;
    private AnalyticalFactors analyticalFactors;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings2, container, false);
        settingsTwoPresenter = new SettingsTwoPresenterImpl(new CalculatorRepositoryImpl(
                new LocalSourceImpl(new WeakReference<>(getActivity())), new DatabaseSourceImpl()));
        settingsTwoPresenter.attachView(this);
        settingsTwoPresenter.getAnalyticalFactors();

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.rgN.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.rb_mineralN:
                    analyticalFactors.setAfN(1);
                    break;
                case R.id.rb_shelochN:
                    analyticalFactors.setAfN(2);
                    break;
                default:
                    analyticalFactors.setAfN(3);
            }
            settingsTwoPresenter.saveAnalyticalFactors(analyticalFactors);
        });
        binding.rgP2O5.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.rb_machiginP2O5:
                    analyticalFactors.setAfP2O5(1);
                    break;
                case R.id.rb_chirikovP2O5:
                    analyticalFactors.setAfP2O5(2);
                    break;
                default:
                    analyticalFactors.setAfP2O5(3);
            }
            settingsTwoPresenter.saveAnalyticalFactors(analyticalFactors);
        });
        binding.rgK2O.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.rb_machiginK2O:
                    analyticalFactors.setAfK2O(1);
                    break;
                case R.id.rb_chirikovK2O:
                    analyticalFactors.setAfK2O(2);
                    break;
                default:
                    analyticalFactors.setAfK2O(3);
            }
            settingsTwoPresenter.saveAnalyticalFactors(analyticalFactors);
        });
    }

    @Override
    public void displayAnalyticalFactors(AnalyticalFactors analyticalFactors) {                         // отображене нужного RadioButton
        this.analyticalFactors = analyticalFactors;
        switch (analyticalFactors.getAfN()) {
            case 1: {
                binding.rbMineralN.setChecked(true);
                binding.rbMineralN.jumpDrawablesToCurrentState();                                       //решение проблемы неполного заполнения rb
            }
            break;
            case 2: {
                binding.rbShelochN.setChecked(true);
                binding.rbShelochN.jumpDrawablesToCurrentState();
            }
            break;
            case 3: {
                binding.rbLiteN.setChecked(true);
                binding.rbLiteN.jumpDrawablesToCurrentState();
            }
            break;
        }
        switch (analyticalFactors.getAfP2O5()) {
            case 1: {
                binding.rbMachiginP2O5.setChecked(true);
                binding.rbMachiginP2O5.jumpDrawablesToCurrentState();
            }
            break;
            case 2: {
                binding.rbChirikovP2O5.setChecked(true);
                binding.rbChirikovP2O5.jumpDrawablesToCurrentState();
            }
            break;
            case 3: {
                binding.rbKirsanovP2O5.setChecked(true);
                binding.rbKirsanovP2O5.jumpDrawablesToCurrentState();
            }
            break;
        }
        switch (analyticalFactors.getAfK2O()) {
            case 1: {
                binding.rbMachiginK2O.setChecked(true);
                binding.rbMachiginK2O.jumpDrawablesToCurrentState();
            }
            break;
            case 2: {
                binding.rbChirikovK2O.setChecked(true);
                binding.rbChirikovK2O.jumpDrawablesToCurrentState();
            }
            break;
            case 3: {
                binding.rbKirsanovK2O.setChecked(true);
                binding.rbKirsanovK2O.jumpDrawablesToCurrentState();
            }
            break;
        }

    }

    @Override
    public void onDestroy() {
        settingsTwoPresenter.detachView();
        super.onDestroy();
    }
}