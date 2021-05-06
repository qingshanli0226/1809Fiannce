package com.finance.user.register.mvp;

import com.finance.framework.IBaseView;
import com.finance.net.bean.LoginBean;
import com.finance.net.bean.RegisterBean;

public interface IRegisterView extends IBaseView {
    void onRegisterData(RegisterBean registerBean);
    void onLogin(LoginBean loginBean);
    void onAutoLogin(LoginBean loginBean);
}
