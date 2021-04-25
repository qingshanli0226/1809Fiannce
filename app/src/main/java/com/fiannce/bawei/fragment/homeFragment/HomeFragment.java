package com.fiannce.bawei.fragment.homeFragment;


import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.fiannce.framework.BaseFragment;
import com.fiannce.framework.manager.CacheManager;

import com.fiannce.framework.view.ProgressView;
import com.fiannce.net.mode.HomeBean;
import com.fiannce.zhaoyuzan.R;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {

    private Banner banner;
    private TextView textView;
    private TextView yuqiText;
    private List<HomeBean.ResultBean.ImageArrBean> list = new ArrayList<>();
    private List<HomeBean.ResultBean.ProInfoBean> proInfoBeanList = new ArrayList<>();
    private ProgressView progressView;

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {

        HomeBean homeBean = CacheManager.getInstance().getHomeBean();
        List<HomeBean.ResultBean.ImageArrBean> imageArr = homeBean.getResult().getImageArr();

        list.addAll(imageArr);

        banner.setImages(list);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                HomeBean.ResultBean.ImageArrBean bean = (HomeBean.ResultBean.ImageArrBean) path;
                String imaurl = bean.getIMAURL();
                Glide.with(HomeFragment.this).load(imaurl).transform(new CenterCrop()).into(imageView);
            }
        });
        banner.start();

        HomeBean.ResultBean.ProInfoBean proInfo = homeBean.getResult().getProInfo();
        String name = proInfo.getName();
        textView.setText(name);
        String yearRate = proInfo.getYearRate();
        yuqiText.setText(yearRate);

        progressView.saledProgress(90,true);
    }

    @Override
    protected void initView() {
        banner = mBaseView.findViewById(R.id.banner);
        progressView = mBaseView.findViewById(R.id.progress);
        textView = mBaseView.findViewById(R.id.guigu);
        yuqiText = mBaseView.findViewById(R.id.yuqi);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        progressView.destry();
    }
}
