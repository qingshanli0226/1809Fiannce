package com.example.a1809fiannce.mian;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.example.a1809fiannce.R;
import com.example.a1809fiannce.money.MyMoneyFragment;
import com.example.a1809fiannce.home.HomeFragment;
import com.example.a1809fiannce.lnvest.InvestFragment;
import com.example.a1809fiannce.many.ManyFragment;
import com.example.common.UserCallBack;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;


import java.io.File;
import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    private CommonTabLayout tab;
    private ArrayList<CustomTabEntity> list = new ArrayList<>();
    private ArrayList<Fragment> fragmentList=new ArrayList<>();
    private long CurrentTime = 0;
    private ViewPager vp;
    private String name;
    private Broad broad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        broad = new Broad();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("complete");
        registerReceiver(broad,intentFilter);
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            requestPermissions(new String[]{
                    Manifest.permission.CALL_PHONE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.SYSTEM_ALERT_WINDOW,
            },100);
        }

        tab = (CommonTabLayout) findViewById(R.id.tab);
        vp = (ViewPager) findViewById(R.id.main);
        list.clear();
        list.add(new MainTabCus("主页",R.mipmap.bottom02,R.mipmap.bottom01));
        list.add(new MainTabCus("投资",R.mipmap.bottom04,R.mipmap.bottom03));
        list.add(new MainTabCus("我的资产",R.mipmap.bottom06,R.mipmap.bottom05));
        list.add(new MainTabCus("更多",R.mipmap.bottom08,R.mipmap.bottom07));

        fragmentList.add(new HomeFragment());
        fragmentList.add(new InvestFragment());
        fragmentList.add(new MyMoneyFragment());
        fragmentList.add(new ManyFragment());
        MainFragment mainFragment = new MainFragment(getSupportFragmentManager(), fragmentList);
        vp.setAdapter(mainFragment);
        tab.setTabData(list);
        tab.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                vp.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tab.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        int num = intent.getIntExtra("num", 0);
        name = intent.getStringExtra("name");

        tab.setCurrentTab(num);
        vp.setCurrentItem(num);
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
    class Broad extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("complete")){
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);
                builder.setTitle("下载完成");
                builder.setMessage("是否安装");
                builder.setIcon(R.mipmap.app_name);
                builder.setPositiveButton("安装", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        Intent intent = new Intent(Intent.ACTION_VIEW);
//                        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
//                        startActivity(intent);
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "取消安装", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broad);
    }
}