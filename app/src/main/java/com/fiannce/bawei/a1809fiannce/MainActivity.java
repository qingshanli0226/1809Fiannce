package com.fiannce.bawei.a1809fiannce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.fiannce.bawei.framework.BaseActivity;
import com.fiannce.bawei.framework.manager.CacheManager;
import com.fiannce.bawei.framework.view.ProgressView;
import com.fiannce.bawei.framework.view.ToolBar;
import com.fiannce.bawei.net.mode.HomeBean;
import com.fiannce.bawei.pay.PayActivity;
import com.fiannce.bawei.user.LoginActivity;



@Route(path="/main/MainActivity")
public class MainActivity extends BaseActivity {
    private TextView progressTv;
    private ProgressView progressView;

    @Override
    protected int getLayoutId() {
        Log.d("lqs", "");
        return R.layout.activity_main;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {
        HomeBean homeBean = CacheManager.getInstance().getHomeBean();
        progressTv.setText("主页面: "+homeBean.toString());
        progressView.saledProgress(20,true);
    }

    @Override
    protected void initView() {
        progressTv = findViewById(R.id.progressTv);
        progressView = findViewById(R.id.progressView);

    }

    @Override
    public void destroy() {
        super.destroy();
        progressView.destry();
    }

    @Override
    public void onRightImgClick() {
        //Toast.makeText(this, "点击了主页的按钮", Toast.LENGTH_SHORT).show();
        ARouter.getInstance().build("/event/EventActivity").withInt("",0).navigation();
    }

    //事件分发的函数，会将事件一层层传递到View上
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("LQS", "收到Down事件:" + ev.getRawX()+ev.getRawY());
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("LQS", "收到Move事件");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("LQS", "收到Up事件");
                break;
        }

        return super.dispatchTouchEvent(ev);
    }

    //事件消费函数
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("LQS onTouchEvent", "收到Down事件:" + ev.getRawX()+ev.getRawY());
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("LQS onTouchEvent", "收到Move事件");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("LQS onTouchEvent", "收到Up事件");
                break;
        }
        return super.onTouchEvent(ev);
    }
}
