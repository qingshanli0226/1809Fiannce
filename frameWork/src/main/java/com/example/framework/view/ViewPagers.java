package com.example.framework.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class ViewPagers extends ViewPager {

    private int lastX = 0;

    public ViewPagers(@NonNull Context context) {
        super(context);
    }

    public ViewPagers(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = (int) ev.getRawX();
            case MotionEvent.ACTION_MOVE:
                if (lastX > 500 && ev.getRawX() < lastX){
                    return false;
                }
        }

        return false;
    }
}
