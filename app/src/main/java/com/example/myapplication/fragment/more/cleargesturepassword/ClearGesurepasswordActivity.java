package com.example.myapplication.fragment.more.cleargesturepassword;

import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.framework.BaseActivity;
import com.example.framework.view.ToolBar;
import com.example.model.GesturePasswordBean;
import com.example.myapplication.R;
import com.wangnan.library.GestureLockView;
import com.wangnan.library.listener.OnGestureLockListener;

import java.util.HashMap;
import java.util.Map;

import static com.example.demo.Demo.AROUTE_PATH_CLEARGESTUREPASSWORD;

@Route(path = AROUTE_PATH_CLEARGESTUREPASSWORD)
public class ClearGesurepasswordActivity extends BaseActivity<ClearGesurepasswordPresenter> implements IClearGesurepasswordView {
    private com.example.framework.view.ToolBar toolbar;
    private com.wangnan.library.GestureLockView glv;
    private Map<String,String> map = new HashMap<>();

    @Override
    protected void initData() {

        glv.setGestureLockListener(new OnGestureLockListener() {
            @Override
            public void onStarted() {

            }

            @Override
            public void onProgress(String progress) {

            }

            @Override
            public void onComplete(String result) {
                if (!result.equals("")){
                    map.put("gPassword",result);
                    httpPresenter.startClear(map);
                }
            }
        });

    }

    @Override
    protected void initPresenter() {
        httpPresenter = new ClearGesurepasswordPresenter(this);
    }

    @Override
    protected void initView() {

        toolbar = (ToolBar) findViewById(R.id.toolbar);
        glv = (GestureLockView) findViewById(R.id.glv);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_clear_passwprd;
    }

    @Override
    public void clearPassword(GesturePasswordBean bean) {
        if (bean.getCode().equals("200")){
            Toast.makeText(this, "清除手势密码", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String error) {

    }
}
