package com.Fiannce.myapplication.fragment.home;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.Fiannce.myapplication.R;
import com.bumptech.glide.Glide;
import com.fiannce.framework.manager.CacheManager;
import com.fiannce.net.mode.HomeBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;


import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    private com.Fiannce.myapplication.fragment.home.customView customView;
    private Banner homeBanner;
    private TextView homeTv1;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_home, container, false);
        customView = (customView) inflate.findViewById(R.id.custom_view);
        homeBanner = (Banner) inflate.findViewById(R.id.home_banner);
        homeTv1 = (TextView) inflate.findViewById(R.id.home_tv1);
        customView.startCurrent();

        HomeBean homeBean = CacheManager.getInstance().getHomeBean();
        List<HomeBean.ResultBean.ImageArrBean> imageArr = homeBean.getResult().getImageArr();

        homeBanner.setImages(imageArr);
        homeBanner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                HomeBean.ResultBean.ImageArrBean imageArrBean = (HomeBean.ResultBean.ImageArrBean) path;
                String imaurl = imageArrBean.getIMAURL();
                Glide.with(getActivity()).load(imaurl).into(imageView);
            }
        });
        homeBanner.start();


        return inflate;
    }

    private void initView() {


    }
}
