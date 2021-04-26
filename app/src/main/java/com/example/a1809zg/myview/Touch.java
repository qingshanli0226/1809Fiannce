package com.example.a1809zg.myview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

public class Touch extends RelativeLayout {
    public Touch(Context context) {
        this(context,null);
    }

    public Touch(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public Touch(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
               Log.d("Touch", "按下");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("Touch", "移动");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("Touch", "抬起");
                break;
        }
        return super.dispatchTouchEvent(ev);

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("6666", "MyRelativeLayout 收到Down事件:" + ev.getRawX()+ev.getRawY());
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("6666", "MyRelativeLayout 收到Move事件");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("6666", "MyRelativeLayout 收到Up事件");
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
                Log.d("6666 ", "onInterceptTouchEvent MyRelativeLayout 收到Down事件:" + ev.getRawX()+ev.getRawY());
                return false;
            case MotionEvent.ACTION_MOVE:
                Log.d("6666 ", "onInterceptTouchEvent MyRelativeLayout 收到Move事件");
                if (ev.getY()-lastY>100) {
                    return true;
                } else {
                    return false;
                }
            case MotionEvent.ACTION_UP:
                Log.d("6666", "onInterceptTouchEvent MyRelativeLayout 收到Up事件");
                break;
        }

        return false;
    }
}
