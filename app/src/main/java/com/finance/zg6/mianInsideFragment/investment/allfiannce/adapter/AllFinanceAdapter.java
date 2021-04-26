package com.finance.zg6.mianInsideFragment.investment.allfiannce.adapter;

import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.finance.net.bean.ProductBean;
import com.finance.zg.R;
import com.finance.zg6.view.ProgressView;

import java.util.List;
import java.util.logging.LogRecord;

public class AllFinanceAdapter extends BaseQuickAdapter<ProductBean.ResultBean, BaseViewHolder> {

    private float rawX;
    private int scrollDiffx;

    private int position = 0;
    private int lastPosition = -1;

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

        helper.itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        rawX = motionEvent.getRawX();
                        helper.itemView.getParent().requestDisallowInterceptTouchEvent(true);
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        if (rawX>500&&motionEvent.getRawX()<rawX){
                            helper.itemView.getParent().requestDisallowInterceptTouchEvent(true);
                            scrollDiffx = (int) -(motionEvent.getRawX()-rawX);
                            helper.itemView.scrollTo(scrollDiffx,0);
                            break;
                        }else {
                            helper.itemView.scrollTo(0,0);
                            helper.itemView.getParent().requestDisallowInterceptTouchEvent(false);
                            break;
                        }
                    case MotionEvent.ACTION_UP:
                        if (scrollDiffx>300){
                            helper.itemView.scrollTo(200,0);
                        }else {
                            helper.itemView.scrollTo(0,0);
                        }
                        break;
                }
                return true;
            }
        });

    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int positions) {
        super.onBindViewHolder(holder, positions);

    }
}
