package com.example.gitproject.invest.manageFinances.adapter;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.framework.view.ProgressView;
import com.example.gitproject.R;
import com.example.net.bean.ProductBean;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class FinancesAdapter extends BaseQuickAdapter<ProductBean.ResultBean, BaseViewHolder> {
    public FinancesAdapter() {
        super(R.layout.item_managefinances, new ArrayList<>());
    }
    private int lastx;
    private View lastView = null;
    private int position = -1;
    private int mX;
    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, ProductBean.ResultBean resultBean) {
        baseViewHolder.setText(R.id.finances_title,resultBean.getName());
        baseViewHolder.setText(R.id.finances_money,resultBean.getMoney());
        baseViewHolder.setText(R.id.finances_memberNum,resultBean.getMemberNum());
        baseViewHolder.setText(R.id.finances_minTouMoney,resultBean.getMinTouMoney());
        baseViewHolder.setText(R.id.finances_suodingDays,resultBean.getSuodingDays());
        baseViewHolder.setText(R.id.finances_yeatRate,resultBean.getYearRate());
        ProgressView progressView = baseViewHolder.getView(R.id.finances_progress);
        progressView.startProgress(Integer.parseInt(resultBean.getProgress()),false);

        addChildClickViewIds(R.id.item_del);

        View itemView = baseViewHolder.itemView;
        TextView del = baseViewHolder.getView(R.id.item_del);
        //删除
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(baseViewHolder.getPosition());
            }
        });

        baseViewHolder.itemView.setTag(false);
        itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        lastx = (int) event.getRawX();
                        itemView.getParent().requestDisallowInterceptTouchEvent(true);

                        if(lastView != null && position != baseViewHolder.getPosition()){
                            lastView.setTag(false);
                            lastView.scrollTo(0,0);
                        }
                        lastView = itemView;

                        return true;
                    case MotionEvent.ACTION_MOVE:
                        boolean tag = (boolean) itemView.getTag();
                        if(event.getRawX() > 50 && event.getRawX() > lastx && tag){//回滑
                            itemView.getParent().requestDisallowInterceptTouchEvent(true);
                            mX = (int) (event.getRawX() - lastx);
                            itemView.scrollTo(del.getMeasuredWidth()-mX,0);
                        } else if(event.getRawX() < 900 && lastx > event.getRawX() && !tag){//出莱
                            itemView.getParent().requestDisallowInterceptTouchEvent(true);
                            mX = (int) ( lastx- event.getRawX());
                            Log.i("TAG", "onTouch:bbb "+mX);
                            itemView.scrollTo(mX,0);
                            position = baseViewHolder.getPosition();
                        } else {
                            itemView.getParent().requestDisallowInterceptTouchEvent(false);
                        }

                        return true;

                    case MotionEvent.ACTION_UP:
                            boolean flag = (boolean) itemView.getTag();
                        Log.i("TAG", "onTouch: "+mX);
                        Log.i("TAG", "onTouch: "+del.getMeasuredWidth());
                            if(mX > del.getMeasuredWidth()/2 && !flag){
                                itemView.scrollTo(del.getMeasuredWidth(),0);
                                itemView.setTag(true);
                            }else if(mX > del.getMeasuredWidth()/2 && flag){
                                itemView.scrollTo(0,0);
                                itemView.setTag(false);

                            } else if(mX <del.getMeasuredWidth()/2 && flag){
                                itemView.scrollTo(del.getMeasuredWidth(),0);
                            } else{
                                itemView.scrollTo(0,0);
                                itemView.setTag(false);
                            }
                            break;
                }
                return false;
            }
        });
    }

    public void hideDel(){
        lastView.scrollTo(0,0);
        lastView = null;

    }
}
