package com.fiance.user.register;

import com.fiance.framework.IBaseView;
import com.fiance.net.mode.RegisterBean;

public interface RegisterView extends IBaseView {
    void onRegisterData(RegisterBean registerBean);
}
