package com.example.gitproject.invest.manageFinances;

import com.example.framework.IBaseView;
import com.example.net.bean.ProductBean;

public interface IManageFinancesView extends IBaseView {

    public void onProduct(ProductBean productBean);
}
