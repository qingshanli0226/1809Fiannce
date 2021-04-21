package com.fiannce.bawei.framework.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.framework.R;

public class CustomView extends View {
    public CustomView(Context context) {
        super(context);
        init(null);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(AttributeSet attrs){
//        if (attrs!=null){
//            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CustomView);
//            mcolor = typedArray.getColor(R.styleable.CustomView_zzy_color,Color.RED);
//            mRingSize = (int) typedArray.getDimension(R.styleable.CustomView_zzy_size,10);
//            typedArray.recycle();
//        }
    }


    public void startmcurrent(){
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 360);
        valueAnimator.setDuration(3000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int  v = (int) animation.getAnimatedValue();
                mcurrent = v;
                invalidate();
            }
        });
        valueAnimator.start();
    }

    private int mRingSize = 20;
    private Paint mbuttonpaint = new Paint();
    private Paint mtoppaint = new Paint();
    private Paint textpant = new Paint();
    private String mtext = "90%";
    private int mcolor = Color.RED;
    private int mcurrent = 0;

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getWidth();
        int center = width/2;
        int radius = center - mRingSize/2;

        mbuttonpaint.setStyle(Paint.Style.STROKE);
        mbuttonpaint.setColor(Color.BLACK);
        mbuttonpaint.setStrokeWidth(mRingSize);
        canvas.drawCircle(center,center,radius,mbuttonpaint);


        mtoppaint.setStyle(Paint.Style.STROKE);
        mtoppaint.setColor(mcolor);
        mtoppaint.setStrokeWidth(mRingSize);
        RectF rectF = new RectF(center-radius,center-radius,center+radius,center+radius);
        canvas.drawArc(rectF,-90,mcurrent,false,mtoppaint);

        textpant.setColor(mcolor);
        textpant.setTextSize(40);

//        mcurrent = mcurrent%360;

        int i = mcurrent * 100 / 360;
        mtext = i+"%";
        int  text = (int) textpant.measureText(mtext);
        canvas.drawText(mtext,center-text/2,center,textpant);


    }
}
