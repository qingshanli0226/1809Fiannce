package com.fiannce.bawei.welcome;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.alibaba.android.arouter.launcher.ARouter;
import com.fiannce.commond.CommonConstant;
import com.fiannce.commond.SpUtil;
import com.fiannce.framework.BaseActivity;
import com.fiannce.framework.manager.CacheManager;
import com.fiannce.net.mode.HomeBean;
import com.fiannce.net.mode.VersionBean;
import com.fiannce.user.service.AutoService;
import com.fiannce.zhaoyuzan.R;

public class WelcomeActivity extends BaseActivity<WelcomePresenter> implements IWelcomeView {

    private TextView coundDownTv;
    private int countDown = 3;
    private final int ONE_TASK_FIISH = 0;
    private final int ALL_TASK_FIISH = 1;
    private final int DELAY_INDEX = 2;
    private final int DELAY = 1 * 1000;
    private boolean homeFinsh = false;
    private boolean versionFinsh = false;
    private boolean advertistFinsh = false;
    private VersionBean versionBean;


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

        handler.sendEmptyMessageDelayed(DELAY_INDEX, DELAY);
        coundDownTv.setText(countDown + getString(R.string.miao));

        if (!SpUtil.getString(this, CommonConstant.SP_TOKEN).equals("")) {
            Intent intent = new Intent(this, AutoService.class);
            startService(intent);
        }
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
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public void onVersionData(VersionBean versionBean) {
        this.versionBean = versionBean;
        Toast.makeText(this, getString(R.string.banben_info) + versionBean.getResult().getVersion(), Toast.LENGTH_SHORT).show();
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
        loadingPage.showError(msg);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case DELAY_INDEX:
                    countDown--;
                    if (countDown > 0) {
                        coundDownTv.setText(countDown + getString(R.string.miao));
                        handler.sendEmptyMessageDelayed(DELAY_INDEX, DELAY);
                    } else {
                        coundDownTv.setText(countDown + getString(R.string.miao));
                        advertistFinsh = true;
                        handler.sendEmptyMessage(ONE_TASK_FIISH);
                    }
                    break;
                case ONE_TASK_FIISH:
                    if (homeFinsh && versionFinsh && advertistFinsh) {
                        handler.sendEmptyMessage(ALL_TASK_FIISH);
                    }
                    break;
                case ALL_TASK_FIISH:
                    showAlert();
                    break;
            }
        }
    };

    private void showAlert() {
        int code = 0;
        try {
            code = getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (code < versionBean.getResult().getVersionCode()) {

            AlertDialog.Builder builder = new AlertDialog.Builder(WelcomeActivity.this);
            builder.setTitle(getString(R.string.new_banben));
            builder.setMessage(versionBean.getResult().getDesc());
            builder.setNegativeButton(getString(R.string.sure), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    final ProgressDialog progressDialog = new ProgressDialog(WelcomeActivity.this);
                    progressDialog.setTitle("下载中...");
                    progressDialog.setMessage("请等待");
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressDialog.setIndeterminate(false);
                    progressDialog.show();

                    final Thread thread = new Thread() {
                        public void run() {
                            super.run();
                            for (int i = 0; i <= 100; i++) {
                                progressDialog.setProgress(i);
                                try {
                                    Thread.sleep(50);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            progressDialog.dismiss();
                        }
                    };
                    thread.start();      //启动线程

                }
            });
            builder.setPositiveButton(getString(R.string.cancle), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ARouter.getInstance().build(getString(R.string.main_mainActivity)).navigation();
                    finish();
                }
            });
            builder.show();
        } else {
            finish();
            ARouter.getInstance().build(getString(R.string.main_mainActivity)).navigation();
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        handler.removeCallbacksAndMessages(null);
    }
}
