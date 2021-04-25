package com.fiannce.bawei.fragment.investFragment.adapter;

import android.view.View;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fiannce.framework.view.ProgressView;
import com.fiannce.net.mode.InvestBean;
import com.fiannce.zhaoyuzan.R;

import java.util.List;

public class InvestAdapter extends BaseQuickAdapter<InvestBean.ResultBean, BaseViewHolder> {
    public InvestAdapter(int layoutResId, @Nullable List<InvestBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, InvestBean.ResultBean resultBean) {
        baseViewHolder.setText(R.id.alltitle,resultBean.getName());
        baseViewHolder.setText(R.id.allonemoney,resultBean.getMoney());
        baseViewHolder.setText(R.id.alltwopercentage,resultBean.getYearRate());
        baseViewHolder.setText(R.id.allthreeday,resultBean.getSuodingDays());
        baseViewHolder.setText(R.id.allonenum,resultBean.getMinTouMoney());
        baseViewHolder.setText(R.id.allthreenum,resultBean.getMemberNum());


        baseViewHolder.addOnClickListener(R.id.txt_delete);
        ProgressView progressView = baseViewHolder.getView(R.id.allmyview);
        progressView.saledProgress(Integer.valueOf(resultBean.getProgress()),false);
    }
}
