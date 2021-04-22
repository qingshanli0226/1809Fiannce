package com.example.a1809zg;

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
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyView extends View {
    private Paint paint;
    private final int START_ANGLE=0;//这是从3点钟方向开始画扇形
    private final int STEP_ANGLE=1;//这是每次一度的加扇形大小
    private int offsetAngle;//绘制扇形的角度.该大小根据理财产品销售的百分比进度计算出来的
    private int progressAngle=0;//绘制的角度
    private int color;
    private int index;
    public MyView(Context context) {

        this(context,null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
         paint = new Paint();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ProgressView);
         color = typedArray.getColor(R.styleable.ProgressView_textColor, Color.BLACK);
         index = typedArray.getInt(R.styleable.ProgressView_circleWith,5);
         typedArray.recycle();

    }
    public void saprogress(int progress,boolean isAnimal){
        offsetAngle=(progress*360)/100;
        if (isAnimal){
            progressAngle=0;
        }else {
            progressAngle=offsetAngle;
        }
        if (progressAngle<=offsetAngle){
            invalidate();
            handler.sendEmptyMessageDelayed(1,10);
        }
    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            progressAngle=progressAngle+STEP_ANGLE;
            if (progressAngle<=offsetAngle){
                invalidate();//重新绘制
                handler.sendEmptyMessageDelayed(1,10);
            }

        }
    };

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        event.getY();event.getRawY();
        event.getX();event.getX();
        return super.onTouchEvent(event);


    }



    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);


    }
    public void setColor(int color) {
        this.color = color;
        invalidate();
    }

    public void setIndex(int index) {
        this.index = index;
        invalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        int centerX=measuredWidth/2;
        int centerY=measuredHeight/2;
        int radius=(measuredWidth<measuredHeight?measuredWidth/2:measuredHeight/2);
        paint.setColor(Color.GRAY);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(index);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(centerX,centerY,radius,paint);


        RectF rectF = new RectF(measuredWidth / 2 - radius, measuredHeight / 2 - radius, measuredWidth / 2 + radius, measuredHeight / 2 + radius);
        paint.setColor(Color.BLUE);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(index);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(rectF,START_ANGLE,progressAngle,false,paint);
        Rect rect = new Rect();
        paint.setColor(Color.BLACK);
        paint.setTextSize(30);
        paint.setStrokeWidth(2);
        String content=(progressAngle*100)/360+"%";
        paint.getTextBounds(content,0,content.length(),rect);
        canvas.drawText(content,measuredWidth/2-rect.width()/2,(measuredHeight/2)+rect.height()/2,paint);
    }
    public void destry(){
        handler.removeCallbacksAndMessages(null);
    }

}
