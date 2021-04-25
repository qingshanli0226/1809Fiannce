package com.example.myfinancial;


import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.framework.BaseActivity;
import com.example.myfinancial.fragment.HomeFragment;
import com.example.myfinancial.fragment.inves.invesmvp.InvestFragment;
import com.example.myfinancial.fragment.MoreFragment;
import com.example.myfinancial.fragment.MyMoneyFragment;
import com.example.net.bean.HomeBean;
import com.example.net.comm.CusComm;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;

import java.util.ArrayList;

@Route(path = "/main/MainActivity")
public class MainActivity extends BaseActivity {

    private HomeBean homeBean;
    private com.flyco.tablayout.CommonTabLayout tab;

    private ArrayList<Fragment> list = new ArrayList<>();
    private ArrayList<String> strings = new ArrayList<>();
    private ArrayList<CustomTabEntity> cusComms = new ArrayList<>();

    @Override
    public void initPresenter() {

    }

    @Override
    public void initData() {
//         homeBean = CaCheHome.getInstance().getHomeBean();
//        Log.d("MainActivity123", homeBean.toString());
        //轮播图
//        initlbt();
        //获取对象
        list.add(new HomeFragment());
        list.add(new InvestFragment());
        list.add(new MyMoneyFragment());
        list.add(new MoreFragment());

        strings.add(getString(R.string.hometool));
        strings.add(getString(R.string.investtool));
        strings.add(getString(R.string.mymoneytool));
        strings.add(getString(R.string.moretool));

        cusComms.add(new CusComm(strings.get(0), R.drawable.select_home, R.drawable.unselect_home));
        cusComms.add(new CusComm(strings.get(1), R.drawable.select_wallet, R.drawable.unselect_wallet));
        cusComms.add(new CusComm(strings.get(2), R.drawable.select_money, R.drawable.unselect_money));
        cusComms.add(new CusComm(strings.get(3), R.drawable.select_more, R.drawable.unselect_more));
        tab.setTextSelectColor(Color.BLUE);
        tab.setTextUnselectColor(Color.BLACK);
        tab.setTabData(cusComms, this, R.id.vp, list);

    }


    @Override
    public void initView() {
        tab = (CommonTabLayout) findViewById(R.id.tab);

    }

    @Override
    public int getbandLayout() {
        return R.layout.activity_main;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.d("ZKH","activity 收到down");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("ZKH","activity move");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("ZKH","activity up");
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.d("ZKH onTouchEvent","activity 收到down");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("ZKH onTouchEvent","activity move");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("ZKH onTouchEvent","activity up");
                break;
        }
        return super.onTouchEvent(event);
    }
}