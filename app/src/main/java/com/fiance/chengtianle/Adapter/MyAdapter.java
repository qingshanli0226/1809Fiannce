package com.fiance.chengtianle.Adapter;

import android.view.View;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.fiance.chengtianle.R;
import com.fiance.framework.MyView.MyView;
import com.fiance.net.mode.LcBean;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MyAdapter extends BaseQuickAdapter<LcBean.ResultBean, BaseViewHolder> {
    public MyAdapter(int layoutResId, @Nullable List<LcBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, LcBean.ResultBean resultBean) {
        baseViewHolder.setText(R.id.alltitle,resultBean.getName());
        baseViewHolder.setText(R.id.allonemoney,resultBean.getMoney());
        baseViewHolder.setText(R.id.alltwopercentage,resultBean.getYearRate());
        baseViewHolder.setText(R.id.allthreeday,resultBean.getSuodingDays());
        baseViewHolder.setText(R.id.allonenum,resultBean.getMinTouMoney());
        baseViewHolder.setText(R.id.allthreenum,resultBean.getMemberNum());
        MyView myView = baseViewHolder.getView(R.id.allmyview);
        myView.saledProgress(Integer.parseInt(resultBean.getProgress()),false);

    }
}
