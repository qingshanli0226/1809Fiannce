package com.example.a1809zg.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class FragPageAdapter extends FragmentPagerAdapter {
   private List<Fragment> list;
   private List<String> data;

    public FragPageAdapter(@NonNull FragmentManager fm, List<Fragment> list, List<String> data) {
        super(fm);
        this.list = list;
        this.data = data;
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
        return data.get(position);
    }
}
