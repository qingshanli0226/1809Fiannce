package com.example.user.mvp;

import com.example.framework.BaseView;
import com.example.net.bean.AutoBean;
import com.example.net.bean.LoginBean;
import com.example.net.bean.RegisterBean;

public interface MoreView extends BaseView {
    void initRegister(RegisterBean registerBean);
    void initLogin(LoginBean loginBean);
    void initAuto(AutoBean autoBean);
}
