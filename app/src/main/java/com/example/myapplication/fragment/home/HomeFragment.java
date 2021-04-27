package com.example.myapplication.fragment.home;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.framework.BaseFragment;
import com.example.framework.manager.CacheManager;
import com.example.framework.view.ToolBar;
import com.example.model.HomeBean;
import com.example.model.LoginBean;
import com.example.model.ProductBean;
import com.example.model.RegisterBean;
import com.example.model.VersionBean;
import com.example.myapplication.R;
import com.example.demo.Demo;
import com.example.framework.view.ProgressView;
import com.example.myapplication.welcome.IWelcomeView;
import com.example.myapplication.welcome.WelcomePresenter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends BaseFragment<WelcomePresenter> implements IWelcomeView {
    private Banner banner;
    private ProgressView progressBar;
    private List<String> bannertitles = new ArrayList<>();
    private ToolBar toolBar;

    @Override
    protected void initData() {

        bannertitles.add("分享砍学费");
        bannertitles.add("人脉总动员");
        bannertitles.add("想不到你是这样的app");
        bannertitles.add("购物节，爱不单行");

        HomeBean homeBean = CacheManager.getInstance().getHomeBean();
        banner.setImages(homeBean.getResult().getImageArr());
        banner.setBannerAnimation(Transformer.Tablet);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        banner.setBannerTitles(bannertitles);

        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                HomeBean.ResultBean.ImageArrBean s = (HomeBean.ResultBean.ImageArrBean) path;
                String imaurl = s.getIMAURL();
                Glide.with(getActivity()).load(imaurl).into(imageView);
            }
        }).start();

        progressBar.startProgressDraw(Demo.SCHEDULE_PROGRESS,true);

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {
        toolBar = mView.findViewById(R.id.toolbar);
        banner = (Banner) mView.findViewById(R.id.banner);
        progressBar = (ProgressView) mView.findViewById(R.id.progress_bar);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void onHomeData(HomeBean homeBean) {

    }

    @Override
    public void onVersionData(VersionBean versionBean) {

    }

    @Override
    public void onProductDara(ProductBean productBean) {

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