package com.fiannce.user.autologin;

import com.fiannce.framework.IBaseView;
import com.fiannce.net.mode.AutoLoginBean;

public interface IAutoLoginView extends IBaseView {

    void getAutoLogin(AutoLoginBean autoLoginBean);
}
