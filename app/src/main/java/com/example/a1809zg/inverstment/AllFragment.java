package com.example.a1809zg.inverstment;

import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.example.a1809zg.R;
import com.example.frame.BaseFragment;
import com.example.net.bean.LoginBean;
import com.example.net.bean.ProductBean;

import java.util.ArrayList;
import java.util.List;

public class AllFragment extends BaseFragment<AllPresenter> implements IFragView {
    protected AllPresenter allPresenter;

    private AllAdapter allAdapter;
    private List<ProductBean.ResultBean> list = new ArrayList<>();
    private RecyclerView rv;


    @Override
    public void onProdactDada(ProductBean productBean) {
        Toast.makeText(getContext(), "111productBean.getResult():" + productBean.getResult().toString(), Toast.LENGTH_SHORT).show();
        List<ProductBean.ResultBean> result = productBean.getResult();
        list.addAll(result);
        allAdapter.notifyDataSetChanged();


    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {
        allAdapter = new AllAdapter(list);
        rv = findViewById(R.id.rv);

       rv.setAdapter(allAdapter);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

//        allAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
//            @Override
//            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
//                list.remove(position);
//                allAdapter.notifyItemRemoved(position);
//            }
//        });

    }

    @Override
    protected int Findlayout() {
        return R.layout.fragment_blank5;
    }

    @Override
    protected void initData() {
        allPresenter = new AllPresenter(this);
        allPresenter.ProDuctData();
    }

    @Override
    public void showLoaing() {
        loadingPage.showLoadingView();
    }

    @Override
    public void hideLoading() {
        loadingPage.showSuccessView();
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void onLoginChange(LoginBean loginBean) {

    }

    @Override
    public void onConnect() {

    }

    @Override
    public void onDisConnect() {

    }
}