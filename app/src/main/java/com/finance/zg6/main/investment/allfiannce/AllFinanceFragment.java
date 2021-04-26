package com.finance.zg6.main.investment.allfiannce;

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
import com.finance.zg6.main.investment.allfiannce.adapter.AllFinanceAdapter;


public class AllFinanceFragment extends BaseFragment<AllFinancePresenter> implements IAllFinanceView {


    private RecyclerView allFinanceRv;
    private AllFinanceAdapter allFinanceAdapter;
    private TextView allTxt;
    private ImageView allImgAnimation;

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

        allFinanceAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.txt_delete){
                    allFinanceAdapter.getData().remove(position);
                    allFinanceAdapter.notifyItemRemoved(position);
                }
            }
        });
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