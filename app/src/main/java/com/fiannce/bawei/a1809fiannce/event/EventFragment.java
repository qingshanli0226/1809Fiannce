package com.fiannce.bawei.a1809fiannce.event;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.fiannce.bawei.a1809fiannce.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class EventFragment extends Fragment implements View.OnTouchListener {
    private SlideView slideView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);

         View rootView = inflater.inflate(R.layout.frament_event, container,false);
         slideView = rootView.findViewById(R.id.slideView);

         slideView.setOnTouchListener(this);


         return rootView;
    }

    int lastX,lastY;
    @Override
    public boolean onTouch(View v, MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = (int) ev.getRawX();
                lastY = (int) ev.getRawY();
                slideView.getParent().requestDisallowInterceptTouchEvent(true);//当收到down事件时，内部View请求ViewPager不要拦截后面的事件

                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("LQS", "lastX="+lastX+" lastY="+lastY + " x=" + ev.getRawX() + " y=" + ev.getRawY());
                if ((lastX<50 || lastX>500)&&(Math.abs(ev.getRawY()-lastY)+20<Math.abs(ev.getRawX()-lastX))) {
                    slideView.getParent().requestDisallowInterceptTouchEvent(false);//当收到down事件时，内部View请求ViewPager不要拦截后面的事件
                } else {
                    slideView.getParent().requestDisallowInterceptTouchEvent(true);//当收到down事件时，内部View请求ViewPager不要拦截后面的事件
                }
        }

        return true;
    }
}
