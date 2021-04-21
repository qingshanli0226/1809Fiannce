package com.example.myapplication.welcome;

import com.example.framework.IBaseView;
import com.example.model.HomeBean;
import com.example.model.ProductBean;
import com.example.model.VersionBean;

public interface IWelcomeView extends IBaseView {
    void onHomeData(HomeBean homeBean);
    void onVersionData(VersionBean versionBean);
    void onProductDara(ProductBean productBean);
}
