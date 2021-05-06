package com.example.user.register;

import com.fiannce.bawei.framework.IBaseView;
import com.fiannce.bawei.net.model.RegisterBean;

public interface IRegisterView extends IBaseView {

    String name();
    String password();
    void onRegisterData(RegisterBean registerBean);

}
