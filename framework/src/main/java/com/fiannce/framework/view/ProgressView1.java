package com.fiannce.framework.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.fiannce.framework.R;

public class ProgressView1 extends View {

    private Paint paint;
    private int START_ANGLE = 0;
    private int STEP_ANGLE = 1;
    private int offsetAngle;
    private int progressAngle = 0;
    private int progressViewHeight;
    private int progressViewWidth;
    private int CIRCLE_MARGIN = 5;

    public ProgressView1(Context context) {
        this(context,null);
    }

    public ProgressView1(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ProgressView1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        paint = new Paint();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ProgressView);
        typedArray.recycle();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void saledProgress(int progress,boolean isAnimal){
        offsetAngle = (progress * 360) / 100;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }


}
