package com.example.a1809fiannce.adapter;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.a1809fiannce.R;
import com.example.framework.view.ProgressView;
import com.example.net.mode.AllfinancialBean;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AllfinancialAdapter extends BaseQuickAdapter<AllfinancialBean.ResultBean, BaseViewHolder> {
    public AllfinancialAdapter(@Nullable List<AllfinancialBean.ResultBean> data) {
        super(R.layout.item_investment_all_moneymanagement, data);
    }

    private int lastx,lastY;
    private View lastView;
    private int position;
    private int mX;
    private int delWidth;
    private boolean is;

    @Override
    protected void convert(@NotNull BaseViewHolder holder, AllfinancialBean.ResultBean resultBean) {
        holder.setText(R.id.all_investment_title, resultBean.getName());
        holder.setText(R.id.all_investment_money, resultBean.getMoney());
        holder.setText(R.id.all_investment_minTouMoney, resultBean.getMinTouMoney());
        holder.setText(R.id.all_investment_yeatRate, resultBean.getYearRate());
        holder.setText(R.id.all_investment_suodingDays, resultBean.getSuodingDays());
        holder.setText(R.id.all_investment_memberNum, resultBean.getMemberNum());

        ProgressView progressView = holder.getView(R.id.all_investment_progress);
        progressView.saledProgress(Integer.parseInt(resultBean.getProgress()), false);

        TextView delText = holder.getView(R.id.delText);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scroolToView();
            }
        });

        addChildClickViewIds(R.id.delText);

//        holder.itemView.setTag(false);

//        holder.itemView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                switch (motionEvent.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        holder.itemView.getParent().requestDisallowInterceptTouchEvent(true);
//
//                        lastX = (int) motionEvent.getRawX();
//                        lastY = (int) motionEvent.getRawY();
//                        holder.itemView.setTag(false);
//                        return true;
//
//                    case MotionEvent.ACTION_MOVE:
//                        boolean tag = (boolean) holder.itemView.getTag();
//                        if (lastX > 800 && motionEvent.getRawX() < lastX &&!tag) {
//                            holder.itemView.getParent().requestDisallowInterceptTouchEvent(true);
//                            if (lastPosition != holder.getAdapterPosition() && lastView != null) {
//                                lastView.scrollTo(0, 0);
//                                lastView=null;
//                                lastPosition=-1;
////                                is=false;
//                            }
//
//                            lastView = holder.itemView;
//                            lastPosition = holder.getAdapterPosition();
//                            delWidth = delText.getWidth();
////                            is=true;
//
//                            int z = (int) (lastX - motionEvent.getRawX());
//                            holder.itemView.scrollTo(z, 0);
//                            return true;
//                        }
//                         if (lastX > 700 && motionEvent.getRawX() > lastX &&tag) {
//                            holder.itemView.getParent().requestDisallowInterceptTouchEvent(true);
//
////                            lastView = null;
////                            lastPosition = -1;
//                            int z = (int) (motionEvent.getRawX() - lastX);
//                            holder.itemView.scrollTo(z+delWidth, 0);
////                            holder.itemView.scrollTo(z,0);
//                             return true;
//                        } else {
//                            holder.itemView.getParent().requestDisallowInterceptTouchEvent(false);
//                        }
//                        break;
//
//                    case MotionEvent.ACTION_UP:
//
//                        if (lastX - motionEvent.getRawX() > delWidth / 2) {
//
//                            lastView = holder.itemView;
//                            lastPosition = holder.getAdapterPosition();
//                            holder.itemView.scrollTo(delWidth, 0);
//
//                            holder.itemView.setTag(true);
//
////                            is=true;
//                        }else if ( motionEvent.getRawX()-lastX < delWidth / 2 ){
//
//                            LogUtils.e(motionEvent.getRawX()-lastX - lastX);
//                            holder.itemView.scrollTo(0, 0);
//
////                            lastView=holder.itemView;
//
//                            lastView=null;
//                            lastPosition=-1;
////                            is=false;
//                        }
//                        else {
//                            holder.itemView.scrollTo(0, 0);
//
//                            holder.itemView.setTag(false);
//                        }
//                        break;
//                }
//                return false;
//            }
//        });

        holder.itemView.setTag(false);
        holder.itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        lastx = (int) event.getRawX();
                        holder.itemView.getParent().requestDisallowInterceptTouchEvent(true);

                        if(lastView != null && position != holder.getPosition()){
                            lastView.setTag(false);
                            lastView.scrollTo(0,0);
                        }
                        lastView = holder.itemView;

                        return true;
                    case MotionEvent.ACTION_MOVE:
                        boolean tag = (boolean) holder.itemView.getTag();
                        if(event.getRawX() > 800 && event.getRawX() > lastx && tag){//回滑
                            holder.itemView.getParent().requestDisallowInterceptTouchEvent(true);
                            mX = (int) (event.getRawX() - lastx);
                            holder.itemView.scrollTo(delText.getMeasuredWidth()-mX,0);
                        } else if(event.getRawX() < 900 && lastx > event.getRawX() && !tag){//出莱
                            holder.itemView.getParent().requestDisallowInterceptTouchEvent(true);
                            mX = (int) ( lastx- event.getRawX());
                            Log.i("TAG", "onTouch:bbb "+mX);
                            holder.itemView.scrollTo(mX,0);
                            position = holder.getPosition();
                        } else {
                            holder.itemView.getParent().requestDisallowInterceptTouchEvent(false);
                        }

                        return true;

                    case MotionEvent.ACTION_UP:
                        boolean flag = (boolean) holder.itemView.getTag();
                        Log.i("TAG", "onTouch: "+mX);
                        Log.i("TAG", "onTouch: "+delText.getMeasuredWidth());
                        if(mX > delText.getMeasuredWidth()/2 && !flag){
                            holder.itemView.scrollTo(delText.getMeasuredWidth(),0);
                            holder.itemView.setTag(true);
                        }else if(mX > delText.getMeasuredWidth()/2 && flag){
                            holder.itemView.scrollTo(0,0);
                            holder.itemView.setTag(false);

                        } else if(mX <delText.getMeasuredWidth()/2 && flag){
                            holder.itemView.scrollTo(delText.getMeasuredWidth(),0);
                        } else{
                            holder.itemView.scrollTo(0,0);
                            holder.itemView.setTag(false);
                        }
                        break;
                }
                return false;
            }
        });
    }

    public void scroolToView(){
        lastView.scrollTo(0, 0);
        position = -1;
        is=false;
    }
}
