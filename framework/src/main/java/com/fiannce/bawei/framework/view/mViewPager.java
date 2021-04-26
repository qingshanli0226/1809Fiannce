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
                int x = (int) e.getX();
                int y = (int) e.getY();
//                LogUtils.e(x+"aaa"+getMeasuredWidth());
                if (x>=getMeasuredWidth()-500){
                    mboolean = true;
                    return false;
                }else {
                    mboolean=false;
                    return true;
                }
            case MotionEvent.ACTION_MOVE:
                if (mboolean){
                    return false;
                }else {
                    return true;
                }
            case MotionEvent.ACTION_UP:
                break;
        }
        return false;
    }
}
