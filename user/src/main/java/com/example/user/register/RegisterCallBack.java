package com.example.user.register;

import com.example.framework.IBaseView;
import com.example.net.mode.RegBean;

public interface RegisterCallBack extends IBaseView {

    void RegData(RegBean regBean);
}
