package com.example.gitproject.welcome;

import androidx.annotation.NonNull;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.framework.BaseActivity;
import com.example.framework.manager.CacheManager;
import com.example.gitproject.R;
import com.example.net.bean.HomeBean;
import com.example.net.bean.UpdateBean;


public class WelcomeActivity extends BaseActivity<WelcomePresenter> implements IWelcomeView {



    private final int ONE_TASK_FIISH = 0;
    private ImageView welImg;
    private final int COUNT_TIME = 1;
    private boolean task_one = false;
    private boolean task_two = false;
    private boolean task_three = false;
    private int count = 3;
    private TextView countDown;
    private UpdateBean updateBean;


    @Override
    public int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    public void initView() {


        countDown = (TextView) findViewById(R.id.countDown);
        welImg = (ImageView) findViewById(R.id.wel_img);
    }

    @Override
    public void initPresenter() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏
        mPresenter = new WelcomePresenter(this);
    }

    @Override
    public void initData() {
        mPresenter.getDataHome();
        mPresenter.getAppUpdate();
        handler.sendEmptyMessageDelayed(COUNT_TIME,1000);

    }

    @Override
    public void onHomeData(HomeBean homeBean) {
        CacheManager.getInstance().setHomeBean(homeBean);
        task_two = true;
        handler.sendEmptyMessage(ONE_TASK_FIISH);
    }

    @Override
    public void onAppUpdate(UpdateBean updateBean) {
        this.updateBean = updateBean;
        task_three = true;
        handler.sendEmptyMessage(ONE_TASK_FIISH);

    }

    @Override
    public void showLoading() {
    }

    @Override
    public void hideLoading() {
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, "错误:"+error, Toast.LENGTH_SHORT).show();
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if(msg.what == COUNT_TIME){
                count--;
                if(count < 0 ){
                    task_one = true;
                    handler.sendEmptyMessage(ONE_TASK_FIISH);
                } else{
                    countDown.setText(count+"s");
                    handler.sendEmptyMessageDelayed(COUNT_TIME,1000);
                }
            } else if(msg.what == ONE_TASK_FIISH){
                if(task_one && task_two && task_three){
                    //跳转
                    int oldCode = 0;
                    try {
                        oldCode = getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
                    } catch (PackageManager.NameNotFoundException e) {
                        e.printStackTrace();
                    }
                    if (oldCode < updateBean.getResult().getVersionCode()) {
                        //提示更新
                        AlertDialog.Builder builder = new AlertDialog.Builder(WelcomeActivity.this);
                        builder.setTitle("下载最新版本");
                        builder.setMessage(updateBean.getResult().getDesc());
                        builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //下载数据
                                Toast.makeText(WelcomeActivity.this, "下载数据", Toast.LENGTH_SHORT).show();
                                ProgressDialog pro = new ProgressDialog(WelcomeActivity.this);
                                pro.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                                pro.show();
                            }
                        });
                        builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //跳转界面
                                //关闭此页面

                                ARouter.getInstance().build("/app/MainActivity").navigation();
                                finish();
                            }
                        });
                        AlertDialog alertDialog = builder.create();
                        builder.show();

                    } else {
                        //最新版本
                        finish();
                    }
                }
            }

        }
    };

    public boolean onKeyDown(int keyCode,KeyEvent event){
        if(keyCode==KeyEvent.KEYCODE_BACK)
            return true;
        return super.onKeyDown(keyCode, event);
    }//屏蔽返回键

    @Override
    public void destroy() {
        super.destroy();
        handler.removeCallbacksAndMessages(null);
    }

    @Override
    public void onClickCenter() {

    }

    @Override
    public void onClickLeft() {

    }

    @Override
    public void onClickRight() {

    }


}