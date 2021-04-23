package com.example.gitproject.Invest.manageFinances;

import com.example.framework.IBaseView;
import com.example.net.bean.ProductBean;

public interface IManageFinancesView extends IBaseView {

    public void onProduct(ProductBean productBean);
}
