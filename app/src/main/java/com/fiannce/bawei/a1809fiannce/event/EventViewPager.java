package com.fiannce.bawei.a1809fiannce.event;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
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
        //什么时候拦截事件，什么时候不拦截, Viewpager在屏幕边缘部分滑动时，拦截，否则不拦截


        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = (int) ev.getRawX();
                lastY = (int) ev.getRawY();

                break;
            case MotionEvent.ACTION_MOVE:

                Log.d("LQS", "lastX="+lastX+" lastY="+lastY + " x=" + ev.getRawX() + " y=" + ev.getRawY());

                if ((lastX<50 || lastX>500)&&(Math.abs(ev.getRawY()-lastY)+20<Math.abs(ev.getRawX()-lastX))) {
                    return true;
                } else {
                    return false;
                }

        }

        return super.onInterceptTouchEvent(ev);
    }
}
