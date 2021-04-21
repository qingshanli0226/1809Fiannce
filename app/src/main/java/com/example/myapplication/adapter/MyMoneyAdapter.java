package com.example.myapplication.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.model.ProductBean;
import com.example.myapplication.R;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MyMoneyAdapter extends BaseQuickAdapter<ProductBean.ResultBean, BaseViewHolder> {
    public MyMoneyAdapter( @Nullable List<ProductBean.ResultBean> data) {
        super(R.layout.layout_home_one, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, ProductBean.ResultBean resultBean) {

    }
}
