package com.fiance.chengtianle.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class MyVp extends FragmentPagerAdapter {
    private List<Fragment> list;
    private List<String> titlelist;

    public MyVp(@NonNull FragmentManager fm, List<Fragment> list, List<String> titlelist) {
        super(fm);
        this.list = list;
        this.titlelist = titlelist;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titlelist.get(position);
    }

}
