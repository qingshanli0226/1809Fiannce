package com.fiance.chengtianle.Fragment;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.fiance.chengtianle.Adapter.MyAdapter;
import com.fiance.chengtianle.LiCai.ILiCaiView;
import com.fiance.chengtianle.LiCai.LiCaiPresenter;
import com.fiance.chengtianle.R;
import com.fiance.framework.BaseFragment;
import com.fiance.net.mode.LcBean;

import java.util.ArrayList;


public class AllFragment extends BaseFragment<LiCaiPresenter> implements ILiCaiView {


    private ArrayList<LcBean.ResultBean> list = new ArrayList<>();
    private RecyclerView rv;
    private MyAdapter myAdapter;
    private LiCaiPresenter liCaiPresenter;

    @Override
    public void onLiCaiData(LcBean lcBean) {
        list.addAll(lcBean.getResult());
        myAdapter.notifyDataSetChanged();
        loadingPage.showSuccessView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_all;
    }

    @Override
    protected void initPresenter() {
        liCaiPresenter = new LiCaiPresenter(this);

    }

    @Override
    protected void initData() {
        showLoading();
        liCaiPresenter.getLiCaiData1();
    }

    @Override
    protected void initView() {
        rv = mbaseView.findViewById(R.id.rv);
        myAdapter = new MyAdapter(R.layout.lc_layout, list);
        rv.setAdapter(myAdapter);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        myAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                switch (view.getId()) {
                    case R.id.btn:
                        list.remove(position);
                        myAdapter.notifyItemRemoved(position);
                        break;
                }

            }
        });

    }

    @Override
    public void showLoading() {
        loadingPage.showLoadingView();
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String error) {
        loadingPage.showTransparentLoadingView();
    }
}