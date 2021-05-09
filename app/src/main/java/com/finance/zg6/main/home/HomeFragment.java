package com.finance.zg6.main.home;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.Glide;
import com.finance.framework.BaseFragment;
import com.finance.framework.manager.CacheManager;
import com.finance.framework.manager.CacheUserManager;
import com.finance.net.bean.HomeBean;
import com.finance.net.bean.LoginBean;
import com.finance.net.bean.UserBean;
import com.finance.zg.R;
import com.finance.zg6.view.ProgressView;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.List;


public class HomeFragment extends BaseFragment implements CacheUserManager.ILoginChange{

    private Banner banner;
    private TextView homeTitle;
    private ProgressView progressView;
    private TextView annualInterestRate;
    private TextView homeText;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {
        HomeBean homeBean = CacheManager.getInstance().getHomeBean();

        List<HomeBean.ResultBean.ImageArrBean> imageArr = homeBean.getResult().getImageArr();
        LogUtils.json(imageArr);

        banner.setImages(imageArr);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                HomeBean.ResultBean.ImageArrBean img = (HomeBean.ResultBean.ImageArrBean) path;
                String imaurl = img.getIMAURL();
                Glide.with(getContext()).load(imaurl + "").into(imageView);
            }
        });
        banner.start();

        homeTitle.setText("" + homeBean.getResult().getProInfo().getName());
        annualInterestRate.setText(getString(R.string.homeFragment_YearRate)+homeBean.getResult().getProInfo().getYearRate()+getString(R.string.homeFragment_YearRate_Percent_sign));
        progressView.startProgress(Integer.valueOf(homeBean.getResult().getProInfo().getProgress()),true);

        CacheUserManager.getInstance().registerLogin(this);

        LoginBean loginBean = CacheUserManager.getInstance().getLoginBean();
        if (loginBean == null){
            Toast.makeText(getContext(), "未登录", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getContext(), "登录", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void initView() {
        banner = (Banner) mView.findViewById(R.id.banner);
        homeTitle = (TextView) mView.findViewById(R.id.home_title);
        progressView = (ProgressView) mView.findViewById(R.id.progress_view);
        annualInterestRate = (TextView) mView.findViewById(R.id.annual_interest_rate);
        homeText = (TextView) mView.findViewById(R.id.home_text);
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

    @Override
    public void onLoginChange(LoginBean loginBean) {
        Toast.makeText(getContext(), ""+loginBean, Toast.LENGTH_SHORT).show();
    }
}