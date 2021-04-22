package com.example.framework.myview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class MyText extends androidx.appcompat.widget.AppCompatTextView {

    public MyText(Context context) {
        super(context);
    }

    public MyText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean isFocused() {
        return true;
    }
}
