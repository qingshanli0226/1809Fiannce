package com.example.myfinancial.home;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.framework.BaseFragment;
import com.example.framework.CacheLoadMore;
import com.example.framework.mannager.FiannceArote;
import com.example.framework.myview.PregressMyView;
import com.example.framework.myview.ToolBar;
import com.example.myfinancial.R;
import com.example.net.bean.HomeBean;
import com.example.pay.PayModel;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.List;

public class HomeFragment extends BaseFragment {
    private HomeBean homeBean;
    private Banner ban;
    private PregressMyView proMyView;
    private ToolBar toolBar;
    private Button satrtpay;

    @Override
    public int getbandLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void initData() {
        homeBean = CacheLoadMore.getInstance().getHomeBean();
        initlbt();
        proMyView.getProgressNum(Integer.parseInt(homeBean.getResult().getProInfo().getProgress()), true);
        showLoading();

        //点击跳转
        satrtpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                PayModel.init();
                FiannceArote.getInstance().getPayInterface().openPayActivity(getActivity(),null);
            }
        });
    }

    @Override
    public void initView() {
        ban = (Banner) findViewById(R.id.ban);
        proMyView = (PregressMyView) findViewById(R.id.proMyView);
        toolBar = (ToolBar) findViewById(R.id.toolBar);
        toolBar.setToolbarListener(this);
        satrtpay = (Button) findViewById(R.id.satrtpay);
    }

    @Override
    public void showLoading() {
        loadingPage.showLoadingView();
        hideLoading();
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

    }

    //轮播图
    private void initlbt() {
        List<HomeBean.ResultBean.ImageArrBean> imageArr = homeBean.getResult().getImageArr();
        ban.setImages(imageArr);
        ban.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                HomeBean.ResultBean.ImageArrBean aa = (HomeBean.ResultBean.ImageArrBean) path;
                String imaurl = aa.getIMAURL();
                Glide.with(context).load(imaurl).into(imageView);
            }
        }).start();
    }

    @Override
    public void onLeftClick() {
        Toast.makeText(getActivity(), "111", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRightImgClick() {
        Toast.makeText(getActivity(), "222", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRightTvClick() {
        Toast.makeText(getActivity(), "333", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.destroy();
        }
        if (ban != null) {
            ban.clearAnimation();
        }
        if (loadingPage != null) {
            loadingPage.clearAnimation();
        }
        if (proMyView != null) {
            proMyView.destroy();
        }
    }

    @Override
    public void onLoginChanged(boolean isLogin) {

    }
}