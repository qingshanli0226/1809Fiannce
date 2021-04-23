package com.example.a1809fiannce.exeprice;

import android.content.Context;
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

public class ProView extends View {
    private Paint paint=new Paint();
    private int StaPro;
    private int OffPro;
    public ProView(Context context) {
        this(context,null);
    }

    public ProView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ProView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what==1){
                if (StaPro<=OffPro){
                    StaPro+=1;
                    invalidate();
                    handler.sendEmptyMessageDelayed(1,5);

                }
            }
        }
    };
    public void num(int pro,boolean isShow){
        OffPro= (int) (pro*3.6);
        StaPro=0;
        if (isShow){
            if (StaPro<=OffPro){
                handler.sendEmptyMessageDelayed(1,5);
            }else {
                invalidate();
            }
        }else {
            StaPro=OffPro;
            invalidate();
        }
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int ScX=getMeasuredWidth()/2;
        int ScY=getMeasuredHeight()/2;
        int radius=getMeasuredWidth()/2>getMeasuredHeight()/2?getMeasuredHeight()/2-15:getMeasuredWidth()/2-15;
        //内圆
        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20);
        paint.setAntiAlias(true);
        canvas.drawCircle(ScX,ScY,radius,paint);

        //外弧形

        RectF rectF = new RectF(getMeasuredWidth() / 2 - radius, getMeasuredHeight() / 2 - radius, getMeasuredWidth() / 2 + radius, getMeasuredHeight() / 2 + radius);
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(20);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(rectF,0,StaPro,false,paint);

        //文本
        Rect rect = new Rect();
        paint.setTextSize(50);
        paint.setStrokeWidth(5);
        paint.setColor(Color.BLACK);
        int i=StaPro*100/360;

        String current = i+"".split("0")[0]+"%";

        paint.getTextBounds(current,0,current.length(),rect);
        canvas.drawText(current,getMeasuredWidth()/2-rect.width()/2,getMeasuredHeight()/2+rect.height()/2,paint);
    }
}
