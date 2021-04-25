package com.fiannce.bawei.a1809fiannce.event;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class EventFragmentAdapter extends FragmentStatePagerAdapter {
    private Fragment[] fragments = new Fragment[] {new HomeFragment(),new EventFragment(),new MineFragment()};

    public EventFragmentAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }
}
