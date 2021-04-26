package com.example.myapplication.fragment;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.example.framework.BaseFragment;
import com.example.framework.view.SlideRecyclerView;
import com.example.model.HomeBean;
import com.example.model.ProductBean;
import com.example.model.VersionBean;
import com.example.myapplication.R;
import com.example.myapplication.adapter.MyMoneyAdapter;
import com.example.myapplication.demo.Demo;
import com.example.myapplication.welcome.IWelcomeView;
import com.example.myapplication.welcome.WelcomePresenter;

import java.util.ArrayList;
import java.util.List;


public class Money_OneFragment extends BaseFragment<WelcomePresenter> implements IWelcomeView {


    private ImageView img;
    private MyMoneyAdapter adapter;
    private RecyclerView moneyOneRv;
    private TextView light;
    private AnimationDrawable animationDrawable;
    private List<ProductBean.ResultBean> list = new ArrayList<>();

    @Override
    protected void initData() {

        httpPresenter.getProductData();
        light.setSelected(true);
    }


    @Override
    protected void initPresenter() {
        httpPresenter = new WelcomePresenter(this);
    }

    @Override
    protected void initView() {
        img = (ImageView) mView.findViewById(R.id.img);
        moneyOneRv = (RecyclerView) mView.findViewById(R.id.money_one_rv);
        moneyOneRv.setLayoutManager(new LinearLayoutManager(getContext()));
        light = (TextView) mView.findViewById(R.id.light);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_money__one;
    }

    @Override
    public void onHomeData(HomeBean homeBean) {

    }

    @Override
    public void onVersionData(VersionBean versionBean) {

    }

    @Override
    public void onProductDara(ProductBean productBean) {
        List<ProductBean.ResultBean> result = productBean.getResult();
        list.addAll(result);

        adapter = new MyMoneyAdapter(result);
        loadingPage.showSuccessView();

//        adapter.setOnItemChildClickListener(new OnItemChildClickListener() {
//            @Override
//            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
//                switch (view.getId()) {
//                    case R.id.txt_delete:
////                        Toast.makeText(getContext(), ""+position, Toast.LENGTH_SHORT).show();
//                        result.remove(position);
//                        break;
//                }
//                adapter.notifyDataSetChanged();
//            }
//        });

        moneyOneRv.setAdapter(adapter);


    }

    @Override
    public void showLoading() {
        loadingPage.showLoadingView();
    }

    @Override
    public void hideLoading() {
//        loadingPage.showTransparentLoadingView();
    }

    @Override
    public void showError(String error) {
//        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
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