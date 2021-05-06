package com.example.user.login;

import com.fiannce.bawei.framework.IBaseView;
import com.fiannce.bawei.net.model.LoginBean;

public interface ILoginView extends IBaseView {

    String name();
    String password();
    void onLoginData(LoginBean loginBean);

}
