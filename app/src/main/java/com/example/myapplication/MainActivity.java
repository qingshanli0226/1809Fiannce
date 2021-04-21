package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.example.framework.BaseActivity;
import com.example.framework.manager.CacheManager;
import com.example.model.HomeBean;
import com.example.myapplication.fragment.HomeFragment;
import com.example.myapplication.fragment.MoneyFragment;
import com.example.myapplication.fragment.MoreFragment;
import com.example.myapplication.fragment.MymoneyFragment;
import com.google.android.material.tabs.TabLayout;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

@Route(path = "/app/MainActivity")
public class MainActivity extends BaseActivity {

    private List<String> list = new ArrayList<>();
    private Banner banner;
    private TabLayout tab;
    private ViewPager vp;
    private android.widget.RadioButton btnHome;
    private android.widget.RadioButton btnMoney;
    private android.widget.RadioButton btnMymoney;
    private android.widget.RadioButton btnMore;
    private android.widget.RadioGroup group;
    private HomeFragment homeFragment;
    private MoneyFragment moneyFragment;
    private MymoneyFragment mymoneyFragment;
    private MoreFragment moreFragment;

    @Override
    protected void initData() {
        HomeBean homeBean = CacheManager.getInstance().getHomeBean();
        HomeBean.ResultBean result = homeBean.getResult();
        List<HomeBean.ResultBean.ImageArrBean> imageArr = result.getImageArr();

//        for (int i = 0; i < imageArr.size(); i++) {
//            String imaurl = imageArr.get(i).getIMAURL();
//            list.add(imaurl);
//        }
//
//        banner.setImages(imageArr);
//        banner.setImageLoader(new ImageLoader() {
//            @Override
//            public void displayImage(Context context, Object path, ImageView imageView) {
//                HomeBean.ResultBean.ImageArrBean s = (HomeBean.ResultBean.ImageArrBean) path;
//                String imaurl = s.getIMAURL();
//                Glide.with(MainActivity.this).load(imaurl).into(imageView);
//            }
//        }).start();


        homeFragment = new HomeFragment();
        moneyFragment = new MoneyFragment();
        mymoneyFragment = new MymoneyFragment();
        moreFragment = new MoreFragment();

        fragmentManger();


        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentManager supportFragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
                switch (checkedId) {
                    case R.id.btn_home:
//                        Toast.makeText(MainActivity.this, "123", Toast.LENGTH_SHORT).show();
                        fragmentTransaction.show(homeFragment);
                        fragmentTransaction.hide(moneyFragment);
                        fragmentTransaction.hide(mymoneyFragment);
                        fragmentTransaction.hide(moreFragment);
                        break;
                    case R.id.btn_money:
                        fragmentTransaction.hide(homeFragment);
                        fragmentTransaction.show(moneyFragment);
                        fragmentTransaction.hide(mymoneyFragment);
                        fragmentTransaction.hide(moreFragment);
                        break;
                    case R.id.btn_mymoney:
                        fragmentTransaction.show(homeFragment);
                        fragmentTransaction.show(moneyFragment);
                        fragmentTransaction.hide(mymoneyFragment);
                        fragmentTransaction.show(moreFragment);
                        break;
                    case R.id.btn_more:
                        fragmentTransaction.show(homeFragment);
                        fragmentTransaction.show(moneyFragment);
                        fragmentTransaction.show(mymoneyFragment);
                        fragmentTransaction.hide(moreFragment);
                        break;
                }
                fragmentTransaction.commit();
            }
        });

    }

    @Override
    protected void initPresenter() {

    }

    public void fragmentManger(){
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.main_linear,homeFragment);
        fragmentTransaction.add(R.id.main_linear,moneyFragment);
        fragmentTransaction.add(R.id.main_linear,mymoneyFragment);
        fragmentTransaction.add(R.id.main_linear,moreFragment);

        fragmentTransaction.show(homeFragment);
        fragmentTransaction.hide(moneyFragment);
        fragmentTransaction.hide(mymoneyFragment);
        fragmentTransaction.hide(moreFragment);
        fragmentTransaction.commit();


    }

    @Override
    protected void initView() {

//        banner = (Banner) findViewById(R.id.banner);
//        tab = (TabLayout) findViewById(R.id.tab);
//        vp = (ViewPager) findViewById(R.id.vp);
        btnHome = (RadioButton) findViewById(R.id.btn_home);
        btnMoney = (RadioButton) findViewById(R.id.btn_money);
        btnMymoney = (RadioButton) findViewById(R.id.btn_mymoney);
        btnMore = (RadioButton) findViewById(R.id.btn_more);
        group = (RadioGroup) findViewById(R.id.group);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
}