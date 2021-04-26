package com.fiance.chengtianle.Adapter;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.blankj.utilcode.util.TouchUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.fiance.chengtianle.R;
import com.fiance.framework.MyView.MyView;
import com.fiance.net.mode.LcBean;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends BaseQuickAdapter<LcBean.ResultBean, BaseViewHolder> {
    private List<LcBean.ResultBean> data=new ArrayList<>();
    private View lastItemView;
    private int lastPosition;
    private int scrollDiffx;
    private int lastX;


    public MyAdapter(int layoutResId, @Nullable List<LcBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, LcBean.ResultBean resultBean) {
        baseViewHolder.setText(R.id.alltitle,resultBean.getName());
        baseViewHolder.setText(R.id.allonemoney,resultBean.getMoney());
        baseViewHolder.setText(R.id.alltwopercentage,resultBean.getYearRate());
        baseViewHolder.setText(R.id.allthreeday,resultBean.getSuodingDays());
        baseViewHolder.setText(R.id.allonenum,resultBean.getMinTouMoney());
        baseViewHolder.setText(R.id.allthreenum,resultBean.getMemberNum());
        MyView myView = baseViewHolder.getView(R.id.allmyview);
        myView.saledProgress(Integer.parseInt(resultBean.getProgress()),false);

        addChildClickViewIds(R.id.btn);

        Button button = baseViewHolder.getView(R.id.btn);
        baseViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastItemView.scrollTo(0,0);
                lastItemView=null;
                lastPosition=-1;
            }
        });

         baseViewHolder.itemView.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 switch (event.getAction()) {
                     case MotionEvent.ACTION_DOWN:
                         lastX = (int) event.getRawX();
                         baseViewHolder.itemView.getParent().requestDisallowInterceptTouchEvent(true);
                         return true;
                     case MotionEvent.ACTION_MOVE:
                         if (lastX > 500 && event.getRawX() < lastX) {
                             baseViewHolder.itemView.getParent().requestDisallowInterceptTouchEvent(true);
                             if (lastItemView != null && baseViewHolder.getAdapterPosition() != lastPosition) {
                                 lastItemView.scrollTo(0, 0);
                             }
                             lastPosition = baseViewHolder.getAdapterPosition();
                             lastItemView = baseViewHolder.itemView;
                             scrollDiffx = -(int) ((event.getRawX() - lastX));
                             if (scrollDiffx > 200){
                                 scrollDiffx = 200;
                             }
                             baseViewHolder.itemView.scrollTo(scrollDiffx,0);//让ItemView向做滑动
                             return true;//消费该事件
                         } else if (lastX > 500 && event.getRawX() > lastX){
                             baseViewHolder.itemView.getParent().requestDisallowInterceptTouchEvent(true);
                             scrollDiffx = (int) (lastX - event.getRawX());
                             if (scrollDiffx < 0) {
                                 baseViewHolder.itemView.scrollTo(0, 0);
                             }
                             baseViewHolder.itemView.scrollTo(0, 0);
                         } else {
                             baseViewHolder.itemView.getParent().requestDisallowInterceptTouchEvent(false);
                         }
                         return true;
                 }
                 return false;
                 }
         });
    }
}
