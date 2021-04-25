package com.example.a1809fiannce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.a1809fiannce.fragment.HomeFragment;
import com.example.a1809fiannce.fragment.InvestFragment;
import com.example.a1809fiannce.fragment.ManyFragment;
import com.example.a1809fiannce.fragment.MyMoneyFragment;
import com.example.formwork.model.HomeBean;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    private CommonTabLayout tab;
    private ArrayList<CustomTabEntity> list = new ArrayList<>();
    private ArrayList<Fragment> fragmentList=new ArrayList<>();
    private long CurrentTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tab = (CommonTabLayout) findViewById(R.id.tab);

        list.add(new TabCus("主页",R.mipmap.bottom02,R.mipmap.bottom01));
        list.add(new TabCus("投资",R.mipmap.bottom04,R.mipmap.bottom03));
        list.add(new TabCus("我的资产",R.mipmap.bottom06,R.mipmap.bottom05));
        list.add(new TabCus("更多",R.mipmap.bottom08,R.mipmap.bottom07));
        fragmentList.add(new HomeFragment());
        fragmentList.add(new InvestFragment());
        fragmentList.add(new MyMoneyFragment());
        fragmentList.add(new ManyFragment());
        tab.setTabData(list,this,R.id.main,fragmentList);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            if (System.currentTimeMillis()-CurrentTime>=2000){
                Toast.makeText(this, "再点一次退出", Toast.LENGTH_SHORT).show();
                CurrentTime=System.currentTimeMillis();
                return true;
            }else {
                finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}