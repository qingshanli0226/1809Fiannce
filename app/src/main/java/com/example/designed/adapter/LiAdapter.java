package com.example.designed.adapter;

import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.designed.R;
import com.fiannce.bawei.framework.view.CustomView;
import com.fiannce.bawei.net.model.Libean;

import java.util.List;

public class LiAdapter extends BaseQuickAdapter<Libean.ResultBean, BaseViewHolder> {

    private int lastX;
    private  View lastView;
    private int lastPosition;
    public LiAdapter(int layoutResId, @Nullable List<Libean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Libean.ResultBean item) {
        helper.setText(R.id.all_investment_title,item.getName());
        helper.setText(R.id.all_investment_money,item.getMoney());
        helper.setText(R.id.all_investment_yeatRate,item.getYearRate());
        helper.setText(R.id.all_investment_yeatRate,item.getYearRate());
        helper.setText(R.id.all_investment_suodingDays,item.getSuodingDays());
        helper.setText(R.id.all_investment_minTouMoney,item.getMinTouMoney());
        helper.setText(R.id.all_investment_memberNum,item.getMemberNum());


        TextView textView = helper.getView(R.id.textView);


        helper.addOnClickListener(R.id.textView);

        CustomView customView = helper.getView(R.id.custom);
        customView.startmcurrent(Integer.parseInt(item.getProgress()),false);

        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lastView.scrollTo(0,0);
                lastView = null;
                lastPosition = -1;

            }
        });


        helper.itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()){

                    case MotionEvent.ACTION_DOWN:
                        lastX = (int) motionEvent.getRawX();

                        helper.itemView.getParent().requestDisallowInterceptTouchEvent(true);

                        return  true;
                    case MotionEvent.ACTION_MOVE:
                        if (lastX>500 && motionEvent.getRawX()<lastX){
                            helper.itemView.getParent().requestDisallowInterceptTouchEvent(true);

                            if (lastView != null && lastPosition != helper.getAdapterPosition() ){
                               lastView.scrollTo(0,0);
                            }

                            lastPosition = helper.getAdapterPosition();
                            lastView = helper.itemView;
                            helper.itemView.scrollTo((int) (lastX-motionEvent.getRawX()),0);
                            return  true;

                        }else {
                            helper.itemView.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        break;

                }

                return false;
            }
        });

    }




}
