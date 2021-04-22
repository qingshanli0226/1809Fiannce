package com.example.framework.view;

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

import com.example.framework.R;

public class ProgressView extends View {
    private Paint paint;
    private final int START_ANGLE = 0;//以3点钟方向开始绘制扇形
    private final int STEP_ANGLE = 1;//每次按照1度的大小进行增加
    private int offsetAngle;
    private int progressAngle = 0;
    private int progressViewWidth;
    private int progressViewHeight;
    private int progress;
    private final int CIRCLE_MARGIN = 17;
    private int textColor;
    private int textSize;
    private int paintSize;
    private int paintColor1;
    private int paintColor2;

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
        this.paint = new Paint();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ProgressView);

        textColor = typedArray.getColor(R.styleable.ProgressView_TextColor, Color.BLACK);
        textSize = typedArray.getInt(R.styleable.ProgressView_TextSize, 30);
        paintSize = typedArray.getInt(R.styleable.ProgressView_PaintSize, 5);
        paintColor1 = typedArray.getInt(R.styleable.ProgressView_PaintColor1, Color.BLUE);
        paintColor2 = typedArray.getInt(R.styleable.ProgressView_PaintColor2, Color.RED);

        typedArray.recycle();
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
        invalidate();
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
        invalidate();
    }

    public void setPaintSize(int paintSize) {
        this.paintSize = paintSize;
        invalidate();
    }

    public void setPaintColor1(int paintColor1) {
        this.paintColor1 = paintColor1;
        invalidate();
    }

    public void setPaintColor2(int paintColor2) {
        this.paintColor2 = paintColor2;
        invalidate();
    }

    public void saledProgress(int progress, boolean is) {
        offsetAngle = (progress * 360) / 100;
        if (is) {
            progressAngle = 0;
        } else {
            progressAngle = offsetAngle;
        }

        this.progress = 0;

        if (progressAngle <= offsetAngle) {
            invalidate();
            handler.sendEmptyMessageDelayed(1, 50);
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            progressAngle = progressAngle + STEP_ANGLE;
            if (progressAngle <= offsetAngle) {
                invalidate();
                handler.sendEmptyMessageDelayed(1, 50);
            }
        }
    };

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        if (widthMode == MeasureSpec.AT_MOST){
            progressViewWidth=50;
        }else {
            int size = MeasureSpec.getSize(widthMeasureSpec);

            if (size>=200){
                progressViewWidth=200;
            }else {
                progressViewWidth=size;
            }
        }

        if (heightMode == MeasureSpec.AT_MOST){
            progressViewHeight=50;
        }else {
            int size = MeasureSpec.getSize(heightMeasureSpec);

            if (size>=200){
                progressViewHeight=200;
            }else {
                progressViewHeight=size;
            }
        }

        setMeasuredDimension(progressViewWidth,progressViewHeight);
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

        int radius = (progressViewWidth < progressViewHeight ? progressViewWidth / 2 : progressViewHeight / 2) - CIRCLE_MARGIN;

        paint.setColor(paintColor1);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(paintSize);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(centerX, centerY, radius, paint);

        RectF rectF = new RectF(progressViewWidth / 2 - radius, progressViewHeight / 2 - radius, progressViewWidth / 2 + radius, progressViewHeight / 2 + radius);
        paint.setColor(paintColor2);
        canvas.drawArc(rectF, START_ANGLE, progressAngle, false, paint);

        Rect rect = new Rect();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(textColor);
        paint.setTextSize(textSize);
        paint.setStrokeWidth(2);
        String content = (progressAngle * 100) / 360 + "%";
        paint.getTextBounds(content, 0, content.length(), rect);
        canvas.drawText(content, progressViewWidth / 2 - rect.width() / 2, progressViewHeight / 2 + rect.height() / 2, paint);
    }
}
