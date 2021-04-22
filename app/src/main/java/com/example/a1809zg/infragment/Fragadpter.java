package com.example.a1809zg.infragment;

import android.text.Layout;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.a1809zg.MyView;
import com.example.a1809zg.R;
import com.example.net.bean.ProductBean;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Fragadpter extends BaseQuickAdapter<ProductBean.ResultBean, BaseViewHolder> {
    public Fragadpter( @Nullable List<ProductBean.ResultBean> data) {
        super(R.layout.item_money_view, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, ProductBean.ResultBean resultBean) {
       baseViewHolder.setText(R.id.text,resultBean.getName());
       baseViewHolder.setText(R.id.money,resultBean.getMoney()+"元");
       baseViewHolder.setText(R.id.year,resultBean.getYearRate()+"%");
       baseViewHolder.setText(R.id.day,resultBean.getSuodingDays()+"天");
       baseViewHolder.setText(R.id.food,resultBean.getMinTouMoney());
       baseViewHolder.setText(R.id.people,resultBean.getMemberNum());
        MyView view = baseViewHolder.findView(R.id.aaa);
        int i = Integer.parseInt(resultBean.getProgress());
        view.saprogress(i,false);
    }
}
