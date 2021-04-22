package com.example.framework.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.framework.R;

public class ToolBar extends RelativeLayout {
    private String  text;
    private int text_color= Color.RED,back_color= Color.GRAY;
    private int left_src,right_src;
    private boolean left_isshow,right_ishow;

    private ToolbarOnClickLisenter toolbarOnClickLisenter;

    public void setToolbarOnClickLisenter(ToolbarOnClickLisenter toolbarOnClickLisenter) {
        this.toolbarOnClickLisenter = toolbarOnClickLisenter;
    }

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
        View inflate = LayoutInflater.from(context).inflate(R.layout.toolbar_layout, this);
        ImageView left_re = inflate.findViewById(R.id.left_re);
        ImageView right_set = inflate.findViewById(R.id.right_set);
        TextView center_title = inflate.findViewById(R.id.center_title);
        RelativeLayout toolbar_back = inflate.findViewById(R.id.toolbar_back);

        if(attrs != null){
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ToolBar);
            text = (String) typedArray.getText(R.styleable.ToolBar_app_text);
            text_color = typedArray.getColor(R.styleable.ToolBar_app_text_color, text_color);
            back_color = typedArray.getColor(R.styleable.ToolBar_app_back_color_toob, back_color);
            left_src = typedArray.getResourceId(R.styleable.ToolBar_app_left_src, 0);
            right_src = typedArray.getResourceId(R.styleable.ToolBar_app_right_src, 0);
            left_isshow = typedArray.getBoolean(R.styleable.ToolBar_app_left_isshow, left_isshow);
            right_ishow = typedArray.getBoolean(R.styleable.ToolBar_app_right_isshow, right_ishow);
            typedArray.recycle();
        }
        center_title.setText(text);
        center_title.setTextColor(text_color);
        toolbar_back.setBackgroundColor(back_color);

        if(left_src !=0 && left_isshow){
            left_re.setImageResource(left_src);
        }
        if(right_src !=0 && right_ishow){
            right_set.setImageResource(right_src);
        }

        left_re.setOnClickListener(v -> {
            if (toolbarOnClickLisenter != null) {
                toolbarOnClickLisenter.onClickLeft();
            }
        });
        right_set.setOnClickListener(v -> {
            if (toolbarOnClickLisenter != null) {
                toolbarOnClickLisenter.onClickRight();
            }
        });
        center_title.setOnClickListener(v -> {
            if (toolbarOnClickLisenter != null) {
                toolbarOnClickLisenter.onClickCenter();
            }
        });

    }


    public static interface ToolbarOnClickLisenter{
        void onClickCenter();
        void onClickLeft();
        void onClickRight();
    }

}
