package com.example.a1809zg.infragment;

import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a1809zg.R;
import com.example.frame.Basefragment;
import com.example.net.bean.ProductBean;

import java.util.ArrayList;
import java.util.List;

public class MoneyFragment extends Basefragment<Fragpresenter> implements FragView {
    protected Fragpresenter fragpresenter;
    private RecyclerView rv;
    private Fragadpter fragadpter;
    private List<ProductBean.ResultBean> list=new ArrayList<>();

    @Override
    public void onProdactDada(ProductBean productBean) {
            Toast.makeText(getContext(), "111productBean.getResult():" + productBean.getResult().toString(), Toast.LENGTH_SHORT).show();
        List<ProductBean.ResultBean> result = productBean.getResult();
        list.addAll(result);
        fragadpter.notifyDataSetChanged();
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {
        fragadpter=new Fragadpter(list);
        rv = findViewById(R.id.rv);
        rv.setAdapter(fragadpter);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    @Override
    protected int Findlayout() {
        return R.layout.fragment_blank5;
    }

    @Override
    protected void initData() {
        fragpresenter = new Fragpresenter(this);
        fragpresenter.ProDuctData();
    }

    @Override
    public void showLoaing() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String msg) {

    }
}