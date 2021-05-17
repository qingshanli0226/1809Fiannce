package com.example.framework;

import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.framework.manager.FiannceArouter;
import com.example.framework.manager.FiannceConnectManager;
import com.example.framework.manager.FiannceUserManager;
import com.example.framework.view.LoadingPage;
import com.example.framework.view.ToolBar;
import com.example.sp.SpUtils;

import static com.example.demo.Demo.AROUTE_PATH_GESTUREPASSWORD;
import static com.example.demo.Demo.AROUTE_PATH_GUANGGAO;
import static com.example.demo.Demo.AROUTE_PATH_LOGINBYGESTUREPASSWORD;

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements ToolBar.IToolbarListener, FiannceConnectManager.IConnectListener, FiannceUserManager.IUserLoginChanged {

    protected T httpPresenter;
    protected ToolBar toolBar;
    private boolean isUseLoading = true;
    protected LoadingPage loadingPage;
    private String time;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadingPage = new LoadingPage(this) {
            @Override
            protected int getSuccessLayoutId() {
                return getLayoutId();
            }
        };
        setContentView(loadingPage);
        initView();
        toolBar = findViewById(R.id.toolbar);
        //toolBar.setToolbarListener(this);
        initPresenter();
        initData();

        //页面启动时，注册网络回调接口
        FiannceConnectManager.getInstance().registerConnectListenter(this);

        FiannceUserManager.getInstance().register(this);

    }

    protected abstract void initData();

    protected abstract void initPresenter();

    protected abstract void initView();

    protected abstract int getLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroy();
        FiannceConnectManager.getInstance().unRegisterConnectListenter(this);
        FiannceUserManager.getInstance().unRegister(this);
    }

    public void destroy(){
        if (httpPresenter!=null){
            httpPresenter.detachView();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //读取秒
        String newTimes = SpUtils.getTime(FrameModel.context);
        long newTime = Long.parseLong(newTimes);
        long l = System.currentTimeMillis() - newTime;
        Log.i("zrf", "onRestart: "+ l);

        if (System.currentTimeMillis() - newTime > 5 * 1000){
            if (FiannceUserManager.getInstance().isLogin()){
                ARouter.getInstance().build(AROUTE_PATH_LOGINBYGESTUREPASSWORD).navigation();
                String time = System.currentTimeMillis() + "";
                SpUtils.putTime(FrameModel.context,time);
            }else {
                Toast.makeText(this, "为了您和他人的信息安全请你尽快登录,并且设置手势密码", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "时间未到5秒", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //存储数据
        time = System.currentTimeMillis() + "";
        SpUtils.putTime(FrameModel.context,System.currentTimeMillis()+"");
    }

    @Override
    public void onLeftClick() {
        finish();
    }

    @Override
    public void onRightImgClick() {
    }

    @Override
    public void onRightTvClick() {

    }

    @Override
    public void onConnected() {

    }

    @Override
    public void onDisconnected() {

    }

    @Override
    public void onLoginChange(boolean isLogin) {

    }
}
