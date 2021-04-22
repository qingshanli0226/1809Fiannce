package com.fiannce.bawei.framework.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class ToolbarView extends RelativeLayout {
    public ToolbarView(Context context) {
        super(context);
        init(context,null,0);
    }

    public ToolbarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs,0);
    }

    public ToolbarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);

    }

    public void init(Context context,AttributeSet attrs,int defStyleAttr ){


    }


}
