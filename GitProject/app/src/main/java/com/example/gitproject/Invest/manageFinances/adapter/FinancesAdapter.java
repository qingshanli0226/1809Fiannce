package com.example.gitproject.Invest.manageFinances.adapter;

import android.view.View;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.framework.view.ProgressView;
import com.example.gitproject.R;
import com.example.net.bean.ProductBean;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class FinancesAdapter extends BaseQuickAdapter<ProductBean.ResultBean, BaseViewHolder> {
    public FinancesAdapter() {
        super(R.layout.item_managefinances, new ArrayList<>());
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, ProductBean.ResultBean resultBean) {
        baseViewHolder.setText(R.id.finances_title,resultBean.getName());
        baseViewHolder.setText(R.id.finances_money,resultBean.getMoney());
        baseViewHolder.setText(R.id.finances_memberNum,resultBean.getMemberNum());
        baseViewHolder.setText(R.id.finances_minTouMoney,resultBean.getMinTouMoney());
        baseViewHolder.setText(R.id.finances_suodingDays,resultBean.getSuodingDays());
        baseViewHolder.setText(R.id.finances_yeatRate,resultBean.getYearRate());
        ProgressView progressView = baseViewHolder.getView(R.id.finances_progress);
        progressView.startProgress(Integer.parseInt(resultBean.getProgress()),false);
    }
}
