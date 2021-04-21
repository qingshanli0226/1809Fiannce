package com.example.net.comm;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;

public class CusComm implements CustomTabEntity {
    private String title;
    private int selecticon;
    private int unselecticon;

    public CusComm(String title, int selecticon, int unselecticon) {
        this.title = title;
        this.selecticon = selecticon;
        this.unselecticon = unselecticon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSelecticon() {
        return selecticon;
    }

    public void setSelecticon(int selecticon) {
        this.selecticon = selecticon;
    }

    public int getUnselecticon() {
        return unselecticon;
    }

    public void setUnselecticon(int unselecticon) {
        this.unselecticon = unselecticon;
    }

    @Override
    public String getTabTitle() {
        return title;
    }

    @Override
    public int getTabSelectedIcon() {
        return selecticon;
    }

    @Override
    public int getTabUnselectedIcon() {
        return unselecticon;
    }
}
