package com.bionagro.bionintelligence.presentation.adapters;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bionagro.bionintelligence.presentation.custom.ChemistryView;
import com.bionagro.bionintelligence.presentation.custom.PhaseView;

// handler custom attributes with Databinding
public class BindingAdapters {

    //заполнение chemistryView значениями с помощью атрибута "item_value"
    @BindingAdapter({"item_value"})
    public static void setItemValue(ChemistryView chemistryView, int value) {
        chemistryView.setItemValue(String.valueOf(value));
    }

    @BindingAdapter({"new_src"})
    public static void setImage(ImageView imageView, int link) {
        Glide.with(imageView.getContext()).load(link).into(imageView);
    }

    @BindingAdapter({"phase_img"})
    public static void setPhaseImage(PhaseView phaseView, int link) {
        phaseView.setPhaseImage(link);
    }

    @BindingAdapter({"phase_value"})
    public static void setPhases(PhaseView phaseView, double value) {
        if (value < 0) {
            phaseView.setVisibility(View.GONE);
        } else {
            phaseView.setVisibility(View.VISIBLE);
            phaseView.set_phaseValue(value);
        }
    }
}
