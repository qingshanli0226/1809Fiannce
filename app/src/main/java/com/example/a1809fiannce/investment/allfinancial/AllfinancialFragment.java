package com.example.a1809fiannce.investment.allfinancial;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.example.a1809fiannce.R;
import com.example.a1809fiannce.adapter.AllfinancialAdapter;
import com.example.framework.BaseFragment;
import com.example.net.mode.AllfinancialBean;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import java.util.ArrayList;
import java.util.List;

public class AllfinancialFragment extends BaseFragment<AllfinancialPresenter> implements IAllfinancial {
    private RecyclerView allfinancialRv;
    private List<AllfinancialBean.ResultBean> list = new ArrayList<>();
    private AllfinancialAdapter allfinancialAdapter;

    private int rawX;
    private int rawY;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_allfinancial;
    }

    @Override
    protected void initView() {
        allfinancialRv = (RecyclerView) findViewById(R.id.allfinancial_rv);
    }

    @Override
    protected void initData() {
        httpPresenter.getAllfinancial();

//        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
//            @Override
//            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
//                return makeMovementFlags(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT);
//            }
//
//            @Override
//            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//                return false;
//            }
//
//            @Override
//            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//
//                int adapterPosition = viewHolder.getAdapterPosition();
//
//                list.remove(adapterPosition);
//
//                allfinancialAdapter.notifyItemRemoved(adapterPosition);
//            }
//        });
//
//        itemTouchHelper.attachToRecyclerView(allfinancialRv);


    }

    @Override
    protected void initPresenter() {
        httpPresenter = new AllfinancialPresenter(this);
    }

    @Override
    public void onAllfinancial(AllfinancialBean allfinancialBean) {

        list.addAll(allfinancialBean.getResult());

        allfinancialAdapter = new AllfinancialAdapter(list);
        allfinancialRv.setAdapter(allfinancialAdapter);
        allfinancialRv.setLayoutManager(new LinearLayoutManager(getContext()));

//        allfinancialRv.setOnTouchListener(this);

        allfinancialAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                switch (view.getId()){
                    case R.id.delText:
                        list.remove(position);

                        allfinancialAdapter.notifyItemRemoved(position);
                        break;
                }
            }
        });
    }

    @Override
    public void showLoading() {
        loadingPage.showLoadingView();
    }

    @Override
    public void hideLoading() {
        loadingPage.showSuccessView();
    }

    @Override
    public void Error(String error) {
        loadingPage.showError(error);
    }

    int lastX, lastY;

//    @Override
//    public boolean onTouch(View view, MotionEvent motionEvent) {
//        switch (motionEvent.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                lastX = (int) motionEvent.getRawX();
//                lastY = (int) motionEvent.getRawY();
//
//                allfinancialRv.getParent().requestDisallowInterceptTouchEvent(true);
//                break;
//
//            case MotionEvent.ACTION_MOVE:
//                if ((lastX < 50 || lastX > 900) && (Math.abs(motionEvent.getRawY() - lastY) + 20 < Math.abs(motionEvent.getRawX() - lastX))) {
//                    allfinancialRv.getParent().requestDisallowInterceptTouchEvent(true);
//                } else {
//                    allfinancialRv.getParent().requestDisallowInterceptTouchEvent(false);
//                }
//                break;
//        }
//        return false;
//    }
}