package com.fiance.chengtianle.Fragment;

import android.os.PowerManager;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
        Toast.makeText(getContext(), lcBean.toString(), Toast.LENGTH_SHORT).show();
     list.addAll(lcBean.getResult());
     loadingPage.showSuccessView();
     myAdapter.notifyDataSetChanged();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_a;
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

    }

    @Override
    public void showLoading() {
        Toast.makeText(getActivity(), "1121", Toast.LENGTH_SHORT).show();
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