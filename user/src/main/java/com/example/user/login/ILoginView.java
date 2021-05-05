package com.example.user.login;

import com.fiannce.bawei.net.model.LoginBean;

public interface ILoginView {

    String name();
    String password();
    void onLoginData(LoginBean loginBean);

}
