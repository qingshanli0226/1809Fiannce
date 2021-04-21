package com.finance.zg6;


import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.LogUtils;
import com.finance.framework.BaseActivity;
import com.finance.framework.manager.CacheManager;
import com.finance.net.bean.HomeBean;
import com.finance.zg.R;
import com.finance.zg6.ui.MainFragment;
import com.finance.zg6.ui.OneFragment;
import com.finance.zg6.ui.ThreeFragment;
import com.finance.zg6.ui.TwoFragment;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;

@Route(path = "/main/MainActivity")
public class MainActivity extends BaseActivity {

    private ViewPager vp;
    private SlidingTabLayout sliding;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {
        HomeBean homeBean = CacheManager.getInstance().getHomeBean();
        LogUtils.json(homeBean);

        ArrayList<Fragment> list = new ArrayList<>();
        list.add(new MainFragment());
        list.add(new OneFragment());
        list.add(new TwoFragment());
        list.add(new ThreeFragment());
        sliding.setViewPager(vp,new String[]{"首页","投资","我的资产","更多"},this,list);
    }

    @Override
    protected void initView() {
        vp = (ViewPager) findViewById(R.id.vp);
        sliding = (SlidingTabLayout) findViewById(R.id.sliding);
    }
}