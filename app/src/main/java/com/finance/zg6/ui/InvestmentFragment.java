package com.finance.zg6.ui;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.finance.framework.BaseFragment;
import com.finance.zg.R;
import com.finance.zg6.ui.investment.ui.allfiannce.AllFinanceFragment;
import com.finance.zg6.ui.investment.ui.hotfinance.HotFinanceFragment;
import com.finance.zg6.ui.investment.ui.RecommendFinanceFragment;
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
        list.add(new AllFinanceFragment());
        list.add(new RecommendFinanceFragment());
        list.add(new HotFinanceFragment());
        sliding.setViewPager(vp, new String[]{
                getString(R.string.investmentFragment_fragment_title_all),
                getString(R.string.investmentFragment_fragment_title_recommend),
                getString(R.string.investmentFragment_fragment_title_hot)}, getActivity(), list);

        
    }

    @Override
    protected void initView() {

        vp = (ViewPager) mView.findViewById(R.id.vp);
        sliding = (SlidingTabLayout) mView.findViewById(R.id.sliding);
    }
}