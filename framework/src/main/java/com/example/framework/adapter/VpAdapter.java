package com.example.framework.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

public class VpAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> list;
    private List<String> strings;

    public VpAdapter(@NonNull FragmentManager fm, List<Fragment> list, List<String> strings) {
        super(fm);
        this.list = list;
        this.strings = strings;
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
        return strings.get(position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

    }
}
