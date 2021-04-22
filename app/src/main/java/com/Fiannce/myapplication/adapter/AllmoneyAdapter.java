package com.Fiannce.myapplication.adapter;

import androidx.annotation.Nullable;

import com.Fiannce.myapplication.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fiannce.net.mode.AllMoneyBean;

import java.util.List;

public class AllmoneyAdapter extends BaseQuickAdapter<AllMoneyBean.ResultBean, BaseViewHolder> {

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
    }
}
