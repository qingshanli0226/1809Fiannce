package com.example.a1809fiannce;

import com.example.formwork.model.AllBean;
import com.example.formwork.model.HomeBean;
import com.example.formwork.model.UpdateBean;
import com.example.network.IView;

public interface CallBack  extends IView{

    void HomeData(HomeBean homeBean);

    void UpdateData(UpdateBean updateBean);

    void AllData(AllBean allBean);



}
