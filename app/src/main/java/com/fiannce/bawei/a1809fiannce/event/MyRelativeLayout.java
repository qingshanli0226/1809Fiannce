package com.fiannce.bawei.a1809fiannce.event;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

public class MyRelativeLayout extends RelativeLayout {
    public MyRelativeLayout(Context context) {
        super(context);
        init(context, null);
    }

    public MyRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MyRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attributeSet) {

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("MyRelativeLayout LQS", "收到Down事件:" + ev.getRawX()+ev.getRawY());
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("MyRelativeLayout LQS", "收到Move事件");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("MyRelativeLayout LQS", "收到Up事件");
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("LQS onTouchEvent", "MyRelativeLayout 收到Down事件:" + ev.getRawX()+ev.getRawY());
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("LQS onTouchEvent", "MyRelativeLayout 收到Move事件");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("LQS onTouchEvent", "MyRelativeLayout 收到Up事件");
                break;
        }
        super.onTouchEvent(ev);
        return true;
    }

    //事件拦截方法，返回true代表着拦截该事件，返回false代表的是不拦截
    private int lastY;
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
         super.onInterceptTouchEvent(ev);
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastY = (int) ev.getY();
                Log.d("LQS ", "onInterceptTouchEvent MyRelativeLayout 收到Down事件:" + ev.getRawX()+ev.getRawY());
                return false;
            case MotionEvent.ACTION_MOVE:
                Log.d("LQS ", "onInterceptTouchEvent MyRelativeLayout 收到Move事件");
                if (ev.getY()-lastY>100) {
                    return true;
                } else {
                    return false;
                }
            case MotionEvent.ACTION_UP:
                Log.d("LQS", "onInterceptTouchEvent MyRelativeLayout 收到Up事件");
                break;
        }

         return false;
    }
}
