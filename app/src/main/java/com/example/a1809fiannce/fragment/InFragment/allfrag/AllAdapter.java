package com.example.a1809fiannce.fragment.InFragment.allfrag;

import android.view.View;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.a1809fiannce.R;
import com.example.a1809fiannce.view.CusView;
import com.example.formwork.model.AllBean;

import java.util.List;

public class AllAdapter extends BaseQuickAdapter<AllBean.ResultBean, BaseViewHolder> {
    public AllAdapter(int layoutResId, @Nullable List<AllBean.ResultBean> data) {
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
        CusView cusView = helper.getView(R.id.cusView);
        int progress = Integer.parseInt(item.getProgress());

        cusView.SealedProgress(progress,false);
    }
}
