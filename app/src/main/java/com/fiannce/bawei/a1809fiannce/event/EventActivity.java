package com.fiannce.bawei.a1809fiannce.event;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fiannce.bawei.a1809fiannce.R;
import com.fiannce.bawei.framework.BaseActivity;

import androidx.viewpager.widget.ViewPager;


@Route(path = "/event/EventActivity")
public class EventActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_event;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new EventFragmentAdapter(getSupportFragmentManager()));
    }
}
