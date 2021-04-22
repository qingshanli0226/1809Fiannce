package com.example.a1809zg;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Toolbar extends RelativeLayout {
    private String titletext;
    private int leftimg,rightimg;
    private boolean isleft,isright;
    private TextView title;
    private ImageView left,right;
    private ItoolbarListenr itoolbarListenr;

    public Toolbar(Context context) {
        this(context,null);
    }

    public Toolbar(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public Toolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.Toolbar);
         titletext = typedArray.getString(R.styleable.Toolbar_titletext);
         leftimg = typedArray.getResourceId(R.styleable.Toolbar_leftimg, 0);
         rightimg = typedArray.getResourceId(R.styleable.Toolbar_rightimg, 0);
         isleft = typedArray.getBoolean(R.styleable.Toolbar_isleft, false);
         isright = typedArray.getBoolean(R.styleable.Toolbar_isright, false);
         typedArray.recycle();

        LayoutInflater from = LayoutInflater.from(context);
        from.inflate(R.layout.item_toolbar,this);

         title = findViewById(R.id.title);
         left = findViewById(R.id.leftimg);
         right =findViewById(R.id.rigtimg);
         title.setText(titletext);
         if (isleft&&leftimg!=0){
             left.setImageResource(leftimg);
         }
         if (isright&&rightimg!=0){
             right.setImageResource(rightimg);
         }
         left.setOnClickListener(new OnClickListener() {
             @Override
             public void onClick(View v) {
               if (itoolbarListenr!=null){
                   itoolbarListenr.onLeftClick();
               }
             }
         });
         right.setOnClickListener(new OnClickListener() {
             @Override
             public void onClick(View v) {
                 if (itoolbarListenr!=null){
                     itoolbarListenr.onRightClick();
                 }
             }
         });
    }
    public void setToolbarlistenr(ItoolbarListenr itoolbarListenr){
        this.itoolbarListenr=itoolbarListenr;
    }
public static interface ItoolbarListenr{
        void onLeftClick();
        void onRightClick();
        void onTextClick();
}
}
