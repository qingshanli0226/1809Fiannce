package com.example.myapplication.welcome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.framework.BaseActivity;
import com.example.framework.manager.CacheManager;
import com.example.model.HomeBean;
import com.example.model.ProductBean;
import com.example.model.VersionBean;
import com.example.myapplication.R;
import com.example.myapplication.apk.APKVersionCodeUtils;
import com.example.demo.Demo;

public class WelcomeActivity extends BaseActivity<WelcomePresenter> implements IWelcomeView {

    private TextView contenNum;
    private ProgressBar bar;
    private TextView homedata;
    private final int ONE_TASK_FINISH = 0;
    private final int All_TASK_FINISH = 1;
    private final int DELAY_INDEX = 2;
    private final int DELAY = 1000;
    private boolean home_finish = false;
    private boolean version_finish = false;
    private boolean advertistFinsh  = false;
    private int FINISH_SESSION = 0;
    private int session = 3;
    private VersionBean versionBeans;

    @Override
    protected void initData() {
        httpPresenter.getHomeData();
        httpPresenter.getVersionData();
        handler.sendEmptyMessageDelayed(DELAY_INDEX,DELAY);
    }

    @Override
    protected void initPresenter() {
        httpPresenter = new WelcomePresenter(this);
    }

    @Override
    protected void initView() {
        contenNum = (TextView) findViewById(R.id.conten_num);
//        homedata = (TextView) findViewById(R.id.homedata);
        contenNum.setText(session+"秒");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    public void onHomeData(HomeBean homeBean) {
        CacheManager.getInstance().setHomeBean(homeBean);
//        homedata.setText(""+homeBean.toString());
        home_finish = true;
        handler.sendEmptyMessage(ONE_TASK_FINISH);
    }

    @Override
    public void onVersionData(VersionBean versionBean) {
        Toast.makeText(this, "获取到版本信息:"+versionBean.getResult().getVersion(), Toast.LENGTH_SHORT).show();
        version_finish = true;
        versionBeans = versionBean;
        handler.sendEmptyMessage(ONE_TASK_FINISH);
    }

    @Override
    public void onProductDara(ProductBean productBean) {

    }

    @Override
    public void showLoading() {
//        bar.setVisibility(View.VISIBLE);
//        loadingPage.showLoadingView();
    }

    @Override
    public void hideLoading() {
//        bar.setVisibility(View.GONE);
//        loadingPage.showTransparentLoadingView();
    }

    @Override
    public void showError(String error) {
//        Toast.makeText(this, "请求出现错误:"+error, Toast.LENGTH_SHORT).show();
        loadingPage.showError(error);
    }



    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case DELAY_INDEX:
                    session--;
                    if (session > FINISH_SESSION){
                        contenNum.setText(session+"秒");
                        handler.sendEmptyMessageDelayed(DELAY_INDEX,DELAY);
                    }else {
                        contenNum.setText(session+"秒");
                        advertistFinsh=true;
                        handler.sendEmptyMessage(ONE_TASK_FINISH);
                    }
                    break;
                case ONE_TASK_FINISH:
                    if (home_finish==true && version_finish==true && advertistFinsh==true){
                        handler.sendEmptyMessage(All_TASK_FINISH);
                    }
                    break;
                case All_TASK_FINISH:
                    Toast.makeText(WelcomeActivity.this, "所有任务完成", Toast.LENGTH_SHORT).show();

                    int code = versionBeans.getCode();
                    if (code == Demo.VERSION_CODE){
                        VersionBean.ResultBean result = versionBeans.getResult();
                        int versionCode = result.getVersionCode();
                        int versionCode1 = APKVersionCodeUtils.getVersionCode(WelcomeActivity.this);
                        if (versionCode1<versionCode){

                            AlertDialog.Builder builder = new AlertDialog.Builder(WelcomeActivity.this);
                            builder.setIcon(R.drawable.ic_launcher_background);
                            builder.setMessage(Demo.HINT_DIALOG_MESSAGE);
                            builder.setTitle(Demo.HINT_DIALOG_TITLE);
                            builder.setCancelable(true);

                            builder.setPositiveButton(Demo.HINT_DIALOG_CONFIRM, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();

                                    ProgressDialog progressDialog = new ProgressDialog(WelcomeActivity.this);
                                    progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                                    progressDialog.setTitle(Demo.CONFIRM_DIALOG_TITLE);
                                    progressDialog.setMessage(Demo.CONFIRM_DIALOG_MESSAGE);
                                    progressDialog.setIcon(R.drawable.ic_launcher_background);
                                    progressDialog.setProgress(100);
                                    progressDialog.setCancelable(true);
                                    progressDialog.setIndeterminate(true);
                                    progressDialog.show();
                                    progressDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                        @Override
                                        public void onDismiss(DialogInterface dialog) {

                                            Toast.makeText(WelcomeActivity.this, "取消更新", Toast.LENGTH_SHORT).show();

                                            ARouter.getInstance().build(Demo.AROUTE_PATH).navigation();
                                            finish();
                                        }
                                    });

                                }
                            });

                            builder.setNegativeButton(Demo.HINT_DIALOG_CANCLE, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    dialog.dismiss();
                                    ARouter.getInstance().build(Demo.AROUTE_PATH).navigation();
                                    finish();
                                }
                            });

                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();


                        }
                    }else {
                        ARouter.getInstance().build(Demo.AROUTE_PATH).navigation();
                        finish();

                    }
                    break;
            }

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}