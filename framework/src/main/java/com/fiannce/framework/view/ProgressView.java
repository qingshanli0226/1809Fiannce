package com.fiannce.framework.view;

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
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.fiannce.framework.R;

public class ProgressView extends View {

    private Paint paint;
    private int START_ANGLE = 0;
    private int STEP_ANGLE = 1;
    private int offsetAngle;
    private int progressAngle = 0;
    private int progressViewHeight;
    private int progressViewWidth;
    private int CIRCLE_MARGIN = 5;
    private int textColor;
    private int circleWith;
    private int neiColor;
    private int waiColor;

    public ProgressView(Context context) {
        this(context, null);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }


    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        paint = new Paint();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ProgressView);
        textColor = typedArray.getColor(R.styleable.ProgressView_textColor, Color.BLACK);
        circleWith = typedArray.getInt(R.styleable.ProgressView_circleWith, 30);
        neiColor = typedArray.getColor(R.styleable.ProgressView_neiColor, Color.parseColor("#5A6E9E"));
        waiColor = typedArray.getColor(R.styleable.ProgressView_waiColor, Color.parseColor("#DC143D"));

        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    public void saledProgress(int progress, boolean isAnimal) {
        offsetAngle = (progress * 360) / 100;
        if (isAnimal) {
            progressAngle = 0;
        } else {
            progressAngle = offsetAngle;
        }

        if (progressAngle <= offsetAngle) {
            invalidate();
            handler.sendEmptyMessageDelayed(1, 10);
        }

    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            progressAngle = progressAngle + STEP_ANGLE;
            if (progressAngle <= offsetAngle) {
                invalidate();
                handler.sendEmptyMessageDelayed(1, 10);
            }
        }
    };

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        event.getY();
        event.getRawY();
        event.getX();
        event.getRawX();
        return super.onTouchEvent(event);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        progressViewWidth = getMeasuredWidth();
        progressViewHeight = getMeasuredHeight();
        int centerX = progressViewWidth / 2;
        int centerY = progressViewHeight / 2;

        //半径
        int radius = ((progressViewWidth < progressViewHeight ? progressViewWidth / 2 : progressViewHeight / 2) - CIRCLE_MARGIN) - 15;
        paint.setColor(neiColor);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(circleWith);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(centerX, centerY, radius, paint);


        //矩形
        RectF rectF = new RectF(progressViewWidth / 2 - radius, progressViewHeight / 2 - radius, progressViewWidth / 2 + radius, progressViewHeight / 2 + radius);
        paint.setColor(waiColor);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(circleWith);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(rectF, START_ANGLE, progressAngle, false, paint);


        Rect rect = new Rect();
        paint.setColor(textColor);
        paint.setTextSize(30);
        paint.setStrokeWidth(2);
        String content = (progressAngle * 100) / 360 + "%";
        paint.getTextBounds(content, 0, content.length(), rect);
        canvas.drawText(content, progressViewWidth / 2 - rect.width() / 2, progressViewHeight / 2 + rect.height() / 2, paint);
    }

    public void destry() {
        handler.removeCallbacksAndMessages(null);
    }
}