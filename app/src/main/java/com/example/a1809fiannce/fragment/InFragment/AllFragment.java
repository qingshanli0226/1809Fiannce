package com.example.a1809fiannce.fragment.InFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.a1809fiannce.CallBack;
import com.example.a1809fiannce.R;
import com.example.a1809fiannce.fragment.InFragment.allfrag.AllAdapter;
import com.example.a1809fiannce.update.UpdatePresenter;
import com.example.formwork.model.AllBean;
import com.example.formwork.model.HomeBean;
import com.example.formwork.model.UpdateBean;
import com.example.network.BaseFragment;


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
    protected int FindLayout() {
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
        Log.i("zx", "AllData: "+allBean.toString());
        AllAdapter allAdapter = new AllAdapter(R.layout.item_all, allBean.getResult());
        re.setAdapter(allAdapter);
        
    }

    @Override
    public void ShowLoading() {

    }

    @Override
    public void HideLoading() {

    }

    @Override
    public void Error(String error) {
        Toast.makeText(getContext(), ""+error, Toast.LENGTH_SHORT).show();
    }
}