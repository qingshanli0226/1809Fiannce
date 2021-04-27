package com.fiannce.bawei.fragment.investFragment.tabFragment.allFragment;

import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fiannce.framework.view.ProgressView;
import com.fiannce.net.mode.InvestBean;
import com.fiannce.zhaoyuzan.R;

import java.util.ArrayList;
import java.util.List;

public class InvestAdapter extends BaseQuickAdapter<InvestBean.ResultBean, BaseViewHolder> {

    private List<InvestBean.ResultBean> data = new ArrayList<>();
    private View lastItemView;
    private int lastPosition;
    private int scrollDiffX;
    private int lastX;

    public InvestAdapter(int layoutResId, @Nullable List<InvestBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder baseViewHolder, InvestBean.ResultBean resultBean) {
        baseViewHolder.setText(R.id.alltitle,resultBean.getName());
        baseViewHolder.setText(R.id.allonemoney,resultBean.getMoney());
        baseViewHolder.setText(R.id.alltwopercentage,resultBean.getYearRate());
        baseViewHolder.setText(R.id.allthreeday,resultBean.getSuodingDays());
        baseViewHolder.setText(R.id.allonenum,resultBean.getMinTouMoney());
        baseViewHolder.setText(R.id.allthreenum,resultBean.getMemberNum());
        Button button = baseViewHolder.getView(R.id.txt_delete);
        baseViewHolder.addOnClickListener(R.id.txt_delete);
        ProgressView progressView = baseViewHolder.getView(R.id.allmyview);
        progressView.saledProgress(Integer.valueOf(resultBean.getProgress()),false);
        baseViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lastItemView.scrollTo(0,0);
                lastItemView = null;
                lastPosition = -1;


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
                        if (lastX > 300 && event.getRawX()<lastX) {
                            baseViewHolder.itemView.getParent().requestDisallowInterceptTouchEvent(true);
                            if (lastItemView!=null&&baseViewHolder.getPosition()!=lastPosition) {
                                lastItemView.scrollTo(0,0);
                            }
                            lastPosition = baseViewHolder.getAdapterPosition();
                            lastItemView = baseViewHolder.itemView;
                            scrollDiffX = (int) (lastX - event.getRawX());
                            if (scrollDiffX > 200){
                                scrollDiffX = 200;
                            }
                            baseViewHolder.itemView.scrollTo(scrollDiffX,0);
                            return true;
                        } else if (lastX > 500 && event.getRawX() > lastX){
                            baseViewHolder.itemView.getParent().requestDisallowInterceptTouchEvent(true);
                            scrollDiffX = (int) (lastX - event.getRawX());
                            if (scrollDiffX < 0) {
                                baseViewHolder.itemView.scrollTo(0, 0);
                            }
                        }else {
                            baseViewHolder.itemView.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                    case MotionEvent.ACTION_UP:
                        if (scrollDiffX > 80){
                            baseViewHolder.itemView.scrollTo(200, 0);
                        }else {
                            baseViewHolder.itemView.scrollTo(0,0);
                        }
                        return true;
                }
                return false;//如果不是我们关注的事件，就不消费了
            }
        });
    }
}
