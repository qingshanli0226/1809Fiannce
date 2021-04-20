package com.example.myfinancial.welcome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.framework.BaseActivity;
import com.example.myfinancial.CaCheHome;
import com.example.myfinancial.R;
import com.example.net.bean.HomeBean;
import com.example.net.bean.VersionBean;

public class WelcomeActivity extends BaseActivity<WelcomePresenter> implements WelComeView {
    private android.widget.TextView welcometv;
    private android.widget.ProgressBar pro;
    private android.widget.TextView countdowntv;

    private final int ONE_TASK_FINISH = 0;
    private final int ALL_TASK_FINISH = 1;
    private final int DELAT_INDEX = 2;//标识符
    private final int DELAY = 1 * 1000;//1秒

    private boolean geth = false;
    private boolean getv = false;
    private boolean drawtime = false;

    private int drawtimetv = 3;

    @Override
    protected int getbandLayout() {
        return R.layout.activity_welcome2;
    }

    @Override
    protected void initPresenter() {
        mPresenter = new WelcomePresenter(this);
    }

    @Override
    protected void initData() {
        mPresenter.getVersion();
        mPresenter.getHome();
    }

    @Override
    protected void initView() {
        welcometv = (TextView) findViewById(R.id.welcometv);
        pro = (ProgressBar) findViewById(R.id.pro);
        countdowntv = (TextView) findViewById(R.id.countdowntv);

        handler.sendEmptyMessageDelayed(DELAT_INDEX, DELAY);//发送倒计时  handler
        countdowntv.setText(drawtimetv + "");//修改
    }


    @Override
    public void initWelcome(HomeBean homeBean) {
        Log.d("WelcomeActivity", homeBean.toString());
        geth = true;
        handler.sendEmptyMessage(ONE_TASK_FINISH);
        CaCheHome.getHomeBean(homeBean);
    }

    @Override
    public void initVersion(VersionBean versionBean) {
        Log.d("WelcomeActivity", versionBean.toString());
        welcometv.setText(versionBean.toString());
        getv = true;
        handler.sendEmptyMessage(ONE_TASK_FINISH);
    }

    @Override
    public void showLoading() {
        pro.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        pro.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case DELAT_INDEX:
                    drawtimetv--;
                    if (drawtimetv > 0) {
                        countdowntv.setText(drawtimetv + "");
                        handler.sendEmptyMessageDelayed(DELAT_INDEX, DELAY);
                    } else {
                        countdowntv.setText(drawtimetv + "");
                        handler.sendEmptyMessage(ONE_TASK_FINISH);
                        drawtime = true;
                    }
                    break;
                case ONE_TASK_FINISH:
                    if (geth && getv && drawtime) {
                        handler.sendEmptyMessage(ALL_TASK_FINISH);
                    }
                    break;
                case ALL_TASK_FINISH:
                    Toast.makeText(WelcomeActivity.this, "全部运行完成", Toast.LENGTH_SHORT).show();
                    Log.d("WelcomeActivity", "全部运行完成");

                    ARouter.getInstance().build("/main/MainActivity").navigation();

                    break;
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }
}