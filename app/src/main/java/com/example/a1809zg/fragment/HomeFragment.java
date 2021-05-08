package com.example.a1809zg.fragment;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.a1809zg.myview.MyView;
import com.example.a1809zg.R;
import com.example.a1809zg.welcome.HomePresenter;
import com.example.a1809zg.welcome.IHomeView;
import com.example.frame.BaseFragment;
import com.example.frame.CacheMore;
import com.example.net.bean.HomeBean;
import com.example.net.bean.LoginBean;
import com.example.net.bean.UpdataBean;
import com.scwang.smartrefresh.layout.BuildConfig;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.List;


public class HomeFragment extends BaseFragment<HomePresenter> implements IHomeView {

    private Banner ban;
    private MyView view;

    @Override
    public void onHomeData(HomeBean homeBean) {

    }

    @Override
    public void onUpdaData(UpdataBean updataBean) {

    }

    @Override
    protected int Findlayout() {
        return R.layout.fragment_blank;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    public void initView() {


        ban = findViewById(R.id.ban);
        view = findViewById(R.id.view);
    }

    @Override
    protected void initData() {
        HomeBean homeBean = CacheMore.getInstance().getHomeBean();
        HomeBean.ResultBean result = homeBean.getResult();
        if (BuildConfig.DEBUG) Log.d("AllFragment", "homeBean:" + homeBean);
        List<HomeBean.ResultBean.ImageArrBean> imageArr = result.getImageArr();
        ban.setImages(imageArr);
        ban.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                HomeBean.ResultBean.ImageArrBean a = (HomeBean.ResultBean.ImageArrBean) path;
                String imaurl = a.getIMAURL();
                Glide.with(context).load(imaurl).into(imageView);
            }
        }).start();

        view.saprogress(50,true);
    }

    @Override
    public void showLoaing() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void onLoginChange(LoginBean loginBean) {

    }

    @Override
    public void onConnect() {
        Toast.makeText(getActivity(), "有网络了", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDisConnect() {
        Toast.makeText(getActivity(), "网络错误", Toast.LENGTH_SHORT).show();
    }
}