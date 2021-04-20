package com.example.a1809fiannce;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.Glide;
import com.example.framework.manager.CacheManager;
import com.example.net.mode.HomeBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

public class HoemFragment extends Fragment {
    private Banner bann;

    public HoemFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_hoem, container, false);

        bann = (Banner) inflate.findViewById(R.id.bann);

        HomeBean homeBean = CacheManager.getInstance().getHomeBean();

        bann.setImages(homeBean.getResult().getImageArr());

        bann.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                HomeBean.ResultBean.ImageArrBean imageArrBean= (HomeBean.ResultBean.ImageArrBean) path;

                LogUtils.d(imageArrBean);
                Glide.with(context).load(imageArrBean.getImaurl()).into(imageView);
            }
        });

        bann.start();

        return inflate;
    }
}