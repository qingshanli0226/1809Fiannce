package com.example.user.login;

import com.example.framework.IBaseView;
import com.example.net.mode.LoginBean;
import com.example.net.mode.RegisterBean;

public interface ILogin extends IBaseView {
    String username();

    String password();

    void onLogin(LoginBean loginBean);
}
