package com.example.pay;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

@Route(path = "/zx/zx1")
public class PayActivity extends AppCompatActivity {
    @Autowired(name = "name")
    String name;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_pay);
        //加个方法
        ARouter.getInstance().inject(this);
        Toast.makeText(this, ""+name, Toast.LENGTH_SHORT).show();
    }
}
