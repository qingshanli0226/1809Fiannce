package com.example.fiannce.fragment.morefragment;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.fiannce.R;

public class MoreView extends LinearLayout {

    private String leftTitle;
    private String rightTitle;
    private int leftIcon;
    private int rightIcon;
    private boolean rightImgShow;
    private boolean rightTextShow;
    private ImageView leftImg;
    private ImageView rightImg;
    private TextView leftTit;
    private TextView rightTit;
    private iCallBack iCallBack = new iCallBack() {
        @Override
        public void leftImg() {

        }

        @Override
        public void rightImg() {

        }

        @Override
        public void leftText() {

        }

        @Override
        public void rightText() {

        }
    };

    public MoreView(Context context) {
        this(context,null);
    }

    public MoreView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MoreView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.MoreView);

        leftTitle = typedArray.getString(R.styleable.MoreView_left_title);
        rightTitle = typedArray.getString(R.styleable.MoreView_left_title);
        leftIcon = typedArray.getResourceId(R.styleable.MoreView_left_icon, 0);
        rightIcon = typedArray.getResourceId(R.styleable.MoreView_right_icon, 0);
        rightImgShow = typedArray.getBoolean(R.styleable.MoreView_lin_right_show, false);
        rightTextShow = typedArray.getBoolean(R.styleable.MoreView_lin_right_Text_show, false);
        typedArray.recycle();
        View view = LayoutInflater.from(context).inflate(R.layout.item_more,this);
        leftImg = view.findViewById(R.id.lin_left_img);
        rightImg = view.findViewById(R.id.lin_right);
        leftTit = view.findViewById(R.id.lin_left_tit);
        rightTit = view.findViewById(R.id.lin_right_tit);

        leftImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                iCallBack.leftImg();
            }
        });
        rightImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                iCallBack.rightImg();
            }
        });
        rightTit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                iCallBack.rightText();
            }
        });
        leftTit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                iCallBack.leftText();
            }
        });
        leftImg.setImageResource(leftIcon);

        if (rightImgShow){
            rightImg.setImageResource(rightIcon);
        }

        leftTit.setText(leftTitle+"");

        if (rightTextShow){
            rightTit.setText(rightTitle+"");
        }


    }

    public void setiCallBack(MoreView.iCallBack iCallBack) {
        this.iCallBack = iCallBack;
    }


    public MoreView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public static interface iCallBack{
        void leftImg();
        void rightImg();
        void leftText();
        void rightText();
    }
}
