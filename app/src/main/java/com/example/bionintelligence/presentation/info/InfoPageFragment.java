package com.example.bionintelligence.presentation.info;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bionintelligence.R;
import com.example.bionintelligence.databinding.FragmentInfoChildBinding;

public class InfoPageFragment extends Fragment {

    static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";
    int pageNumber;
    FragmentInfoChildBinding binding;

    static InfoPageFragment newInstance(int page) {
        InfoPageFragment infoPageFragment = new InfoPageFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(ARGUMENT_PAGE_NUMBER, page);
        infoPageFragment.setArguments(arguments);
        return infoPageFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNumber = getArguments() != null ? getArguments().getInt(ARGUMENT_PAGE_NUMBER) : 1;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_info_child, container, false);
        binding.tvPageText.setTextSize(15);

        if (pageNumber == 0) {
            binding.tvPageText.setText(R.string.text_info1);
        } else {
            binding.tvPageText.setText(R.string.text_info2);
        }
        return binding.getRoot();
    }
}
