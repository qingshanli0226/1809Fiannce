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
import com.fiannce.bawei.a1809fiannce.connect.ConnectActivity;
import com.fiannce.bawei.a1809fiannce.leak.MVPActivity;
import com.fiannce.bawei.a1809fiannce.leak.MemActivity;
import com.fiannce.bawei.a1809fiannce.leak.ThreadActivity;
import com.fiannce.bawei.a1809fiannce.startmode.AActivity;
import com.fiannce.bawei.a1809fiannce.startmode.BActivity;
import com.fiannce.bawei.common.FiannceConstants;
import com.fiannce.bawei.framework.BaseActivity;
import com.fiannce.bawei.framework.manager.CacheManager;
import com.fiannce.bawei.framework.manager.FiannceArouter;
import com.fiannce.bawei.framework.manager.FiannceUserManager;
import com.fiannce.bawei.framework.view.ProgressView;
import com.fiannce.bawei.framework.view.ToolBar;
import com.fiannce.bawei.net.mode.HomeBean;
import com.fiannce.bawei.pay.PayActivity;
import com.fiannce.bawei.user.LoginActivity;

import java.util.Locale;


@Route(path="/main/MainActivity")
public class MainActivity extends BaseActivity implements FiannceUserManager.IUserLoginChanged {
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
        //HomeBean homeBean = CacheManager.getInstance().getHomeBean();
     /*   progressTv.setText("主页面: "+homeBean.toString());
        progressView.saledProgress(20,true);*/
    }

    @Override
    protected void initView() {
        if (FiannceUserManager.getInstance().isLogin()) {
            Toast.makeText(this, "当前用户已经登录", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "当前用户未登录", Toast.LENGTH_SHORT).show();
        }
        /*progressTv = findViewById(R.id.progressTv);
        progressView = findViewById(R.id.progressView);*/
        findViewById(R.id.startMode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.callBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FiannceArouter.getInstance().build(FiannceConstants.PAY_PATH).navigation();
                //FiannceArouter.getInstance().getPayInterface().openPayActivity(MainActivity.this,null);
            }
        });
        findViewById(R.id.connectBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ConnectActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.menBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent = new Intent(MainActivity.this,ThreadActivity.class);
                startActivity(intent);*/
               CacheManager.getInstance().init(MainActivity.this);
            }
        });

        FiannceUserManager.getInstance().register(this);

    }

    @Override
    public void destroy() {
        super.destroy();
        //progressView.destry();
        FiannceUserManager.getInstance().unRegister(this);
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

    @Override
    public void onLoginChange(boolean isLogin) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (isLogin) {
                    Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "退出登录", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
