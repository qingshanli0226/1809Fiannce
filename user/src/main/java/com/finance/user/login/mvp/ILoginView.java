package com.finance.user.login.mvp;

import com.finance.framework.IBaseView;
import com.finance.net.bean.LoginBean;
import com.finance.net.bean.RegisterBean;

public interface ILoginView extends IBaseView {
    void onLoginData(LoginBean loginBean);
}
