package com.fiance.chengtianle.Fragment;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fiance.chengtianle.R;
import com.fiance.framework.BaseFragment;
import com.fiance.framework.CacheManager;
import com.fiance.net.mode.HomeBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;


public class oneFragment extends BaseFragment {


    private Banner banner;
    private ArrayList<HomeBean.ResultBean.ImageArrBean> list =new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_one;
    }

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
                HomeBean.ResultBean.ImageArrBean bean= (HomeBean.ResultBean.ImageArrBean) path;
                String imaurl = bean.getIMAURL();
                Glide.with(context).load(imaurl).into(imageView);

            }
        }).start();
    }

    @Override
    protected void initView() {
        banner = mbaseView.findViewById(R.id.banner);
    }
}