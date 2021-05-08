package com.example.designed;


import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;


import android.view.KeyEvent;
import android.widget.Toast;

import com.example.designed.adapter.FragmentAdapter;
import com.example.designed.fragment.BlankFragment;
import com.example.designed.fragment.Fragment2;
import com.example.designed.fragment.Fragment3;
import com.example.designed.fragment.Fragment4;
import com.example.user.login.LoginActivity;
import com.fiannce.bawei.framework.manager.CacheManager;
import com.fiannce.bawei.framework.manager.FiannceUserManager;
import com.fiannce.bawei.net.model.HomeBean;
import com.fiannce.bawei.net.model.LoginBean;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

//@Route(path = "/main/MainActivity")
public class MainActivity extends AppCompatActivity {

    List<Fragment> list = new ArrayList<>();
    List<String> title = new ArrayList<>();
    private ViewPager vp;
    private TabLayout tab;
    private long time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        HomeBean homeBean = CacheManager.getInstance().getHomeBean();

        title.add("首页");
        title.add("投资");
        title.add("我的资产");
        title.add("更多");

        list.add(new BlankFragment());
        list.add(new Fragment2());
        list.add(new Fragment3());
        list.add(new Fragment4());

        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), list, title);
        vp.setAdapter(fragmentAdapter);
        tab.setupWithViewPager(vp);

        tab.getTabAt(0).setIcon(R.drawable.bottom01);
        tab.getTabAt(1).setIcon(R.drawable.bottom03);
        tab.getTabAt(2).setIcon(R.drawable.bottom05);
        tab.getTabAt(3).setIcon(R.drawable.bottom07);

        tab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if (position==2){
                    LoginBean loginBean = FiannceUserManager.getInstance().getLoginBean();
                    if (loginBean== null){
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
//        Intent intent = new Intent();
//        intent.setAction("event");
//        startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK){

            if (System.currentTimeMillis()-2000>time){
                Toast.makeText(this, "再点击就退出了", Toast.LENGTH_SHORT).show();
                time = System.currentTimeMillis();

                return true;
            }else {
                finish();
            }

        }

        return super.onKeyDown(keyCode, event);

    }

    private void initView() {
        vp = (ViewPager) findViewById(R.id.vp);
        tab = (TabLayout) findViewById(R.id.tab);
    }


}
