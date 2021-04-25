package com.example.myfinancial.fragment;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.framework.BaseFragment;
import com.example.framework.CaCheLoadMore;
import com.example.framework.myview.PregressMyView;
import com.example.framework.myview.ToolBar;
import com.example.myfinancial.R;
import com.example.net.bean.HomeBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.List;

public class HomeFragment extends BaseFragment {
    private HomeBean homeBean;
    private Banner ban;
    private PregressMyView promyview;
    private ToolBar toolbar;

    @Override
    public int getbandLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void initData() {
        homeBean = CaCheLoadMore.getInstance().getHomeBean();
        Log.d("MainActivity123", homeBean.toString());
        initlbt();
        promyview.getProgressNum(Integer.parseInt(homeBean.getResult().getProInfo().getProgress()), true);
        showLoading();
    }

    @Override
    public void initView() {
        ban = (Banner) findViewById(R.id.ban);
        promyview = (PregressMyView) findViewById(R.id.promyview);

        toolbar = (ToolBar) findViewById(R.id.toolbar);

        toolbar.setToolbarListener(this);
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
                Log.d("MainActivity", imaurl);
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
}