package com.finance.zg6.mianInsideFragment.investment.allfiannce;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.finance.framework.BaseFragment;
import com.finance.net.bean.ProductBean;
import com.finance.zg.R;
import com.finance.zg6.mianInsideFragment.investment.allfiannce.adapter.AllFinanceAdapter;
import com.finance.zg6.view.SlideRecyclerView;


public class AllFinanceFragment extends BaseFragment<AllFinancePresenter> implements IAllFinanceView {


    private RecyclerView allFinanceRv;
    private AllFinanceAdapter allFinanceAdapter;
    private TextView allTxt;
    private ImageView allImgAnimation;
    private SlideRecyclerView slideRecyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_all_financingk;
    }

    @Override
    protected void initPresenter() {
        httpPresenter = new AllFinancePresenter(this);
    }

    @Override
    protected void initData() {
        httpPresenter.getProductData();
        slideRecyclerView = new SlideRecyclerView(getContext());
    }

    @Override
    protected void initView() {

        allFinanceRv = (RecyclerView) mView.findViewById(R.id.all_finance_rv);
        allTxt = (TextView) mView.findViewById(R.id.all_txt);
    }

    @Override
    public void onProductData(ProductBean productBean) {
        LogUtils.json(productBean);
        loadingPage.showSuccessView();

        allFinanceAdapter = new AllFinanceAdapter(productBean.getResult());
        allFinanceRv.setLayoutManager(new LinearLayoutManager(getContext()));
        allFinanceRv.setAdapter(allFinanceAdapter);

    }

    @Override
    public void showLoading() {
        loadingPage.showTransparentLoadingView();
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightImgClick() {

    }

    @Override
    public void onRightTvClick() {

    }
}