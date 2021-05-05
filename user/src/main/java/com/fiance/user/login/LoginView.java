package com.fiance.user.login;

import com.fiance.framework.IBaseView;
import com.fiance.net.mode.LoginBean;

public interface LoginView extends IBaseView {
    void onLoginData(LoginBean loginBean);
}
