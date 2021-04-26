package com.example.myfinancial.fragment.inves.fragment;

import android.view.MotionEvent;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.framework.BaseFragment;
import com.example.myfinancial.R;
import com.example.myfinancial.fragment.inves.invesmvp.InVesPresenter;
import com.example.myfinancial.fragment.inves.invesmvp.IVesView;
import com.example.net.bean.AllMoneyBean;

import java.util.ArrayList;
import java.util.List;

public class AllFragment extends BaseFragment<InVesPresenter> implements IVesView {
    private RecyclerView allrec;

    private AllAdapter allAdapter;
    private List<AllMoneyBean.ResultBean> alllist = new ArrayList<>();

    @Override
    public int getbandLayout() {
        return R.layout.fragment_all;
    }

    @Override
    public void initData() {
        mPresenter.getAllMoney();//获取数据
    }


    @Override
    public void initView() {
        allrec = (RecyclerView) findViewById(R.id.rec);
        allAdapter = new AllAdapter(alllist);
        allrec.setLayoutManager(new LinearLayoutManager(getActivity()));
        allrec.setAdapter(allAdapter);
    }

    @Override
    public void showLoading() {
        loadingPage.showLoadingView();
    }

    @Override
    public void hideLoading() {
        loadingPage.showSuccessView();
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void initPresenter() {
        mPresenter = new InVesPresenter(this);
    }


    @Override
    public void initAllMoney(AllMoneyBean allMoneyBean) {
        alllist.addAll(allMoneyBean.getResult());
        allAdapter.notifyDataSetChanged();
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




    @Override
    public void onDestroy() {
        super.onDestroy();
        if (loadingPage!=null){
            loadingPage.clearAnimation();
        }
        if (mPresenter!=null){
            mPresenter.destroy();
        }
    }


}