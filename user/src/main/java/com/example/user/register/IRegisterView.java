package com.example.user.register;

import com.example.framework.IBaseView;
import com.example.net.mode.RegisterBean;

public interface IRegisterView extends IBaseView {
    String username();

    String password();

    void onRegister(RegisterBean registerBean);
}
