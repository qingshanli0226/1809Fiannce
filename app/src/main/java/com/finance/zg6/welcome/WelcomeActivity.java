package com.finance.zg6.welcome;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.LogUtils;
import com.finance.framework.BaseActivity;
import com.finance.framework.manager.CacheManager;
import com.finance.framework.sp.Constant;
import com.finance.framework.sp.SPUtil;
import com.finance.net.bean.HomeBean;
import com.finance.net.bean.VersionBean;
import com.finance.user.service.AutoService;
import com.finance.zg.R;
import com.finance.zg6.main.MainActivity;

public class WelcomeActivity extends BaseActivity<WelcomePresenter> implements IWelcomeView {

    private TextView countDownTv;
    private int countDownTime = 3;
    private ProgressBar progressBar;

    private final int ONE_TASK_FINISH = 0;
    private final int All_TASK_FINISH = 1;

    private boolean homeFinish = false;
    private boolean versionFinish = false;
    private boolean allFinish = false;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initPresenter() {
        httpPresenter = new WelcomePresenter(this);
    }

    @Override
    protected void initData() {
        httpPresenter.getHomeData();
        httpPresenter.getVersionData();

        countDownTv.setText(getString(R.string.down_time)+countDownTime+getString(R.string.time));
        handler.sendEmptyMessageDelayed(ONE_TASK_FINISH,1000);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    protected void initView() {
        countDownTv = (TextView) findViewById(R.id.countDown);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        //启动自动登录服务
        if(!SPUtil.getString(this, Constant.SP_TOKEN).equals("")){
            Intent intent = new Intent(this, AutoService.class);
            startService(intent);
        }
    }

    @Override
    public void onHomeData(HomeBean homeBean) {
        CacheManager.getInstance().setHomeBean(homeBean);
        LogUtils.json(homeBean);
        homeFinish = true;
        handler.sendEmptyMessage(All_TASK_FINISH);
    }

    @Override
    public void onVersionData(VersionBean versionBean) {
        Toast.makeText(this, getString(R.string.welcomeActivity_latest_version_number_toast) + versionBean.getResult().getVersion(), Toast.LENGTH_SHORT).show();
        versionFinish = true;
        handler.sendEmptyMessage(All_TASK_FINISH);
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private final Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what==ONE_TASK_FINISH){
                countDownTime--;
                if (countDownTime<=0){
                    countDownTv.setText(getString(R.string.down_time)+countDownTime+getString(R.string.time));
                    allFinish = true;
                    handler.sendEmptyMessageDelayed(All_TASK_FINISH,1000);
                }else {
                    countDownTv.setText(getString(R.string.down_time)+countDownTime+getString(R.string.time));
                    handler.sendEmptyMessageDelayed(ONE_TASK_FINISH,1000);
                }
            }
            else if (msg.what == All_TASK_FINISH){
                if (homeFinish&&versionFinish&&allFinish){
                    AlertDialog.Builder builder = new AlertDialog.Builder(WelcomeActivity.this);
                    builder.setTitle(getString(R.string.welcomeActivity_alert_title));
                    builder.setMessage(getString(R.string.welcomeActivity_alert_message));
                    builder.setNegativeButton(getString(R.string.welcomeActivity_alert_button_no), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(WelcomeActivity.this, getString(R.string.welcomeActivity_alert_success_toast), Toast.LENGTH_SHORT).show();
//                    ARouter.getInstance().build("/main/MainActivity").withInt("",1).navigation();
                            Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                    builder.setPositiveButton(getString(R.string.welcomeActivity_alert_button_yes), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    builder.show();
                }
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);

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