package com.example.a1809zg.welcome;

import com.example.frame.IBaseview;
import com.example.net.bean.HomeBean;
import com.example.net.bean.ProductBean;
import com.example.net.bean.UpdataBean;

public interface Iview extends IBaseview {
    void onHomeData(HomeBean homeBean);
    void onUpdaData(UpdataBean updataBean);
}
