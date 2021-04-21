package com.example.a1809fiannce.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class Fragment extends FragmentPagerAdapter {
    private List<androidx.fragment.app.Fragment> list;
    private List<String> strings;

    public Fragment(@NonNull FragmentManager fm, List<androidx.fragment.app.Fragment> list, List<String> strings) {
        super(fm);
        this.list = list;
        this.strings = strings;
    }

    @NonNull
    @Override
    public androidx.fragment.app.Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return strings.get(position);
    }
}
