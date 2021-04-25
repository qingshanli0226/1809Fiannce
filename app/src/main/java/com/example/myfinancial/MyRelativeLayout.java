package com.example.myfinancial;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

public class MyRelativeLayout extends RelativeLayout {
    public MyRelativeLayout(Context context) {
        super(context);
    }

    public MyRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.d("ZKH","activity 收到down");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("ZKH","activity move");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("ZKH","activity up");
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.d("ZKH onTouchEvent","MyAvtivity  activity 收到down");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("ZKH onTouchEvent","MyAvtivity activity move");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("ZKH onTouchEvent","MyAvtivity activity up");
                break;
        }
         super.onTouchEvent(event);
        return true;
    }
}
