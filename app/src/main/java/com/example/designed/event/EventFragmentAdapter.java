package com.example.designed.event;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class EventFragmentAdapter extends FragmentPagerAdapter {

    private Fragment[] fragments = new Fragment[] {new HomeFragment(),new EventFragment(),new MineFragment()};

    public EventFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }
}