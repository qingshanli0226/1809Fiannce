package com.finance.zg6.main.investment.allfiannce.adapter;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.finance.net.bean.ProductBean;
import com.finance.zg.R;
import com.finance.zg6.view.ProgressView;

import java.util.List;

public class AllFinanceAdapter extends BaseQuickAdapter<ProductBean.ResultBean, BaseViewHolder> {

    private float rawX;
    private int scrollDiffx;
    private final int VIEW_WIDTH = 200;
    private boolean isOpen = true;
    public AllFinanceAdapter(@Nullable List<ProductBean.ResultBean> data) {
        super(R.layout.item_all_finance_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProductBean.ResultBean item) {
        TextView view = helper.getView(R.id.all_fiannce_name);
        TextView view1 = helper.getView(R.id.all_finance_money);
        TextView view2 = helper.getView(R.id.all_finance_year_Rate);
        TextView view3 = helper.getView(R.id.all_finance_year_suodingDays);
        TextView view4 = helper.getView(R.id.all_finance_minTouMoney);
        TextView view5 = helper.getView(R.id.all_finance_memberNum);
        ProgressView view6 = helper.getView(R.id.all_finance_progress_view);

        view.setText(""+item.getName());
        view1.setText(""+item.getMoney());
        view2.setText(""+item.getYearRate());
        view3.setText(""+item.getSuodingDays());
        view4.setText(""+item.getMinTouMoney());
        view5.setText(""+item.getMemberNum());
        view6.startProgress(Integer.parseInt(item.getProgress()),false);

        helper.addOnClickListener(R.id.txt_delete);

        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               helper.itemView.scrollTo(0,0);
            }
        });

        helper.itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        if (AllFinanceView!=null&&AllFinanceView!=helper.itemView){
                            AllFinanceView.scrollTo(0,0);
                        }

                        rawX = motionEvent.getRawX();
                        helper.itemView.getParent().requestDisallowInterceptTouchEvent(false);
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        if (rawX>500&&motionEvent.getRawX()<rawX){
                            helper.itemView.getParent().requestDisallowInterceptTouchEvent(true);
                            scrollDiffx = (int) -(motionEvent.getRawX()-rawX);
                            if (isOpen==false){
                                helper.itemView.scrollTo(scrollDiffx+VIEW_WIDTH,0);
                            }else {
                                helper.itemView.scrollTo(scrollDiffx,0);
                            }

                            Log.i(TAG, "onTouch1: "+scrollDiffx);

                            break;
                        }else if (rawX>500&&motionEvent.getRawX()>rawX){
                            helper.itemView.getParent().requestDisallowInterceptTouchEvent(true);
                            scrollDiffx = (int) -(motionEvent.getRawX()-rawX);
                            helper.itemView.scrollTo(scrollDiffx+VIEW_WIDTH,0);
                            Log.i(TAG, "onTouch: "+scrollDiffx);

                            break;
                        }
                    case MotionEvent.ACTION_UP:

                        helper.itemView.getParent().requestDisallowInterceptTouchEvent(false);
                        if (scrollDiffx>VIEW_WIDTH){
                            AllFinanceView= helper.itemView;

                            isOpen=false;
                            helper.itemView.scrollTo(VIEW_WIDTH,0);
                        }else if (scrollDiffx>0&&scrollDiffx<VIEW_WIDTH){
                            if (isOpen){
                                AllFinanceView = null;
                                helper.itemView.scrollTo(0,0);
                            }else {
                                AllFinanceView= helper.itemView;
                                helper.itemView.scrollTo(VIEW_WIDTH,0);
                            }
                        }else if (scrollDiffx>-VIEW_WIDTH&&scrollDiffx<0){
                            AllFinanceView= helper.itemView;
                            helper.itemView.scrollTo(200,0);
                        }else if (scrollDiffx<-VIEW_WIDTH){
                            AllFinanceView = null;
                            helper.itemView.scrollTo(0,0);
                            isOpen = true;
                        }
                        break;
                }
                return false;
            }
        });

    }

    private View AllFinanceView;

}
