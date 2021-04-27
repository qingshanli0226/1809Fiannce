package com.fiannce.framework.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class CustomViewPager extends ViewPager {
    int x = 0;
    boolean aBoolean = false;

    public CustomViewPager(@NonNull Context context) {
        super(context);
    }

    public CustomViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x = (int) getX();
                if (x >= getMeasuredWidth() - 700) {
                    aBoolean = true;
                    return false;
                } else {
                    aBoolean = false;
                    return true;
                }
            case MotionEvent.ACTION_MOVE:
                if (aBoolean) {
                    return false;
                } else {
                    return true;
                }
        }
        return false;
    }
}
