package com.fiannce.bawei.framework.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.blankj.utilcode.util.LogUtils;

public class mViewPager extends ViewPager {
    private boolean  mboolean;
    private int   lofitY;
    private int   lofitX;
    public mViewPager(@NonNull Context context) {
        super(context);
    }

    public mViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        switch (e.getAction()){
            case MotionEvent.ACTION_DOWN:
                lofitX = (int) e.getX();
                lofitY = (int) e.getY();
            case MotionEvent.ACTION_MOVE:
                int y1 = (int) getY();
                if (y1!=lofitY||lofitX>=getMeasuredWidth()-500){
                    return false;
                }else {
                    LogUtils.e("aaa");
                    return true;
                }
            case MotionEvent.ACTION_UP:
                break;
        }
        return false;
    }
}
