package com.fiannce.user.register;

import com.fiannce.framework.IBaseView;
import com.fiannce.net.mode.RegisterBean;

public interface IRegisterView extends IBaseView {

    void onRegisterData(RegisterBean registerBean);

}
