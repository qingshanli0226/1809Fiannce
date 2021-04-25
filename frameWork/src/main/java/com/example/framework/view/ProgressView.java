package com.example.framework.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import com.example.framework.R;

public class ProgressView extends View {

    private Paint paint;
    private final int START_Angle = 0;
    private final int STEP_Angle = 1;
    private final int RADUS_MARGIN = 15;
    private final int FLAG_ANIMATER = 0;
    int color;
    private int progressAngle = 0;
    private int percentage_angle = 0;

    public ProgressView(Context context) {
        this(context,null);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    @SuppressLint("ResourceAsColor")
    private void init(Context context, AttributeSet attrs) {
        paint = new Paint();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ProgressView);
        color = typedArray.getColor(R.styleable.ProgressView_textcolor, R.color.blueee);
        typedArray.getText(R.styleable.ProgressView_android_text);
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //圆形
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.GRAY);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(20);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();

        int X = measuredWidth / 2;
        int Y = measuredHeight / 2;

        int radus = (measuredWidth>measuredHeight?measuredHeight/2:measuredWidth/2) - RADUS_MARGIN;
        canvas.drawCircle(X,Y,radus,paint);

        //上面
        RectF rectF = new RectF(measuredWidth/2-radus,measuredHeight/2-radus,measuredWidth/2+radus,measuredHeight/2+radus);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(color);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(20);
        canvas.drawArc(rectF,START_Angle,progressAngle,false,paint);

        //画文字
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(40);
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);

        Rect rect = new Rect();
        int i = progressAngle * 100 / 360;
        String percentage = i + "".split(".")[0] + "%";
        paint.getTextBounds(percentage,0,percentage.length(),rect);
        canvas.drawText(percentage,measuredWidth/2-rect.width()/2,measuredHeight/2+rect.height()/2,paint);


    }

    //播放动画
    public void startProgressDraw(int percentage,boolean isStart){
        percentage_angle = (int) (percentage * 3.6);
        progressAngle = 0;
        if (isStart){
            if (progressAngle<=percentage_angle){
                handler.sendEmptyMessageDelayed(FLAG_ANIMATER,5);
            }else {
                invalidate();
            }
        }else {
            progressAngle = percentage_angle;
            invalidate();
        }
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            progressAngle += STEP_Angle;
            invalidate();
            if (progressAngle <= percentage_angle){
                handler.sendEmptyMessageDelayed(FLAG_ANIMATER,5);
            }
        }
    };

}
