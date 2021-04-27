package com.example.a1809fiannce.main.invest.allfinancial;

import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.a1809fiannce.R;
import com.example.framework.view.ProgressView;
import com.example.net.model.AllProductBean;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AllProductAdapter extends BaseQuickAdapter<AllProductBean.ResultBean, BaseViewHolder> {


    public AllProductAdapter(@Nullable List<AllProductBean.ResultBean> data) {
        super(R.layout.item_allproduct_rv, data);
    }


    @Override
    protected void convert(@NotNull BaseViewHolder holder, AllProductBean.ResultBean resultBean) {
        holder.setText(R.id.all_investment_title, resultBean.getName());
        holder.setText(R.id.all_investment_money, resultBean.getMoney());
        holder.setText(R.id.all_investment_minTouMoney, resultBean.getMinTouMoney());
        holder.setText(R.id.all_investment_yeatRate, resultBean.getYearRate());
        holder.setText(R.id.all_investment_suodingDays, resultBean.getSuodingDays());
        holder.setText(R.id.all_investment_memberNum, resultBean.getMemberNum());

        ProgressView progressView = holder.getView(R.id.all_investment_progress);
        progressView.beginProgressAnimation(Integer.parseInt(resultBean.getProgress()), false);

        TextView delet = holder.getView(R.id.all_investment_delet);

        addChildClickViewIds(R.id.all_investment_delet);



        holder.itemView.setOnTouchListener((view, ev) -> {
            if (ev.getAction() == MotionEvent.ACTION_DOWN) {
                if (holder.getAdapterPosition() != postion && postion != -1) {
                    if (holder.itemView != itemView && itemView != null) {
                        itemView.scrollTo(0, 0);
                        itemView = null;
                        isstartback = false;
                        postion = -1;
                    }
                }
                lastX = (int) ev.getRawX();
                holder.itemView.getParent().requestDisallowInterceptTouchEvent(true);
                return true;
            } else if (ev.getAction() == MotionEvent.ACTION_MOVE) {
                if ((lastX < 900) && (lastX > ev.getRawX()&& !isstartback)) {


                    holder.itemView.getParent().requestDisallowInterceptTouchEvent(true);
                    int z = (int) (lastX - ev.getRawX());
                    DELET_WIDTH = delet.getWidth();
                    if (z > DELET_WIDTH) {
                        z = DELET_WIDTH;
                    }
                    holder.itemView.scrollTo(z, 0);
                    itemView = holder.itemView;
                    return true;
                } else if ((lastX < 900) && (lastX < ev.getRawX()) && isstartback) {
                    holder.itemView.getParent().requestDisallowInterceptTouchEvent(true);
                    int z = (int) (lastX - ev.getRawX());
                    DELET_WIDTH = delet.getWidth();
                    if (z < -DELET_WIDTH) {
                        z = -DELET_WIDTH;
                    }
                    holder.itemView.scrollTo(DELET_WIDTH + z, 0);
                    itemView = holder.itemView;
                    return true;
                } else if ((lastX < 900) && (lastX > ev.getRawX())&& isstartback) {
                    holder.itemView.scrollTo(DELET_WIDTH, 0);
                    itemView = holder.itemView;
                } else {
                    holder.itemView.getParent().requestDisallowInterceptTouchEvent(false);
                }


            } else if (ev.getAction() == MotionEvent.ACTION_UP) {
                if ((lastX - ev.getRawX()) > DELET_WIDTH / 2) {
                    holder.itemView.scrollTo(DELET_WIDTH, 0);
                    itemView = holder.itemView;
                    isstartback = true;
                    postion = holder.getAdapterPosition();
                } else if ((ev.getRawX() - lastX) < DELET_WIDTH / 2 && isstartback) {
                    holder.itemView.scrollTo(DELET_WIDTH, 0);
                    itemView = holder.itemView;
                } else {
                    holder.itemView.scrollTo(0, 0);
                    itemView = null;
                    isstartback = false;
                    postion = -1;
                }
            }

            return false;
        });
    }

    int lastX;
    private int DELET_WIDTH;
    private boolean isstartback = false;
    View itemView;
    private int postion = -1;

}
