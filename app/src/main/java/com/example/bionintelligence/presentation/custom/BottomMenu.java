package com.example.bionintelligence.presentation.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.example.bionintelligence.R;

public class BottomMenu extends FrameLayout {
    private BottomMenuClickListener listener;

    public BottomMenu(Context context) {
        super(context);
        initialization(context);
    }

    public BottomMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialization(context);
    }

    public BottomMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialization(context);
    }

    private void initialization(Context context) {
        inflate(context, R.layout.layout_bottom_menu, this);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.menu_radio_group);
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.rb1: {
                    listener.onClick(0);
                    break;
                }
                case R.id.rb2: {
                    listener.onClick(1);
                    break;
                }
                case R.id.rb3: {
                    listener.onClick(2);
                    break;
                }
                case R.id.rb4: {
                    listener.onClick(3);
                    break;
                }
            }
        });
    }

    public void setOnBottomMenuClickListener(BottomMenuClickListener listener) {
        this.listener = listener;
    }

    public interface BottomMenuClickListener {
        void onClick(int position);
    }
}
