package com.example.user.register;

import com.example.framework.IBaseView;
import com.example.model.LoginBean;
import com.example.model.RegisterBean;

public interface IPersonRegisterView extends IBaseView {
    void onRegister(RegisterBean registerBean);
}
