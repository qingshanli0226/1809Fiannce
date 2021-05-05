package com.example.user.auto;

import com.example.framwork.IView;
import com.example.network.model.LogBean;

public interface AutoCallBack extends IView {
        void LogData(LogBean logBean);
}
