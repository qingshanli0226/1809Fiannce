package com.example.framework.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

public class SlidingDelete extends FrameLayout {
    private int arcwidth=100;
    private int archeight=100;
    private Paint mPaint=new Paint();
    public SlidingDelete(Context context) {
        super(context);
    }

    public SlidingDelete(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SlidingDelete(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawRect(0,0,arcwidth,archeight,mPaint);
    }

    private int lateX;
    private int lateY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction()==MotionEvent.ACTION_DOWN){
            lateX= (int) event.getX();
            lateY= (int) event.getY();
        } else if (event.getAction()==MotionEvent.ACTION_DOWN) {
                arcwidth+=event.getX()-lateX;
                archeight+=event.getY()-lateY;
                invalidate();
        } else if (event.getAction()==MotionEvent.ACTION_DOWN) {

        }


        return super.onTouchEvent(event);
    }
}
