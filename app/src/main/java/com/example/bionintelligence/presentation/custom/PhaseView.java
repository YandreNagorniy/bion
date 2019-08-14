package com.example.bionintelligence.presentation.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.bionintelligence.R;

public class PhaseView extends FrameLayout {
    private EditText et_phaseValue;
    private ImageView et_phaseImage;

    public PhaseView(Context context) {
        this(context, null);
    }

    public PhaseView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PhaseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.phase_layout, this);

        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.PhaseView);

        Float phaseValue = array.getFloat(R.styleable.PhaseView_phase_value, 0);

        et_phaseValue = findViewById(R.id.et_phaseValue);
        et_phaseImage = findViewById(R.id.iv_phaseImage);

        et_phaseValue.setText(String.valueOf(phaseValue));
        array.recycle();
    }

    public ImageView getPhaseImage() {
        return et_phaseImage;
    }

    public void setPhaseImage(int link) {
        //todo при link==0 нужно загружать картинку которая отслеживает ошибку (метод .error(R.drawable...))
        Glide.with(et_phaseImage.getContext())
                .load(link)
                .into(et_phaseImage);
    }

    public EditText get_phaseValue() {
        return et_phaseValue;
    }

    public void set_phaseValue(double value) {
        this.et_phaseValue.setText(String.valueOf(value));
    }
}
