package com.example.a1809fiannce.home;

import android.content.Context;

import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.a1809fiannce.R;
import com.example.a1809fiannce.view.CusView;
import com.example.framwork.base.BaseFragment;
import com.example.framwork.view.TobView;
import com.example.network.model.HomeBean;
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
        cus.SealedProgress(90,true);
        expect.setText("预期年利润："+homeBean.getResult().getProInfo().getYearRate()+"%");

        tobView.setImgCallBackListener(new TobView.iImgCallBack() {
            @Override
            public void OnLeftImgListener() {
                Toast.makeText(getContext(), "这是图片", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void OnRightImgListener() {
                Toast.makeText(getContext(), "这是图片", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void initView() {
        bar = baseView.findViewById(R.id.bar);
        cus = baseView.findViewById(R.id.cus);
        name = baseView.findViewById(R.id.name);

        expect = (TextView) baseView.findViewById(R.id.expect);
    }

    @Override
    protected int FindLayout1() {
        return R.layout.fragment_home;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (cus!=null){
            cus.Destroy();
        }
    }
}