package com.example.a1809zg;

import android.content.Context;
import android.util.AttributeSet;
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
            case 
        }
        return super.dispatchTouchEvent(ev);

    }
}
