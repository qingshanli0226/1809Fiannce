package com.example.framework.myview;

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

public class ToolBar extends RelativeLayout {
    private TextView titleTv;
    private LinearLayout rightArea;
    private ImageView leftIma;
    private ImageView rightIma;
    private TextView rightTv;
    private boolean rightAreaIsShow,leftAreaIsShow;
    private String titleText;
    private int rightImageId,leftImageId;
    private IToolbarListener iToolbarListener;

    public ToolBar(Context context) {
        this(context,null);
    }

    public ToolBar(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ToolBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        //获取属性值
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ToolBar);
        titleText=typedArray.getString(R.styleable.ToolBar_titleText);
        leftImageId=typedArray.getResourceId(R.styleable.ToolBar_leftImage,0);
        rightImageId=typedArray.getResourceId(R.styleable.ToolBar_rightImage,0);
        rightAreaIsShow=typedArray.getBoolean(R.styleable.ToolBar_rightIsShow,false);
        leftAreaIsShow=typedArray.getBoolean(R.styleable.ToolBar_leftIsShow,false);
        typedArray.recycle();
        //设置布局获取控件
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.view_toolbar,this);

        titleTv=findViewById(R.id.titleTv);
        rightArea=findViewById(R.id.rightArea);
        leftIma=findViewById(R.id.leftImg);
        rightIma=findViewById(R.id.rightImg);
        rightTv=findViewById(R.id.rightTv);
        //通过属性控制控件的渲染
        titleTv.setText(titleText);
        if (rightAreaIsShow&&rightImageId!=0){
            rightIma.setImageResource(rightImageId);
        }
        if (leftAreaIsShow&&leftImageId!=0){
            leftIma.setImageResource(leftImageId);
        }
        leftIma.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iToolbarListener!=null){
                    iToolbarListener.onLeftClick();
                }
            }
        });
        rightIma.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iToolbarListener!=null){
                    iToolbarListener.onRightImgClick();
                }
            }
        });

    }

    public void updateRightImage(int image){
        rightIma.setImageResource(image);
    }

    public void setToolbarListener(IToolbarListener iToolbarListener){
        this.iToolbarListener=iToolbarListener;
    }
        public static interface IToolbarListener{
        void onLeftClick();
        void onRightImgClick();
        void onRightTvClick();
        }
}
