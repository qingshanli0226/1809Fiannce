package com.example.a1809fiannce.main.fragment.more.adapter;


import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;

import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.a1809fiannce.R;
import com.fiannce.bawei.framework.view.ProgressView;
import com.fiannce.bawei.net.mode.ProductBean;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MyMoneyAdapter extends BaseQuickAdapter<ProductBean.ResultBean, BaseViewHolder> {

    private int lastX;
    private int lastY;
    private View lastItemView;
    private int lastposition;
    private int scrollDiffx;
    private int viewWindh = 0;
    private TextView dismiss;
    public MyMoneyAdapter( @Nullable List<ProductBean.ResultBean> data) {
        super(R.layout.layout_home_one, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, ProductBean.ResultBean resultBean) {
        holder.setText(R.id.home_one_name,resultBean.getName());
        holder.setText(R.id.home_one_money,resultBean.getMoney());
        holder.setText(R.id.home_one_yearrate,resultBean.getYearRate());
        holder.setText(R.id.home_one_suodingdays,resultBean.getSuodingDays());
        holder.setText(R.id.home_one_mintoumoney,resultBean.getMinTouMoney());
        holder.setText(R.id.home_one_membernum,resultBean.getMemberNum());
        dismiss = holder.getView(R.id.home_one_dismiss);

        if (resultBean.ismBoolean()){

        }else {

        }

        int position = holder.getPosition();
        dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastItemView = null;
                scrollDiffx = 0;
                holder.itemView.scrollTo(0,0);
            }
        });

        holder.itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        lastX = (int) event.getRawX();
                        lastY = (int) event.getRawY();
                        viewWindh = dismiss.getMeasuredWidth();
                        holder.itemView.scrollTo(0,0);
                        return true;
                    case MotionEvent.ACTION_MOVE:

                        if (lastX > 500 && event.getRawX() < lastX){
                            if (lastItemView!=null && lastposition!=position){
                                lastItemView.scrollTo(0,0);
                            }
                            lastposition = position;
                            lastItemView = holder.itemView;
                            scrollDiffx = - (int) (event.getRawX() - lastX);
                            holder.itemView.scrollTo(scrollDiffx,0);
                            return true;
                        }
//                        if (lastY>=event.getY()||lastY<event.getY()){
//
//
//
//                            return false;
//                        }
                    case MotionEvent.ACTION_UP:

                        if (scrollDiffx < viewWindh/2){
                            lastItemView = null;
                            scrollDiffx = 0;
                            holder.itemView.scrollTo(scrollDiffx,0);
                        }else {
                            holder.itemView.scrollTo(viewWindh,0);
                        }
                        return true;
                }

                return false;
            }
        });
        ProgressView view = holder.getView(R.id.home_one_progress);
        view.saledProgress(Integer.parseInt(resultBean.getProgress()),false);
//        baseViewHolder.setText(R.id.home_one_progress,resultBean.getProgress());
    }
}
