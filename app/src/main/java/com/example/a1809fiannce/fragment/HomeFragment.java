package com.example.a1809fiannce.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.a1809fiannce.HomeCallBack;
import com.example.a1809fiannce.MainActivity2;
import com.example.a1809fiannce.R;
import com.example.a1809fiannce.view.CusView;
import com.example.formwork.model.HomeBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    private Banner bar;
    private List<String> list=new ArrayList<>();
    private CusView cus;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_home, container, false);
        bar = (Banner) inflate.findViewById(R.id.bar);
        cus = (CusView) inflate.findViewById(R.id.cus);
        HomeBean homeBean = HomeCallBack.getHomeCallBack().getHomeBean();
        List<HomeBean.ResultBean.ImageArrBean> imageArr = homeBean.getResult().getImageArr();
        for (HomeBean.ResultBean.ImageArrBean imageArrBean : imageArr) {
            list.add(imageArrBean.getImaurl());
        }
        // HomeAdapter homeAdapter = new HomeAdapter(R.layout.item_home, imageArr);
        bar.setImages(list);
        bar.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                String s= (String) path;
                Log.i("zx", "displayImage: "+s);
                Glide.with(getActivity()).load(s).into(imageView);
            }
        });
        bar.start();

        cus.SealedProgress(50,false);
        return inflate;
    }
}