package com.example.myfinancial.welcome;

import androidx.annotation.NonNull;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.framework.BaseActivity;
import com.example.framework.CacheLoadMore;
import com.example.myfinancial.R;
import com.example.net.bean.HomeBean;
import com.example.net.bean.VersionBean;

public class WelcomeActivity extends BaseActivity<WelcomePresenter> implements IWelComeView {
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
    public int getbandLayout() {
        return R.layout.activity_welcome2;
    }

    @Override
    public void initPresenter() {
        mPresenter = new WelcomePresenter(this);
    }

    @Override
    public void initData() {
        mPresenter.getVersion();
        mPresenter.getHome();
    }

    @Override
    public void initView() {
        //向上顶  全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        countdowntv = (TextView) findViewById(R.id.countDownTv);
        handler.sendEmptyMessageDelayed(DELAT_INDEX, DELAY);//发送倒计时  handler
        countdowntv.setText(drawtimetv + "");//修改
    }


    @Override
    public void initWelcome(HomeBean homeBean) {
        Log.d("WelcomeActivity", homeBean.toString());
        geth = true;
        handler.sendEmptyMessage(ONE_TASK_FINISH);
        CacheLoadMore.getInstance().setHomeBean(homeBean);
    }

    @Override
    public void initVersion(VersionBean versionBean) {
        Log.d("WelcomeActivity", versionBean.toString());
        getv = true;
        handler.sendEmptyMessage(ONE_TASK_FINISH);
    }

    @Override
    public void showLoading() {
        loadingPage.showLoadingView();
    }

    @Override
    public void hideLoading() {
        loadingPage.showSuccessView();
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
                    finish();
                    break;
            }
        }
    };

    //加载页面期间不可返回
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        if (mPresenter!=null){
            mPresenter.destroy();
        }
    }
}