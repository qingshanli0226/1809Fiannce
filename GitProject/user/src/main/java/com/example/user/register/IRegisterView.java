package com.example.user.register;

import com.example.framework.IBaseView;
import com.example.net.bean.RegisterBean;

public interface IRegisterView extends IBaseView {
    public void onRegister(RegisterBean registerBean);
}
