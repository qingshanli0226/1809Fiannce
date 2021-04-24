package com.example.fiannce.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.fiannce.R;
import com.example.fiannce.custom_view.CustomView_HomeFragment;
import com.example.net.mode.AllBean;

import java.util.List;

public class AllAdapter_AllFragment extends BaseQuickAdapter<AllBean.ResultBean, BaseViewHolder> {

    public AllAdapter_AllFragment(int layoutResId, @Nullable List<AllBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AllBean.ResultBean item) {
        helper.setText(R.id.tit,item.getName()+"");
        helper.setText(R.id.money,item.getMoney()+"万");
        helper.setText(R.id.Year,item.getYearRate()+"%");
        helper.setText(R.id.date,item.getSuodingDays()+"");
        helper.setText(R.id.TouMoney,item.getMinTouMoney()+"");
        helper.setText(R.id.num,item.getMemberNum()+"");

        CustomView_HomeFragment cusView = helper.getView(R.id.cusView);

        int progress = Integer.parseInt(item.getProgress());

        cusView.SealedProgress(progress,false);
    }
}
