package com.example.a1809fiannce.adapter;

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

    @Override
    protected void convert(@NotNull BaseViewHolder holder, AllfinancialBean.ResultBean resultBean) {
        holder.setText(R.id.all_investment_title,resultBean.getName());
        holder.setText(R.id.all_investment_money,resultBean.getMoney());
        holder.setText(R.id.all_investment_minTouMoney,resultBean.getMinTouMoney());
        holder.setText(R.id.all_investment_yeatRate,resultBean.getYearRate());
        holder.setText(R.id.all_investment_suodingDays,resultBean.getSuodingDays());
        holder.setText(R.id.all_investment_memberNum,resultBean.getMemberNum());

        ProgressView progressView = holder.getView(R.id.all_investment_progress);
        progressView.saledProgress(Integer.parseInt(resultBean.getProgress()),false);
    }
}
