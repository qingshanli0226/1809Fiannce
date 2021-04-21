package com.finance.zg6.ui.investment;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.finance.framework.BaseFragment;
import com.finance.zg.R;
import com.finance.zg6.ui.HomeFragment;
import com.finance.zg6.ui.investment.ui.AllFinancingkFragment;
import com.finance.zg6.ui.investment.ui.HotFinancingFragment;
import com.finance.zg6.ui.investment.ui.RecommendFinancingFragment;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;


public class InvestmentFragment extends BaseFragment {


    private ViewPager vp;
    private SlidingTabLayout sliding;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_one;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {
        ArrayList<Fragment> list = new ArrayList<>();
        list.add(new AllFinancingkFragment());
        list.add(new RecommendFinancingFragment());
        list.add(new HotFinancingFragment());
        sliding.setViewPager(vp, new String[]{"全部理财", "推荐理财", "热门理财"}, getActivity(), list);
    }

    @Override
    protected void initView() {

        vp = (ViewPager) mView.findViewById(R.id.vp);
        sliding = (SlidingTabLayout) mView.findViewById(R.id.sliding);
    }
}