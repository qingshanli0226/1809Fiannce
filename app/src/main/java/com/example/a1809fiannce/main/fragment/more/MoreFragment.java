package com.example.a1809fiannce.main.fragment.more;



import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;



import com.example.a1809fiannce.R;
import com.example.a1809fiannce.main.fragment.more.adapter.MyFragmentAdapter;
import com.fiannce.bawei.framework.BaseFragmnet;
import com.fiannce.bawei.framework.view.ToolBar;
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

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_more;
    }

}
