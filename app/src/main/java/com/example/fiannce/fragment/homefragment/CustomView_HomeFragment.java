package com.example.fiannce.fragment.homefragment;

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
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.fiannce.R;

public class CustomView_HomeFragment extends View {

    private Paint paint = new Paint();
    private int START_ANGLE = 0;
    private int START_ADD = 1;
    private int offprogress = 0;
    private int progressangle;
    private int progress1;
    private int FILG = 0;
    private int RADUS_MARGIN = 15;
    private int color;

    public CustomView_HomeFragment(Context context) {
        super(context,null);
    }

    public CustomView_HomeFragment(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
        init(context,attrs,0);
    }

    public CustomView_HomeFragment(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.CustomView_HomeFragment);
        color = typedArray.getColor(R.styleable.CustomView_HomeFragment_color,Color.BLACK);
        int anInt = typedArray.getInt(R.styleable.CustomView_HomeFragment_textSize,20);

        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int progressWith,progressHigh;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        if (widthMode == MeasureSpec.AT_MOST){
            progressWith = 70;
        }else {
            progressWith = MeasureSpec.getSize(widthMeasureSpec);
        }

        if (heightMode == MeasureSpec.AT_MOST){
            progressHigh = 70;
        }else {
            progressHigh = MeasureSpec.getSize(heightMeasureSpec);
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
            if (progressangle <= offprogress){
                progressangle += START_ADD;
                invalidate();
                handler.sendEmptyMessageDelayed(FILG,5);
            }
        }
    };

    public void SealedProgress(int progress,boolean isShow){

        offprogress = (int) (progress * 3.6);

        progressangle = 0;
        if (isShow){
            if (progressangle <= offprogress){
                handler.sendEmptyMessageDelayed(FILG,5);
            }else {
                invalidate();
            }
        }else {
            progressangle = offprogress;
            invalidate();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20);
        paint.setAntiAlias(true);

        int ScaX = getMeasuredWidth() / 2;
        int ScaY = getMeasuredHeight() / 2;
        int radius = getMeasuredWidth() / 2 > getMeasuredHeight() / 2 ? getMeasuredHeight() / 2 - RADUS_MARGIN : getMeasuredHeight() / 2 - RADUS_MARGIN;

        canvas.drawCircle(ScaX,ScaY,radius,paint);

        RectF rectF = new RectF(getMeasuredWidth() / 2 - radius,getMeasuredHeight() / 2 - radius,getMeasuredWidth() / 2 + radius,getMeasuredHeight() / 2 +radius);
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(15);
        canvas.drawArc(rectF,START_ANGLE,progressangle,false,paint);

        Rect rect = new Rect();
        paint.setColor(Color.BLACK);
        paint.setTextSize(50);
        paint.setStrokeWidth(5);
        int i = progressangle * 100 / 360;
        String current = i + "".split(".")[0]+"%";

        paint.getTextBounds(current,0,current.length(),rect);
        canvas.drawText(current,getMeasuredWidth()/2  - rect.width()/2,getMeasuredHeight() / 2 +rect.height()/2,paint);
    }
}
