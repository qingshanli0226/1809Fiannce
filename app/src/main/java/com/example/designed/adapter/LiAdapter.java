package com.example.designed.adapter;

import android.view.View;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.designed.R;
import com.fiannce.bawei.framework.view.CustomView;
import com.fiannce.bawei.net.model.Libean;

import java.util.List;

public class LiAdapter extends BaseQuickAdapter<Libean.ResultBean, BaseViewHolder> {

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

        CustomView customView = helper.getView(R.id.custom);
        customView.startmcurrent(Integer.parseInt(item.getProgress()),false);

    }
}
