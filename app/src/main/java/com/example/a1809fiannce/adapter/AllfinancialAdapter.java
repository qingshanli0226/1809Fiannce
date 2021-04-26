package com.example.a1809fiannce.adapter;

import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.a1809fiannce.R;
import com.example.framework.view.ProgressView;
import com.example.net.mode.AllfinancialBean;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AllfinancialAdapter extends BaseQuickAdapter<AllfinancialBean.ResultBean, BaseViewHolder> {
    public AllfinancialAdapter(@Nullable List<AllfinancialBean.ResultBean> data) {
        super(R.layout.item_investment_all_moneymanagement, data);
    }

    private int lastX;
    private View lastView;
    private int lastPosition;
    private int delWidth;

    @Override
    protected void convert(@NotNull BaseViewHolder holder, AllfinancialBean.ResultBean resultBean) {
        holder.setText(R.id.all_investment_title, resultBean.getName());
        holder.setText(R.id.all_investment_money, resultBean.getMoney());
        holder.setText(R.id.all_investment_minTouMoney, resultBean.getMinTouMoney());
        holder.setText(R.id.all_investment_yeatRate, resultBean.getYearRate());
        holder.setText(R.id.all_investment_suodingDays, resultBean.getSuodingDays());
        holder.setText(R.id.all_investment_memberNum, resultBean.getMemberNum());

        ProgressView progressView = holder.getView(R.id.all_investment_progress);
        progressView.saledProgress(Integer.parseInt(resultBean.getProgress()), false);

        TextView delText = holder.getView(R.id.delText);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lastView.scrollTo(0, 0);
                lastView = null;
                lastPosition = -1;
            }
        });

        addChildClickViewIds(R.id.delText);

        holder.itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        lastX = (int) motionEvent.getRawX();
                        holder.itemView.getParent().requestDisallowInterceptTouchEvent(true);
                        return true;

                    case MotionEvent.ACTION_MOVE:
                        if (lastX > 800 && motionEvent.getRawX() < lastX ) {
                            holder.itemView.getParent().requestDisallowInterceptTouchEvent(true);
                            if (lastPosition != holder.getAdapterPosition() && lastView != null) {
                                lastView.scrollTo(0, 0);
                            }

                            lastView = holder.itemView;
                            lastPosition = holder.getAdapterPosition();
                            delWidth = delText.getWidth();

                            int z = (int) (lastX - motionEvent.getRawX());
                            holder.itemView.scrollTo(z, 0);
                            return true;
                        } else if (lastX > 800 && motionEvent.getRawX() > lastX) {
                            holder.itemView.getParent().requestDisallowInterceptTouchEvent(true);

                            lastView = null;
                            lastPosition = -1;

                            int z = (int) (motionEvent.getRawX() - lastX);
                            holder.itemView.scrollTo(-z+delWidth, 0);
                        } else {
                            holder.itemView.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        break;

                    case MotionEvent.ACTION_UP:
                        if (lastX - motionEvent.getRawX() > delWidth / 2) {

                            lastView = holder.itemView;
                            lastPosition = holder.getAdapterPosition();
                            holder.itemView.scrollTo(delWidth, 0);

                        }else if (lastX - motionEvent.getRawX() < delWidth / 2){

                            holder.itemView.scrollTo(0, 0);

                        }
                        break;
                }
                return false;
            }
        });
    }
}
