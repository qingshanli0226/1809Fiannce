package com.example.a1809fiannce.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class PageView extends ViewPager {

    public PageView(@NonNull Context context) {
        super(context);
    }

    public PageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    int ScX,ScY;

//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        switch (ev.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                ScX= (int) ev.getRawX();
//                ScY= (int) ev.getRawY();
//
//                break;
//            case MotionEvent.ACTION_MOVE:
//                if (ScX<50||ScX>500){
//                    return true;
//                }
//        }
//        return super.onInterceptTouchEvent(ev);
//    }
}
