package com.example.a1809fiannce.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import com.example.a1809fiannce.R;
import com.example.a1809fiannce.money.MyMoneyFragment;
import com.example.a1809fiannce.home.HomeFragment;
import com.example.a1809fiannce.lnvest.InvestFragment;
import com.example.a1809fiannce.many.ManyFragment;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;


import java.io.File;
import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    private CommonTabLayout tab;
    private ArrayList<CustomTabEntity> list = new ArrayList<>();
    private ArrayList<Fragment> fragmentList = new ArrayList<>();
    private long CurrentTime = 0;
    private ViewPager vp;
    private Broad broad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        broad = new Broad();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("complete");
        intentFilter.addAction("verity");
        registerReceiver(broad, intentFilter);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{
                    Manifest.permission.CALL_PHONE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.REQUEST_INSTALL_PACKAGES,
            }, 100);
        }
        //??????????????????????????????
        SharedPreferences sharedPreferences = getSharedPreferences("install", MODE_PRIVATE);
        boolean isInstall = sharedPreferences.getBoolean("isInstall", false);
        if (isInstall) {
            Toast.makeText(this, "??????????????????????????????", Toast.LENGTH_SHORT).show();
            installwindow();
        }
        tab = (CommonTabLayout) findViewById(R.id.tab);
        vp = (ViewPager) findViewById(R.id.main);
        list.clear();

        list.add(new MainTabCus("??????", R.mipmap.bottom02, R.mipmap.bottom01));
        list.add(new MainTabCus("??????", R.mipmap.bottom04, R.mipmap.bottom03));
        list.add(new MainTabCus("????????????", R.mipmap.bottom06, R.mipmap.bottom05));
        list.add(new MainTabCus("??????", R.mipmap.bottom08, R.mipmap.bottom07));

        fragmentList.add(new HomeFragment());
        fragmentList.add(new InvestFragment());
        fragmentList.add(new MyMoneyFragment());
        fragmentList.add(new ManyFragment());

        MainFragment mainFragment = new MainFragment(getSupportFragmentManager(), fragmentList);
        vp.setAdapter(mainFragment);
        tab.setTabData(list);


        //ViewPager???TabLayout??????
        tab.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                vp.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        //ViewPager???TabLayout??????
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
        tab.setCurrentTab(0);
        vp.setCurrentItem(0);
    }
    //????????????
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - CurrentTime >= 2000) {
                Toast.makeText(this, "??????????????????", Toast.LENGTH_SHORT).show();
                CurrentTime = System.currentTimeMillis();
                return true;
            } else {
                finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }
    //??????
    class Broad extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("complete")) {
                installwindow();
            }else if (intent.getAction().equals("verity")){
                vp.setCurrentItem(3);
                tab.setCurrentTab(3);
            }
        }
    }
    //??????
    private void installwindow() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);
        builder.setTitle("????????????");
        builder.setMessage("????????????");
        builder.setIcon(R.mipmap.app_name);
        builder.setPositiveButton("??????", new DialogInterface.OnClickListener() {
            @SuppressLint("CommitPrefEdits")
            @Override
            public void onClick(DialogInterface dialog, int which) {
                openAPK("/sdcard/Download/a.apk");
                SharedPreferences sharedPreferences = getSharedPreferences("install", MODE_PRIVATE);
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putBoolean("isInstall",false);
                edit.commit();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("??????", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(MainActivity2.this, "????????????", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void openAPK(String fileSavePath) {
        File file = new File(Uri.parse(fileSavePath).getPath());
        String filePath = file.getAbsolutePath();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri data = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//????????????????????????7.0
            // ???????????????uri??????
            // ?????? ????????????com.ausee.fileprovider ???apk???????????????.fileprovider???
            data = FileProvider.getUriForFile(this, "com.example.a1809fiannce", new File(filePath));
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);// ?????????????????????????????????
        } else {
            data = Uri.fromFile(file);
        }

        intent.setDataAndType(data, "application/vnd.android.package-archive");
        startActivity(intent);
        SharedPreferences sharedPreferences = getSharedPreferences("install", MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean("isInstall",false);
        edit.commit();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broad);
        list.clear();
        fragmentList.clear();
    }
}