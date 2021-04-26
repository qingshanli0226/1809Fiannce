package com.fiannce.myapplication.adapter;

import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fiannce.myapplication.R;
import com.fiannce.net.mode.AllMoneyBean;

import java.util.List;

public class AllmoneyAdapter extends BaseQuickAdapter<AllMoneyBean.ResultBean, BaseViewHolder> {

    int rawX;
    View lastItemView;
    int lastPosition;
    int scrollDiffx;
    int measuredWidth;
    public AllmoneyAdapter(int layoutResId, @Nullable List<AllMoneyBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AllMoneyBean.ResultBean item) {
        helper.setText(R.id.rv_all_tv,item.getName());
        helper.setText(R.id.rv_tv1,item.getMoney());
        helper.setText(R.id.rv_tv2,item.getYearRate());
        helper.setText(R.id.rv_tv3,item.getSuodingDays());
        helper.setText(R.id.rv_imgtv1,item.getMinTouMoney());
        helper.setText(R.id.rv_imgtv2,item.getMemberNum());
        helper.setText(R.id.rv_imgtv3,item.getProgress());
        helper.addOnClickListener(R.id.rv_text_delete);

        int position = helper.getPosition();
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastItemView.scrollTo(0,0);
                lastItemView = null;
                lastPosition = 0;
            }
        });

        helper.itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        rawX   = (int) event.getRawX();
                        helper.itemView.getParent().requestDisallowInterceptTouchEvent(true);
                        View view = helper.getView(R.id.rv_text_delete);
                         measuredWidth = view.getMeasuredWidth();
                        return  true;
                    case MotionEvent.ACTION_MOVE:
                        if (rawX > 700 &&event.getRawX()<rawX){
                            helper.itemView.getParent().requestDisallowInterceptTouchEvent(true);
                            if (lastItemView!=null&&position != lastPosition){
                                lastItemView.scrollTo(0,0);
                            }
                            lastPosition = position;
                            lastItemView = helper.itemView;
                            scrollDiffx = - (int)(event.getRawX()-rawX);
                            if(scrollDiffx <= 200){
                                helper.itemView.scrollTo(scrollDiffx,0);
                            }

                            return true;
                        }else {
                            helper.itemView.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        if (scrollDiffx < measuredWidth/2){
                            helper.itemView.scrollTo(0,0);
                        }else if (scrollDiffx > measuredWidth/2){
                            helper.itemView.scrollTo(measuredWidth,0);
                        }

                        return true;
                }
                return false;
            }
        });
    }
}
