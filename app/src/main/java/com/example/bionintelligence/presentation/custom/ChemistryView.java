package com.example.bionintelligence.presentation.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.RelativeSizeSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.bionintelligence.R;

public class ChemistryView extends FrameLayout {
    private TextView tvItemName;
    public EditText etItemValue;
    public int itemValue;

    public ChemistryView(Context context) {
        this(context, null);
    }

    public ChemistryView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChemistryView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        inflate(context, R.layout.chemistry_layout, this);

        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.ChemistryView);
        String text = array.getString(R.styleable.ChemistryView_item_text);
        String name = array.getString(R.styleable.ChemistryView_item_name);
        itemValue = array.getInt(R.styleable.ChemistryView_item_value, 0);

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
        etItemValue.setText(String.valueOf(itemValue));
        array.recycle();
    }

    public void setItemValue(String element) {
        etItemValue.setText(element);
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

    public void setTextChangedListener(TextWatcher textWatcher) {
        etItemValue.addTextChangedListener(textWatcher);
    }

    public String getTvItemName() {
        return (String) tvItemName.getText().toString();
    }

    public void setTvItemName(TextView tvItemName) {
        this.tvItemName = tvItemName;
    }

    public EditText getEtItemValue() {
        return etItemValue;
    }
}
