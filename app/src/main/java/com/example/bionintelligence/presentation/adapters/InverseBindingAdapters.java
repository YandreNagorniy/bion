package com.example.bionintelligence.presentation.adapters;

import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.databinding.InverseBindingListener;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.bionintelligence.presentation.custom.SoilFactorView;

//two way binding from soilFactorsValue
public class InverseBindingAdapters {

    @BindingAdapter(value = "sf_valueAttrChanged")
    public static void setListener(SoilFactorView view, final InverseBindingListener listener) {
        if (listener != null) {
            view.getEtItemValue().addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    listener.onChange();
                }
            });
        }
    }

    @BindingAdapter(value = "sf_valueAttrChanged")
    public static void setListener(EditText view, final InverseBindingListener listener) {
        if (listener != null) {
            view.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    listener.onChange();
                }
            });
        }
    }

    @InverseBindingAdapter(attribute = "sf_value")
    public static double getItemValue(SoilFactorView view) {
        return view.getItemValue();
    }

    @InverseBindingAdapter(attribute = "sf_value")
    public static double getItemValue(EditText view) {
        try {
            return Double.valueOf(view.getText().toString());
        } catch (NumberFormatException n) {
            return 0.0;
        }
    }

    @InverseBindingAdapter(attribute = "sf_value")
    public static int getItemValueInteger(EditText view) {
        try {
            double value = Double.parseDouble(view.getText().toString());
            return (int) value;
        } catch (NumberFormatException n) {
            return 0;
        }
    }

    @BindingAdapter({"sf_value", "sf_min", "sf_max"})
    public static void setItemValue(SoilFactorView view, double value, double min, double max) {
        if (min != 0) {
            if (value < min) value = min;
            else if (value > max) value = max;
        }
        view.setItemValue(String.valueOf(value));
    }

    @BindingAdapter({"sf_value", "sf_min", "sf_max"})
    public static void setItemValue(EditText view, double value, double min, double max) {
        if (min != 0) {
            if (value < min) value = min;
            else if (value > max) value = max;
        }
        view.setText(String.valueOf(value));
    }
}
