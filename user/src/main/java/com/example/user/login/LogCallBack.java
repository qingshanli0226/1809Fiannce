package com.example.user.login;

import com.example.framework.IBaseView;
import com.example.net.mode.LogBean;

public interface LogCallBack extends IBaseView {
    void LogData(LogBean logBean);
}
