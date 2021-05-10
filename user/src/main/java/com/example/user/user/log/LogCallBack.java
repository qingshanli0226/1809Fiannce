package com.example.user.user.log;

import com.example.framwork.IView;
import com.example.network.model.LogBean;

public interface LogCallBack extends IView {
        void LogData(LogBean logBean);
}
