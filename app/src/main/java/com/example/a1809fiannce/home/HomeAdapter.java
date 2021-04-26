package com.example.a1809fiannce.home;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.a1809fiannce.R;
import com.example.network.model.HomeBean;

import java.util.List;

public class HomeAdapter extends BaseQuickAdapter<HomeBean.ResultBean.ImageArrBean, BaseViewHolder> {
    public HomeAdapter(int layoutResId, @Nullable List<HomeBean.ResultBean.ImageArrBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBean.ResultBean.ImageArrBean item) {
        Glide.with(mContext).load(item.getImaurl()).into((ImageView) helper.getView(R.id.img));

    }
}
