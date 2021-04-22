package com.example.fiannce.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.fiannce.HomeCallBack;
import com.example.fiannce.R;
import com.example.net.mode.HomeBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private Banner banner;
    private List<String> list = new ArrayList<>();

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_home, container, false);

        banner = (Banner) inflate.findViewById(R.id.banner);

        HomeBean homeBean = HomeCallBack.getHomeCallback().getHomeBean();
        List<HomeBean.ResultBean.ImageArrBean> imageArr = homeBean.getResult().getImageArr();

        for (HomeBean.ResultBean.ImageArrBean imageArrBean : imageArr){
            list.add(imageArrBean.getIMAURL());
        }

        banner.setImages(list);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                String url = (String) path;
                Glide.with(getActivity()).load(url).into(imageView);
            }
        });

        banner.start();

        return inflate;
    }
}