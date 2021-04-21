package com.example.a1809fiannce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.a1809fiannce.fragment.HomeFragment;
import com.example.a1809fiannce.fragment.InvestFragment;
import com.example.a1809fiannce.fragment.ManyFragment;
import com.example.a1809fiannce.fragment.MyMoneyFragment;
import com.example.formwork.model.HomeBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    private Banner bar;
    private List<String> list=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        bar = (Banner) findViewById(R.id.bar);
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
                Glide.with(MainActivity2.this).load(s).into(imageView);
            }
        });
        bar.start();
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        HomeFragment homeFragment = new HomeFragment();
        InvestFragment investFragment = new InvestFragment();
        MyMoneyFragment myMoneyFragment = new MyMoneyFragment();
        ManyFragment manyFragment = new ManyFragment();
        beginTransaction.add(R.id.main,homeFragment);
        beginTransaction.add(R.id.main,investFragment);
        beginTransaction.add(R.id.main,myMoneyFragment);
        beginTransaction.add(R.id.main,manyFragment);
        beginTransaction.show(homeFragment);
        beginTransaction.hide(investFragment);
        beginTransaction.hide(myMoneyFragment);
        beginTransaction.hide(manyFragment);


    }
}