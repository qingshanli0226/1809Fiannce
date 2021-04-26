package com.finance.zg6.view;


import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;
//跑马灯

@SuppressLint("AppCompatCustomView")
public class horseRaceLampView extends TextView {

    public horseRaceLampView(Context context) {
        super(context);
    }

    public horseRaceLampView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public horseRaceLampView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public horseRaceLampView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean isFocused() {
        return true;
    }
}
