package com.example.a1809zg.welcome;

import com.example.frame.IBaseView;
import com.example.net.bean.HomeBean;
import com.example.net.bean.UpdataBean;

public interface IHomeView extends IBaseView {
    void onHomeData(HomeBean homeBean);
    void onUpdaData(UpdataBean updataBean);
}
