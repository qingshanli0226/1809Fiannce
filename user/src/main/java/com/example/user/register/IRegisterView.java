package com.example.user.register;

import com.fiannce.bawei.net.model.RegisterBean;

public interface IRegisterView {

    String name();
    String password();
    void onRegisterData(RegisterBean registerBean);

}
