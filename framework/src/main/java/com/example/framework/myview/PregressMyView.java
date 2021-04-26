package com.example.framework.myview;

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

import com.example.framework.R;

public class PregressMyView extends View {
    private Paint paint;
    private final int START_ANGLE = 0;//以三点钟方向绘制弧形
    private final int STEP_ANGLE = 1;//每次增加一度
    private int offsetAngle;//绘制弧形的角度，该值为给的百分比数据计算
    private int progressAngle = 0;//已经绘制的角度
    private int progressViewWidth;//控件的宽高
    private int progressViewHeight;
    private final int CIRCLE_MARGIN = 10;
    private int textColor;
    private int circleWith;
    private int downCirCleColor;
    private int upCirCleColor;


    public PregressMyView(Context context) {
        this(context,null);
    }

    public PregressMyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PregressMyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        Log.d("PregressMyView", "我的myView");

        TypedArray typedArray=context.obtainStyledAttributes(attrs,R.styleable.PregressMyView);
        textColor=typedArray.getColor(R.styleable.PregressMyView_textColor,Color.BLACK);
        circleWith=typedArray.getInt(R.styleable.PregressMyView_circleWidth,10);
        upCirCleColor=typedArray.getColor(R.styleable.PregressMyView_upCirclecolor,Color.BLUE);
        downCirCleColor=typedArray.getColor(R.styleable.PregressMyView_downCirclecolor,Color.RED);
        typedArray.recycle();//清理
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthmode=MeasureSpec.getMode(widthMeasureSpec);
        int heightmode=MeasureSpec.getMode(heightMeasureSpec);
        if (widthmode==MeasureSpec.AT_MOST){
            progressViewWidth=200;
        }else {
            progressViewWidth=MeasureSpec.getSize(widthMeasureSpec);
        }
        if (heightmode==MeasureSpec.AT_MOST){
            progressViewHeight=200;
        }else {
            progressViewHeight=MeasureSpec.getSize(heightMeasureSpec);
        }

        setMeasuredDimension(progressViewWidth,progressViewHeight);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    public void settextColor(int setcolor){
        textColor=setcolor;
        invalidate();
    }
    public void setCirCleWidth(int width){
        circleWith=width;
        invalidate();
    }
    public void setdownCirCleColor(int width){
        downCirCleColor=width;
        invalidate();
    }
    public void setUpCirCleColor(int width){
        upCirCleColor=width;
        invalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint=new Paint();
        //获取宽高
        progressViewWidth = getMeasuredWidth();
        progressViewHeight = getMeasuredHeight();
        //下面的圆
        int centerX = progressViewWidth / 2;
        int centerY = progressViewHeight / 2;
        //获取半径
        int radio = (progressViewWidth < progressViewHeight ? progressViewWidth / 2 : progressViewHeight / 2) - CIRCLE_MARGIN;
        paint.setColor(downCirCleColor);
//        paint.setAntiAlias(true);
        paint.setStrokeWidth(circleWith);//笔宽
        paint.setStyle(Paint.Style.STROKE);//中空
        canvas.drawCircle(centerX, centerY, radio, paint);//画下面的圆

        //画扇形
        RectF rectF = new RectF(progressViewWidth / 2 - radio, progressViewHeight / 2 - radio, progressViewWidth / 2 + radio, progressViewHeight / 2 + radio);
        paint.setColor(upCirCleColor);
        //        paint.setAntiAlias(true);
        paint.setStrokeWidth(circleWith);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(rectF,START_ANGLE,progressAngle,false,paint);//画扇形
        //文字
        Rect rect=new Rect();
        paint.setColor(textColor);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(2);
        paint.setTextSize(50);
        String content=(progressAngle*100)/360+"%";
        paint.getTextBounds(content,0,content.length(),rect);
        canvas.drawText(content,progressViewWidth/2-rect.width()/2,progressViewHeight/2+rect.height()/2,paint);
    }


    public void destroy(){
        handler.removeCallbacksAndMessages(null);
    }
    public void getProgressNum(int progress, boolean isAnimation) {
        offsetAngle = (progress * 360) / 100;//计算角度
        if (isAnimation) {//需要动画  从0开始
            progressAngle = 0;
        } else {//不需要动画  直接给角度
            progressAngle = offsetAngle;
        }
        if (progressAngle <= offsetAngle) {
            invalidate();//重绘
            handler.sendEmptyMessageDelayed(1, 10);
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            //只有一个 不需要判断
            progressAngle += STEP_ANGLE;
            if (progressAngle <= offsetAngle) {
                invalidate();
                handler.sendEmptyMessageDelayed(1, 10);
            }
        }
    };
}
