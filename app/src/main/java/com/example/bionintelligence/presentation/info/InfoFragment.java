package com.example.bionintelligence.presentation.info;

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

import com.example.bionintelligence.R;
import com.example.bionintelligence.databinding.FragmentInfoBinding;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Consumer;

public class InfoFragment extends Fragment {
    //todo используй один фрагмент и внутри адаптера сделай две вьюшки(два элемента) с текстом
    FragmentInfoBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_info, container, false);

        binding.viewpager.setAdapter(new InfoPageAdapter(getFragmentManager()));
        binding.indicator.setViewPager(binding.viewpager);


        return binding.getRoot();
    }
}
