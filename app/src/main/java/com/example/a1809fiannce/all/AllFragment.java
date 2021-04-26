package com.example.a1809fiannce.all;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a1809fiannce.welcome.CallBack;
import com.example.a1809fiannce.R;
import com.example.a1809fiannce.update.UpdatePresenter;
import com.example.framwork.base.BaseFragment;
import com.example.network.model.AllBean;
import com.example.network.model.HomeBean;
import com.example.network.model.UpdateBean;



public class AllFragment extends BaseFragment<UpdatePresenter> implements CallBack {
    private RecyclerView re;

    @Override
    protected void initData() {
        mPresenter=new UpdatePresenter(this);
        mPresenter.AllData();
    }

    @Override
    protected void initView() {
        re = (RecyclerView) BaseView.findViewById(R.id.re);
        re.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    @Override
    protected int FindLayout1() {
        return R.layout.fragment_all;
    }

    @Override
    public void HomeData(HomeBean homeBean) {

    }

    @Override
    public void UpdateData(UpdateBean updateBean) {

    }

    @Override
    public void AllData(AllBean allBean) {
        pageView.ShowSuccess();
        AllAdapter allAdapter = new AllAdapter(R.layout.item_all, allBean.getResult());
        re.setAdapter(allAdapter);
        
    }

    @Override
    public void ShowLoading() {
        pageView.ShowLoad();
    }

    @Override
    public void HideLoading() {

    }

    @Override
    public void Error(String error) {
        pageView.ShowError(error);
        //Toast.makeText(getContext(), ""+error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null){
            mPresenter.delView();
        }
    }
}