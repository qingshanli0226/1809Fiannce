package com.example.myfinancial.fragment.invesfragment;

import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.framework.BaseFragment;
import com.example.myfinancial.R;
import com.example.myfinancial.adapter.AllAdapter;
import com.example.myfinancial.fragment.invesfragment.invesmvp.InVesPresenter;
import com.example.myfinancial.fragment.invesfragment.invesmvp.InVesView;
import com.example.net.bean.AllMoneyBean;

import java.util.ArrayList;
import java.util.List;

public class AllFragment extends BaseFragment<InVesPresenter> implements InVesView {
    private RecyclerView rec;

    private AllAdapter allAdapter;
    private List<AllMoneyBean.ResultBean> list = new ArrayList<>();

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
        rec = (RecyclerView) findViewById(R.id.rec);
        allAdapter = new AllAdapter(list);
        rec.setLayoutManager(new LinearLayoutManager(getActivity()));
        rec.setAdapter(allAdapter);

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

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
        list.addAll(allMoneyBean.getResult());
        allAdapter.notifyDataSetChanged();
    }
}