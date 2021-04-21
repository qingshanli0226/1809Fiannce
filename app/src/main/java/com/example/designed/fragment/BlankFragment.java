package com.example.designed.fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.designed.R;
import com.fiannce.bawei.framework.manager.CacheManager;
import com.fiannce.bawei.framework.view.CustomView;
import com.fiannce.bawei.net.model.HomeBean;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {

    private Banner banner;
    private List<HomeBean.ResultBean.ImageArrBean> list = new ArrayList<>();
    private CustomView vi;

    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_blank, container, false);
        banner = (Banner) inflate.findViewById(R.id.banner);
        vi = (CustomView) inflate.findViewById(R.id.vi);

        HomeBean homeBean = CacheManager.getInstance().getHomeBean();
        list.addAll(homeBean.getResult().getImageArr());

        banner.setImages(list);
        banner.setBannerAnimation(Transformer.CubeIn);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
               HomeBean.ResultBean.ImageArrBean resultBean = (HomeBean.ResultBean.ImageArrBean) path;
                Glide.with(getActivity()).load(resultBean.getIMAURL()).into(imageView);
            }
        });
        banner.start();

        vi.startmcurrent();


        return inflate;
    }

}
