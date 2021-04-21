package com.example.a1809fiannce.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class CusView extends View {
    private Paint paint = new Paint();
    private int START_ANGLE=0;
    private int START_ADD=1;
    private int offprogress;
    private int progressangle;
    private int progress;
    public CusView(Context context) {
        super(context,null);
    }

    public CusView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
    }

    public CusView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
    }
    public void SealedProgress(int progress){
        offprogress = (progress*360)/100;
        this.progress=progress;
        progressangle=progressangle+START_ADD;
        if (progress<=offprogress){
            invalidate();
        }

    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //内圈
        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.STROKE);
        int ScaX = getMeasuredWidth()/2;
        int ScaY = getMeasuredHeight()/2;
        int radius = getMeasuredWidth()/2>getMeasuredHeight()/2?getMeasuredHeight()/2:getMeasuredHeight()/2;
        canvas.drawCircle(ScaX,ScaY,radius,paint);

        //动画外圈
        RectF rectF = new RectF(getMeasuredWidth()/2-radius,getMeasuredHeight()/2-radius,getMeasuredWidth()/2+radius,getMeasuredHeight()/2+radius);
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        canvas.drawArc(rectF,START_ANGLE,offprogress,false,paint);

        //画文本
        Rect rect = new Rect();
        paint.setColor(Color.BLACK);
        paint.setTextSize(30);
        paint.setStrokeWidth(2);
        int f = progress*100/360;
        String current = f+"%";

        paint.getTextBounds(current,0,current.length(),rect);
        canvas.drawText(current,getMeasuredWidth()/2-rect.width()/2,getMeasuredHeight()/2+rect.height()/2,paint);


    }
}
