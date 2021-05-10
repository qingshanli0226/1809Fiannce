package com.example.user.user.reg;


import com.example.framwork.IView;
import com.example.network.model.RegBean;

public interface RegisterCallBack extends IView{
    void RegData(RegBean regBean);

}
