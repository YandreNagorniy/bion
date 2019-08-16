package com.example.bionintelligence.presentation.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.bionintelligence.R;

public class SoilFactorView extends FrameLayout {
    private TextView tvItemName;
    private EditText etItemValue;

    public SoilFactorView(Context context) {
        this(context, null);
    }

    public SoilFactorView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SoilFactorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.soilfactors_layout, this);

        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.SoilFactorView);
        String text = array.getString(R.styleable.SoilFactorView_item_text);
        String name = array.getString(R.styleable.SoilFactorView_item_name);
        int value = array.getInt(R.styleable.SoilFactorView_sf_value, 0);

        //todo используй датабайдинг
        TextView tvItemText = findViewById(R.id.tv_itemText);
        tvItemName = findViewById(R.id.tv_itemName);
        etItemValue = findViewById(R.id.et_itemValue);

        switch (name != null ? name : "") {
            case "P2O5":
                setItemName2(new SpannableString(name));
                break;
            case "K2O":
                setItemName1(new SpannableString(name));
                break;
            case "H2O":
                setItemName1(new SpannableString(name));
                break;
            default:
                tvItemName.setText(name);
        }

        tvItemText.setText(text);
        etItemValue.setText(String.valueOf(value));
        array.recycle();
    }

    public void setOnEditorActionListener(TextView.OnEditorActionListener listener) {
        etItemValue.setOnEditorActionListener(listener);
    }

    public void setItemValue(String element) {
        etItemValue.setText(element);
    }

    public double getItemValue() {
        try {
            return Double.valueOf(etItemValue.getText().toString());
        } catch (NumberFormatException n) {
            return 0.0;
        }
    }

    // изменение размера текста для H2O и K2O
    public void setItemName1(Spannable text) {
        text.setSpan(new RelativeSizeSpan(0.6f), 1, 2, 0);
        tvItemName.setText(text, TextView.BufferType.SPANNABLE);
    }

    // изменение размера текста для P2O5
    public void setItemName2(Spannable text) {
        text.setSpan(new RelativeSizeSpan(0.6f), 1, 2, 0);
        text.setSpan(new RelativeSizeSpan(0.6f), 3, 4, 0);
        tvItemName.setText(text, TextView.BufferType.SPANNABLE);
    }

    public String getTvItemName() {
        return tvItemName.getText().toString();
    }

    public EditText getEtItemValue() {
        return etItemValue;
    }

    public void setEtItemValue(EditText etItemValue) {
        this.etItemValue = etItemValue;
    }
}
