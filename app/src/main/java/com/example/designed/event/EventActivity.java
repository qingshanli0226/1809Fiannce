package com.example.designed.event;

import androidx.viewpager.widget.ViewPager;

import com.example.designed.R;
import com.fiannce.bawei.framework.BaseActivity;


public class EventActivity extends BaseActivity {

    private ViewPager viewPager;

    @Override
    protected void initData() {

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected int getLaoutId() {
        return R.layout.activity_event;
    }

    @Override
    protected void initView() {

        viewPager = (ViewPager) findViewById(R.id.viewPager);

        viewPager.setAdapter(new EventFragmentAdapter(getSupportFragmentManager()));

    }
}
