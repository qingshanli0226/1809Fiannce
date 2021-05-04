package com.example.fiannce.fragment.homefragment;

import android.content.Context;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.Glide;
import com.example.fiannce.R;
import com.example.net.ToolBarView;
import com.example.framework.BaseFragment;
import com.example.framework.manager.CacheManager;
import com.example.net.mode.HomeBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.List;

public class HomeFragment extends BaseFragment {

    private Banner banner;
    private TextView name;
    private CustomView_HomeFragment customview;
    private TextView expect;
    private Button buy;
    private ToolBarView tobView;

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
                Glide.with(getContext()).load(imaurl).into(imageView);
            }
        });
        banner.start();

        customview.SealedProgress(80,true);

        tobView.ImgCallBackListener(new ToolBarView.ImgCallBack() {
            @Override
            public void OnLeftImg() {
                Toast.makeText(getContext(), "图片", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void OnRightImg() {
                Toast.makeText(getContext(), "图片", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void initView() {
        banner = (Banner) mView.findViewById(R.id.banner);
        tobView = (ToolBarView) mView.findViewById(R.id.tob);
        name = (TextView) mView.findViewById(R.id.name);
        customview = (CustomView_HomeFragment) mView.findViewById(R.id.customview);
        expect = (TextView) mView.findViewById(R.id.expect);
        buy = (Button) mView.findViewById(R.id.buy);
    }
}