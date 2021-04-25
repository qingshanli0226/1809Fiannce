package com.example.a1809fiannce.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.a1809fiannce.HomeCallBack;
import com.example.a1809fiannce.R;
import com.example.a1809fiannce.view.CusView;
import com.example.formwork.model.HomeBean;
import com.example.network.BaseFragment;
import com.example.network.TobView;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends BaseFragment {
    private Banner bar;
    private List<String> list=new ArrayList<>();
    private CusView cus;
    private TextView name;
    private TextView expect;

    @Override
    protected void initData() {
        HomeBean homeBean = HomeCallBack.getHomeCallBack().getHomeBean();
        Log.i("aa", "onCreateView: "+homeBean.toString());
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
        name.setText(homeBean.getResult().getProInfo().getName()+"");
        cus.SealedProgress(50,true);
        expect.setText("预期年利润："+homeBean.getResult().getProInfo().getYearRate()+"%");

        tobView.ImgCallBackListener(new TobView.ImgCallBack() {
            @Override
            public void OnLeftImg() {
                Toast.makeText(getContext(), "这是图片", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void OnRightImg() {
                Toast.makeText(getContext(), "这是图片", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void initView() {
        bar = BaseView.findViewById(R.id.bar);
        cus = BaseView.findViewById(R.id.cus);
        name = BaseView.findViewById(R.id.name);

        expect = (TextView) BaseView.findViewById(R.id.expect);
    }

    @Override
    protected int FindLayout1() {
        return R.layout.fragment_home;
    }

}