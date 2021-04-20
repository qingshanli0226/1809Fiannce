package com.example.a1809zg.welcome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.a1809zg.R;
import com.example.frame.BaseActivity;
import com.example.net.bean.HomeBean;
import com.example.net.bean.UpdataBean;

public class MainActivity extends BaseActivity<Ipresenter> implements Iview {
private Ipresenter ipresenter;
    @Override
    public void onHomeData(HomeBean homeBean) {

    }

    @Override
    public void onUpdaData(UpdataBean updataBean) {
        Toast.makeText(this, "获取到版本信息："+ updataBean.getResult().getVersion(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main2;
    }

    @Override
    protected void initPresenter() {
         ipresenter = new Ipresenter(this);
    }

    @Override
    protected void initData() {
         ipresenter.getHomeData();
         ipresenter.getVersionData();
    }

    @Override
    protected void initView() {

    }

    @Override
    public void showLoaing() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String msg) {
          Toast.makeText(this, "错误"+msg, Toast.LENGTH_SHORT).show()
    }
}