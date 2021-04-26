package com.example.designed.event;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class EventViewPager extends ViewPager {
    public EventViewPager(@NonNull Context context) {
        super(context);
    }

    public EventViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    int lastX;
    int lastY;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:

                lastX = (int) ev.getRawX();
                lastY = (int) ev.getRawY();

                break;

            case MotionEvent.ACTION_MOVE:

                if (lastX < 50 || lastX>500&&(Math.abs(ev.getRawY()-lastY)+20<Math.abs(ev.getRawX()-lastX))){

                    return true;
                }else {
                    return false;
                }


        }

        return super.onInterceptTouchEvent(ev);
    }
}
