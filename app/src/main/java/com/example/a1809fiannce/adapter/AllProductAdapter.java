package com.example.a1809fiannce.adapter;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.a1809fiannce.R;
import com.example.framework.view.ProgressView;
import com.example.net.model.AllProductBean;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AllProductAdapter extends BaseQuickAdapter<AllProductBean.ResultBean, BaseViewHolder> {
    public AllProductAdapter( @Nullable List<AllProductBean.ResultBean> data) {
        super(R.layout.item_allproduct_rv, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, AllProductBean.ResultBean resultBean) {

        holder.setText(R.id.all_investment_title,resultBean.getName());
        holder.setText(R.id.all_investment_money,resultBean.getMoney());
        holder.setText(R.id.all_investment_minTouMoney,resultBean.getMinTouMoney());
        holder.setText(R.id.all_investment_yeatRate,resultBean.getYearRate());
        holder.setText(R.id.all_investment_suodingDays,resultBean.getSuodingDays());
        holder.setText(R.id.all_investment_memberNum,resultBean.getMemberNum());

        ProgressView progressView = holder.getView(R.id.all_investment_progress);
        progressView.beginProgressAnimation(Integer.parseInt(resultBean.getProgress()),false);
    }
}
