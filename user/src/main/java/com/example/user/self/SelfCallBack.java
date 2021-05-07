package com.example.user.self;

import com.example.framework.IBaseView;
import com.example.net.mode.LogBean;

public interface SelfCallBack extends IBaseView {
    void LogData(LogBean logBean);
}
