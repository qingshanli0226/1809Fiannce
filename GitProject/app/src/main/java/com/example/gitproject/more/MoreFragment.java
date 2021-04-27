package com.example.gitproject.more;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.framework.BaseFragment;
import com.example.framework.view.ItemBar;
import com.example.framework.view.ToolBar;
import com.example.gitproject.R;
import com.example.gitproject.utils.PathConstant;


public class MoreFragment extends BaseFragment {

    private ToolBar toolbar;
    private ItemBar itemRegister;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_more;
    }

    @Override
    protected void initView() {

        toolbar = (ToolBar) findViewById(R.id.toolbar);
        itemRegister = (ItemBar) findViewById(R.id.item_register);
    }

    @Override
    protected void initPrensenter() {

    }

    @Override
    protected void initData() {
        itemRegister.setItemOnClickLisenter(new ItemBar.ItemOnClickLisenter() {
            @Override
            public void leftOnClick() {

            }

            @Override
            public void rightOnClick() {

            }

            @Override
            public void titleOnClick() {

            }

            @Override
            public void rightTextOnClick() {

            }

            @Override
            public void itemOnClick() {
                //注册跳转
                ARouter.getInstance().build(PathConstant.USER_REGISTER_PATH).navigation();
            }
        });

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