package com.fiannce.myapplication.fragment.home;


import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


import com.bumptech.glide.Glide;
import com.fiannce.framework.BaseFragment;
import com.fiannce.framework.manager.CacheManager;
import com.fiannce.myapplication.R;
import com.fiannce.net.mode.HomeBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;


import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {


    private customView customView;
    private Banner homeBanner;
    private TextView homeTv1;


    @Override
    protected void initData() {

    }

    @Override
    protected void initPresenter() {

    }

    protected void initView() {
        customView = (customView) mView.findViewById(R.id.custom_view);
        homeBanner = (Banner) mView.findViewById(R.id.home_banner);
        homeTv1 = (TextView) mView.findViewById(R.id.home_tv);
        customView.startCurrent();

        HomeBean homeBean = CacheManager.getInstance().getHomeBean();
        List<HomeBean.ResultBean.ImageArrBean> imageArr = homeBean.getResult().getImageArr();

        homeBanner.setImages(imageArr);
        homeBanner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                HomeBean.ResultBean.ImageArrBean imageArrBean = (HomeBean.ResultBean.ImageArrBean) path;
                String imaurl = imageArrBean.getIMAURL();
                Glide.with(getActivity()).load(imaurl).into(imageView);
            }
        });
        homeBanner.start();

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
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
