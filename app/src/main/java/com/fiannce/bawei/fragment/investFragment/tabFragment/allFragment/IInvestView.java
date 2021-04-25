package com.fiannce.bawei.fragment.investFragment.tabFragment.allFragment;

import com.fiannce.framework.IBaseView;
import com.fiannce.net.mode.InvestBean;

public interface IInvestView extends IBaseView {

    void onProductData(InvestBean investBean);

}
