package com.fiannce.bawei.user;

import com.fiannce.bawei.net.mode.HomeBean;
import com.fiannce.bawei.net.mode.ProductBean;
import com.fiannce.bawei.net.mode.VersionBean;
import com.fiannce.bawei.net.user.login.bean.LoginBean;
import com.fiannce.bawei.net.user.register.bean.RegisterBean;

public interface UserView {

        void onLoginData(LoginBean loginBean);
        void onRegisterData(RegisterBean registerBean);

}
