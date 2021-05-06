package com.example.a1809fiannce.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.LogUtils;
import com.example.a1809fiannce.R;

public class CusView extends View {
    private Paint paint = new Paint();
    private int START_ANGLE=0;
    private int START_ADD=5;
    private int offprogress=0;
    private int progressangle;
    private int progress1;
    private int FILG=0;
    private int RADUS_MARGIN=15;
    private int color;
    public CusView(Context context) {
        this(context,null);
    }

    public CusView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CusView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CusView);
        color = typedArray.getColor(R.styleable.CusView_Color, Color.BLACK);
        Log.i("zx", "init: "+color);
        int anInt = typedArray.getInt(R.styleable.CusView_textSize, 20);

        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int progressWith,progressHigh;
        int withMode=MeasureSpec.getMode(widthMeasureSpec);
        int highMode=MeasureSpec.getMode(heightMeasureSpec);

        if (withMode==MeasureSpec.AT_MOST){
            progressWith=70;
        }else {
            progressWith=MeasureSpec.getSize(widthMeasureSpec);
        }

        if (highMode==MeasureSpec.AT_MOST){
            progressHigh=70;
        }else {
            progressHigh=MeasureSpec.getSize(heightMeasureSpec);
        }
        setMeasuredDimension(progressWith,progressHigh);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            invalidate();
            if (progressangle<=offprogress){
                progressangle+=START_ADD;
                invalidate();
                handler.sendEmptyMessageDelayed(FILG,50);
            }
        }
    };
    public void SealedProgress(int progress,boolean isShow){
        offprogress = (int) (progress*3.6);
        Log.i("cc", "SealedProgress: "+offprogress);
        progressangle=0;
        if (isShow){
            if (progressangle<=offprogress){
                handler.sendEmptyMessageDelayed(FILG,50);
            }else {
                invalidate();
            }
        }else {
            progressangle=offprogress;
            invalidate();
        }


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //内圈
        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(30);
        paint.setAntiAlias(true);
        int ScaX = getMeasuredWidth()/2;
        int ScaY = getMeasuredHeight()/2;
        int radius = getMeasuredWidth()/2>getMeasuredHeight()/2?getMeasuredHeight()/2-RADUS_MARGIN:getMeasuredHeight()/2-RADUS_MARGIN;
        canvas.drawCircle(ScaX,ScaY,radius,paint);

        //动画外圈
        RectF rectF = new RectF(getMeasuredWidth()/2-radius,getMeasuredHeight()/2-radius,getMeasuredWidth()/2+radius,getMeasuredHeight()/2+radius);
        paint.setColor(color);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(30);
        canvas.drawArc(rectF,START_ANGLE,progressangle,false,paint);

        //画文本
        Rect rect = new Rect();
        paint.setColor(Color.BLUE);
        paint.setTextSize(50);
        paint.setStrokeWidth(5);

        int i = progressangle*100/360;
        String current = i+"".split(".")[0]+"%";

        paint.getTextBounds(current,0,current.length(),rect);
        canvas.drawText(current,getMeasuredWidth()/2-rect.width()/2,getMeasuredHeight()/2+rect.height()/2,paint);


    }
    public void Destroy(){
        if (handler!=null){
            handler.removeCallbacksAndMessages(null);
        }
    }
}
