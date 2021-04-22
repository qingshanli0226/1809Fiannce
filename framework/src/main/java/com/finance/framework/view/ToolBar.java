package com.finance.framework.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.finance.framework.R;

public class ToolBar extends RelativeLayout {

    public ToolBar(Context context) {
        super(context,null,0);
    }

    public ToolBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs,0);
    }

    public ToolBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private String titleText;
    private int rightImgId,leftImgId;
    private ImageView leftImg;
    private ImageView rightImg;
    private TextView titleTv,rightTv;
    private boolean rightShow,leftShow;
    private LinearLayout rightArea;
    private IToolbarListener iToolbarListener;

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        //获取属性值
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ToolBar);
        titleText = typedArray.getString(R.styleable.ToolBar_titleText);
        leftImgId = typedArray.getResourceId(R.styleable.ToolBar_leftImg, 0);
        rightImgId = typedArray.getResourceId(R.styleable.ToolBar_rightImage, 0);
        rightShow = typedArray.getBoolean(R.styleable.ToolBar_rightIsShow, false);
        leftShow = typedArray.getBoolean(R.styleable.ToolBar_leftIsShow,false);
        typedArray.recycle();

        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.view_toolbar,this);
        titleTv = findViewById(R.id.titleTv);
        rightArea = findViewById(R.id.rightArea);
        leftImg = findViewById(R.id.leftImg);
        rightImg = findViewById(R.id.rightImg);
        rightTv = findViewById(R.id.rightTv);

        titleTv.setText(titleText);
        if (rightShow&&rightImgId!=0){
            rightImg.setImageResource(leftImgId);
        }
        if (leftShow&&leftImgId!=0){
            leftImg.setImageResource(leftImgId);
        }

        leftImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (iToolBar!=null){

                }
            }
        });



    }
}
