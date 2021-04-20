package com.example.gitproject.welcome;

import com.example.framework.BaseView;
import com.example.net.bean.HomeBean;
import com.example.net.bean.UpdateBean;

public interface WelcomeView extends BaseView{
    public void onHomeData(HomeBean homeBean);
    public void onAppUpdate(UpdateBean updateBean);
}
