package com.example.a1809fiannce.mainActivity.fragment;



import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;



import com.example.a1809fiannce.R;
import com.example.a1809fiannce.mainActivity.fragment.more.Money_OneFragment;
import com.example.a1809fiannce.mainActivity.fragment.more.Money_ThreeFragment;
import com.example.a1809fiannce.mainActivity.fragment.more.Money_TwoFragment;
import com.example.a1809fiannce.mainActivity.fragment.more.adapter.MyFragmentAdapter;
import com.fiannce.bawei.framework.BaseFragmnet;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoreFragment extends BaseFragmnet {

    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> stringList = new ArrayList<>();
    private TabLayout moneyTab;
    private ViewPager moneyVp;
    private MyFragmentAdapter adapter;

    @Override
    protected void initData() {

        fragmentList.add(new Money_OneFragment());
        fragmentList.add(new Money_TwoFragment());
        fragmentList.add(new Money_ThreeFragment());

        stringList.add("全部理财");
        stringList.add("推荐理财");
        stringList.add("热门理财");

        adapter = new MyFragmentAdapter(getChildFragmentManager(),fragmentList,stringList);
        moneyVp.setAdapter(adapter);
        moneyTab.setupWithViewPager(moneyVp);


    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {

        moneyTab = (TabLayout) mView.findViewById(R.id.money_tab);
        moneyVp = (ViewPager) mView.findViewById(R.id.money_vp);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_more;
    }

}
