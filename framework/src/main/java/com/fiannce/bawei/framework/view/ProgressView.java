package com.fiannce.bawei.framework.view;

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

import com.fiannce.bawei.framework.R;

import androidx.annotation.NonNull;


public class ProgressView extends View {
    private Paint paint;
    private final int START_ANGLE = 0;//以3点钟方向开始绘制扇形
    private final int STEP_ANGLE = 1;//每次按照1度的大小进行增加
    private int offsetAngle;//绘制扇形的角度.该大小根据理财产品销售的百分比进度计算出来的
    private int progressAngle = 0;//已经绘制的角度
    private int progressViewWidth;
    private int progressViewHeight;//控件的宽度和高度
    private int progess;
    private final int CIRCLE_MARGIN = 5;
    private int textColor;
    private int circleWith;

    public ProgressView(Context context) {//new 一个控件时，会调用该构造方法
        this(context,null);
    }

    public ProgressView(Context context, AttributeSet attrs) {//在布局里声明，findViewById会调用该构造方法
        this(context, attrs, 0);
    }

    public ProgressView(Context context, AttributeSet attrs, int defStyleAttr) {//当布局里有style属性时会调用该构造函数
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    //初始化方法
    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        paint = new Paint();

        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.ProgressView);
        textColor = typedArray.getColor(R.styleable.ProgressView_textColor,Color.BLACK);
        circleWith = typedArray.getInt(R.styleable.ProgressView_circleWith,5);

        typedArray.recycle();
    }

    //添加一个方法，设置理财产品销售的百分比（如果卖了20%，progress参数就传20）
    public void saledProgress(int progress,boolean isAnimal) {
        offsetAngle = (progress * 360)/100;//计算出扇形绘制的角度
        if (isAnimal) {
            progressAngle = 0;
        } else {
            progressAngle = offsetAngle;
        }

        if (progressAngle<=offsetAngle) {
            invalidate();//触发onDraw函数执行
            handler.sendEmptyMessageDelayed(1,10);
        }

    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            progressAngle = progressAngle + STEP_ANGLE;
            if (progressAngle<=offsetAngle) {
                invalidate();
                handler.sendEmptyMessageDelayed(1,10);
            }
        }
    };
    

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        event.getY();event.getRawY();
        event.getX();event.getRawX();
        return super.onTouchEvent(event);
    }

    //把控件摆放到那个位置的，实现自定义View时，该方法按照默认实现就可以。把控件摆放到何处，由它的父布局来决定就可以了
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    public void setTextColor(int color) {
        this.textColor = color;
        invalidate();
    }

    public void setCircleWith(int width) {
        this.circleWith = width;
        invalidate();
    }

    //绘制View，重点实现
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //第一步绘制底部圆形
        //先求出圆心
        progressViewWidth = getMeasuredWidth();//获取控件的宽度，必须在onMeasure之后再获取
        progressViewHeight = getMeasuredHeight();//获取控件的高度，必须在onMeasure之后再获取
        int centerX = progressViewWidth/2;
        int centerY = progressViewHeight/2;
        //获取半径
        int radius = (progressViewWidth<progressViewHeight?progressViewWidth/2:progressViewHeight/2)-CIRCLE_MARGIN;
        paint.setColor(Color.BLUE);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(circleWith);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(centerX,centerY,radius,paint);


        //画扇形
        RectF rectF = new RectF(progressViewWidth/2-radius,progressViewHeight/2-radius,progressViewWidth/2+radius,progressViewHeight/2+radius);
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(circleWith);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(rectF,START_ANGLE,progressAngle,false,paint);

        //绘制文字
        Rect rect = new Rect();
        paint.setColor(textColor);
        paint.setTextSize(30);
        paint.setStrokeWidth(2);
        String content = (progressAngle*100)/360+"%";
        Log.d("LQS", content+"");
        paint.getTextBounds(content,0,content.length(),rect);
        canvas.drawText(content,progressViewWidth/2-rect.width()/2,progressViewHeight/2+rect.height()/2,paint);
    }

    public void destry() {
        handler.removeCallbacksAndMessages(null);
    }
}
