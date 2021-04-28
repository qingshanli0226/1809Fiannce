package com.example.user.login;

import com.example.framework.IBaseView;
import com.example.model.LoginBean;

public interface IPersonLoginView extends IBaseView {

    void onLogin(LoginBean loginBean);

}
