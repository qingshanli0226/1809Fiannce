package com.fiannce.user.login;

import com.fiannce.framework.IBaseView;
import com.fiannce.net.mode.LoginBean;
import com.fiannce.net.mode.RegisterBean;

public interface ILoginView extends IBaseView {

    void onLoginData(LoginBean loginBean);

}
