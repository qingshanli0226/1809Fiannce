package com.example.framework.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.framework.R;

import java.util.Timer;
import java.util.TimerTask;

public class ToolBar extends RelativeLayout {
    private TextView titleTV;
    private LinearLayout rightArea;
    private ImageView leftImg;
    private ImageView rightImg;
    private TextView rightTv;
    private boolean rightAreaIsShow, leftIsShow;
    private String titleText;
    private int rightImgId, leftImgId;

    private IToolbarListener iToolbarListener;

    public ToolBar(Context context) {
        super(context);
        init(context, null, 0);
    }

    public ToolBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public ToolBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    public void init(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ToolBar);

        titleText = typedArray.getString(R.styleable.ToolBar_titleText);
        leftImgId = typedArray.getResourceId(R.styleable.ToolBar_leftImage, 0);
        rightImgId = typedArray.getResourceId(R.styleable.ToolBar_rightImage, 0);
        rightAreaIsShow = typedArray.getBoolean(R.styleable.ToolBar_rightIsShow, false);
        leftIsShow = typedArray.getBoolean(R.styleable.ToolBar_leftIsShow, false);

        typedArray.recycle();

        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.view_toolbar, this);

        titleTV = findViewById(R.id.titleTv);
        rightArea = findViewById(R.id.rightArea);
        leftImg = findViewById(R.id.leftImg);
        rightImg = findViewById(R.id.rightImg);
        rightTv = findViewById(R.id.rightTv);

        titleTV.setText(titleText);
        if (rightAreaIsShow && rightImgId != 0) {
            rightImg.setImageResource(rightImgId);
        }
        if (leftIsShow && leftImgId!=0){
            leftImg.setImageResource(leftImgId);
        }

        leftImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (iToolbarListener!=null){
                    iToolbarListener.onLeftClick();
                }
            }
        });

        rightImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (iToolbarListener!=null){
                    iToolbarListener.onRightImgClick();
                }
            }
        });
    }

    public void setToolbarListener(IToolbarListener iToolbarListener) {
        this.iToolbarListener = iToolbarListener;
    }

    public void setTitleTV(TextView titleTV) {
        this.titleTV = titleTV;
    }

    public void setRightArea(LinearLayout rightArea) {
        this.rightArea = rightArea;
    }

    public void setLeftImg(ImageView leftImg) {
        this.leftImg = leftImg;
    }

    public void setRightImg(ImageView rightImg) {
        this.rightImg = rightImg;
    }

    public void setRightTv(TextView rightTv) {
        this.rightTv = rightTv;
    }

    public void setRightAreaIsShow(boolean rightAreaIsShow) {
        this.rightAreaIsShow = rightAreaIsShow;
    }

    public void setLeftIsShow(boolean leftIsShow) {
        this.leftIsShow = leftIsShow;
    }

    public void setTitleText(String titleText) {
        this.titleText = titleText;
    }

    public void setRightImgId(int rightImgId) {
        this.rightImgId = rightImgId;
    }

    public void setLeftImgId(int leftImgId) {
        this.leftImgId = leftImgId;
    }

    public static interface IToolbarListener {
        void onLeftClick();

        void onRightImgClick();

        void onRightTvClick();
    }
}
