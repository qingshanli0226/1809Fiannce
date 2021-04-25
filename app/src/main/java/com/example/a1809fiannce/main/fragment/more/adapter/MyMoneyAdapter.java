package com.example.a1809fiannce.main.fragment.more.adapter;


import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;

import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.a1809fiannce.R;
import com.fiannce.bawei.net.mode.ProductBean;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MyMoneyAdapter extends BaseQuickAdapter<ProductBean.ResultBean, BaseViewHolder> {
    public MyMoneyAdapter( @Nullable List<ProductBean.ResultBean> data) {
        super(R.layout.layout_home_one, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, ProductBean.ResultBean resultBean) {
        baseViewHolder.setText(R.id.home_one_name,resultBean.getName());
        baseViewHolder.setText(R.id.home_one_money,resultBean.getMoney());
        baseViewHolder.setText(R.id.home_one_yearrate,resultBean.getYearRate());
        baseViewHolder.setText(R.id.home_one_suodingdays,resultBean.getSuodingDays());
        baseViewHolder.setText(R.id.home_one_mintoumoney,resultBean.getMinTouMoney());
        baseViewHolder.setText(R.id.home_one_membernum,resultBean.getMemberNum());
//        ProgressView view = baseViewHolder.getView(R.id.home_one_progress);
//        view.saledProgress(Integer.parseInt(resultBean.getProgress()),false);
//        baseViewHolder.setText(R.id.home_one_progress,resultBean.getProgress());
    }
}
