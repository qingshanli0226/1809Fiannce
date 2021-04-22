package com.example.a1809zg.infragment;

import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.a1809zg.R;
import com.example.frame.Basefragment;
import com.example.net.bean.ProductBean;

public class MoneyFragment extends Basefragment<Fragpresenter> implements FragView {
    protected Fragpresenter fragpresenter;
    private RecyclerView rv;

    @Override
    public void onProdactDada(ProductBean productBean) {
            Toast.makeText(getContext(), "productBean.getResult():" + productBean.getResult(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {

        rv = findViewById(R.id.rv);
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