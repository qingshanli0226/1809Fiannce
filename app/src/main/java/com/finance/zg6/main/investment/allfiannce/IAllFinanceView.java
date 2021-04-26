package com.finance.zg6.main.investment.allfiannce;

import com.finance.framework.IBaseView;
import com.finance.net.bean.ProductBean;

public interface IAllFinanceView extends IBaseView {
    void onProductData(ProductBean productBean);
}
