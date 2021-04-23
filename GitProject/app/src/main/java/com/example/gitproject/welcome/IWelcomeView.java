package com.example.gitproject.welcome;

import com.example.framework.IBaseView;
import com.example.net.bean.HomeBean;
import com.example.net.bean.UpdateBean;

public interface IWelcomeView extends IBaseView {
    public void onHomeData(HomeBean homeBean);
    public void onAppUpdate(UpdateBean updateBean);
}
