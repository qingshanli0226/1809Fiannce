package com.fiannce.myapplication.fragment.investment.money.allmoeny;


import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fiannce.framework.BaseFragment;
import com.fiannce.myapplication.R;
import com.fiannce.myapplication.adapter.AllmoneyAdapter;
import com.fiannce.net.mode.AllMoneyBean;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllmoneyFragment extends BaseFragment<AllmoneyPresenter> implements IAllmoneyView {


    private RecyclerView rv;
    private TextView light;
    List<AllMoneyBean.ResultBean> result = new ArrayList<>();
    AllmoneyAdapter allmoneyAdapter;
    AllmoneyPresenter allmoneyPresenter;

    public AllmoneyFragment() {
        // Required empty public constructor
    }


    @Override
    protected void initPresenter() {
        httpPresenter = new AllmoneyPresenter(this);

    }

    @Override
    public void onAllmoney(AllMoneyBean allMoneyBean) {
        result.addAll(allMoneyBean.getResult());
        allmoneyAdapter.notifyDataSetChanged();
        loadingPage.showSuccessView();
    }

    @Override
    protected void initView() {
        rv = (RecyclerView) mView.findViewById(R.id.rv);
        light = (TextView) mView.findViewById(R.id.light);
        allmoneyAdapter = new AllmoneyAdapter(R.layout.recyclerview_allmoney, result);
        rv.setAdapter(allmoneyAdapter);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    protected void initData() {
        light.setSelected(true);
        httpPresenter.getAllmoney();

        allmoneyAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.rv_text_delete:
                        result.remove(position);
                        allmoneyAdapter.notifyItemRemoved(position);
                        break;
                }
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_allmoney;
    }

    @Override
    public void showLoading() {
        loadingPage.showTransparentLoadingView();
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String error) {
        loadingPage.showError(error);
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
