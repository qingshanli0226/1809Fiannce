package com.example.a1809fiannce.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class CusView extends RelativeLayout {
    private int START_ANGLE=0;
    private int START_ADD=1;
    private int offprogress=0;
    private int progressangle;
    private int progress1;
    private int FILG=0;
    private int RADUS_MARGIN=15;
    private int color;
    public CusView(Context context) {
        super(context);
        init();
    }
    public CusView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CusView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public CusView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    private Paint paint;
    private void init() {
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int cusX = getMeasuredWidth()/2;
        int cusY = getMeasuredHeight()/2;
        paint.setColor(Color.parseColor("#5A6E9E"));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(30);

        int radius  =(getMeasuredWidth()>getMeasuredHeight()?getMeasuredWidth()/2:getMeasuredHeight()/2)-15;
        canvas.drawCircle(cusX,cusY,radius,paint);

        RectF rectF = new RectF(getMeasuredWidth()/2-radius,getMeasuredHeight()/2-radius,getMeasuredWidth()/2+radius,getMeasuredHeight()/2+radius);
        paint.setColor(color);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(15);
        canvas.drawArc(rectF,START_ANGLE,progressangle,false,paint);

        Rect rect = new Rect();
        paint.setColor(Color.BLACK);
        paint.setTextSize(50);
        paint.setStrokeWidth(5);

        int i = progressangle*100/360;
        String current = i+"".split(".")[0]+"%";

        paint.getTextBounds(current,0,current.length(),rect);
        canvas.drawText(current,getMeasuredWidth()/2-rect.width()/2,getMeasuredHeight()/2+rect.height()/2,paint);
    }
}
