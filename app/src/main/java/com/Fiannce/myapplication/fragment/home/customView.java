package com.Fiannce.myapplication.fragment.home;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import static com.blankj.utilcode.util.CrashUtils.init;

public class customView extends View {

    private int WidthSize = 20;
    private Paint buttonpaint = new Paint();
    private Paint toppaint = new Paint();
    private Paint textpant = new Paint();
    private String mtext = "90%";
    private int current = 0;

    public customView(Context context) {
        this(context,null);
    }

    public customView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public customView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    public void startCurrent(){
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 360);
        valueAnimator.setDuration(3000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int schedule = (int) animation.getAnimatedValue();
                current = schedule;
                invalidate();
            }
        });
        valueAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int half = width/2;
        int radius = half - WidthSize/2;


        buttonpaint.setStyle(Paint.Style.STROKE);
        buttonpaint.setColor(Color.parseColor("#5A6E9E"));
        buttonpaint.setStrokeWidth(WidthSize);
        canvas.drawCircle(half,half,radius,buttonpaint);

        toppaint.setStyle(Paint.Style.STROKE);
        toppaint.setColor(Color.parseColor("#DC143D"));
        toppaint.setStrokeWidth(WidthSize);
        canvas.drawArc(half-radius,half-radius,half+radius,half+radius,0,current,false,toppaint);

        int i = current * 100 /360;
        mtext = i+"%";
        textpant.setTextSize(50);
        int text = (int) textpant.measureText(mtext);
        canvas.drawText(mtext,half-text/2,half,textpant);
    }
}
