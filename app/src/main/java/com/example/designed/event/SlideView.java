package com.example.designed.event;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class SlideView extends View {

    private int centerX = 200,centerY = 200;

    public SlideView(Context context) {
        super(context);
    }

    public SlideView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SlideView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);

        canvas.drawCircle(centerX,centerY,200,paint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_MOVE:
                centerX = (int) event.getX();
                centerY = (int) event.getY();
                invalidate();
                break;
        }

        return true;
    }
}
