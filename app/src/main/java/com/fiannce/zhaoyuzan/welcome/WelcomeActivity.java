package com.fiannce.zhaoyuzan.welcome;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.alibaba.android.arouter.launcher.ARouter;
import com.fiannce.framework.BaseActivity;
import com.fiannce.framework.manager.CacheManager;
import com.fiannce.net.mode.HomeBean;
import com.fiannce.net.mode.VersionBean;
import com.fiannce.zhaoyuzan.R;

public class WelcomeActivity extends BaseActivity<WelcomePresenter> implements IWelcomeView {

    private TextView coundDownTv;
    private int countDown = 3;
    private final int ONE_TASK_FIISH = 0;
    private final int ALL_TASK_FIISH = 1;
    private final int DELAY_INDEX = 2;
    private final int DELAY = 1*1000;
    private boolean homeFinsh = false;
    private boolean versionFinsh = false;
    private boolean advertistFinsh = false;


    @Override
    protected void initPresenter() {
        httpPresenter = new WelcomePresenter(this);
    }

    @Override
    protected void initData() {
        httpPresenter.getHomeData();
        httpPresenter.getVersionData();
    }

    @Override
    protected void initView() {
        coundDownTv = findViewById(R.id.countDownTv);

        handler.sendEmptyMessageDelayed(DELAY_INDEX,DELAY);
        coundDownTv.setText(countDown + "秒");

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    public void onHomeData(HomeBean homeBean) {
        CacheManager.getInstance().setHomeBean(homeBean);
        homeFinsh = true;
        handler.sendEmptyMessage(ONE_TASK_FIISH);
    }

    @Override
    public void onVersionData(VersionBean versionBean) {
        Toast.makeText(this, "获取到版本信息：" + versionBean.getResult().getVersion(), Toast.LENGTH_SHORT).show();
        versionFinsh = true;
        handler.sendEmptyMessage(ONE_TASK_FIISH);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this,"请求出现错误："+ msg,Toast.LENGTH_SHORT);
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case DELAY_INDEX:
                    countDown--;
                    if (countDown > 0){
                        coundDownTv.setText(countDown + "秒");
                        handler.sendEmptyMessageDelayed(DELAY_INDEX,DELAY);
                    }else {
                        coundDownTv.setText(countDown + "秒");
                        advertistFinsh = true;
                        handler.sendEmptyMessage(ONE_TASK_FIISH);
                    }
                    break;
                case ONE_TASK_FIISH:
                    if (homeFinsh && versionFinsh && advertistFinsh){
                        handler.sendEmptyMessage(ALL_TASK_FIISH);
                    }
                    break;
                case ALL_TASK_FIISH:
                    AlertDialog.Builder builder = new AlertDialog.Builder(WelcomeActivity.this);

                    builder.setTitle("更新");
                    builder.setMessage("是否更新?");
                    builder.setPositiveButton ("确定", new DialogInterface.OnClickListener () {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ARouter.getInstance().build("/main/MainActivity").navigation();
                            finish();
                        }
                    });

                    builder.show();


                    break;
            }
        }
    };

    @Override
    public void destroy() {
        super.destroy();
        handler.removeCallbacksAndMessages(null);
    }
}
