package com.example.myapplication.fragment.invest.allinvest;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.model.ProductBean;
import com.example.myapplication.R;
import com.example.framework.view.ProgressView;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MyMoneyAdapter extends BaseQuickAdapter<ProductBean.ResultBean, BaseViewHolder> {

    private int lastX;
    private View lastItemView;
    private int lastPosition;
    private int scrollDiffx;
    private int viewWindh = 0;
    private TextView view1;

    public MyMoneyAdapter( @Nullable List<ProductBean.ResultBean> data) {
        super(R.layout.item_allinvest, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, ProductBean.ResultBean resultBean) {
        baseViewHolder.setText(R.id.home_one_name,resultBean.getName());
        baseViewHolder.setText(R.id.home_one_money,resultBean.getMoney());
        baseViewHolder.setText(R.id.home_one_yearrate,resultBean.getYearRate());
        baseViewHolder.setText(R.id.home_one_suodingdays,resultBean.getSuodingDays());
        baseViewHolder.setText(R.id.home_one_mintoumoney,resultBean.getMinTouMoney());
        baseViewHolder.setText(R.id.home_one_membernum,resultBean.getMemberNum());
        ProgressView view = baseViewHolder.getView(R.id.home_one_progress);
        view.startProgressDraw(Integer.parseInt(resultBean.getProgress()),false);
//        baseViewHolder.setText(R.id.home_one_progress,resultBean.getProgress());
        int position = baseViewHolder.getPosition();

         view1 = baseViewHolder.getView(R.id.txt_delete);
        baseViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext(), "123", Toast.LENGTH_SHORT).show();
                lastItemView = null;
                scrollDiffx = 0;
                baseViewHolder.itemView.scrollTo(0,0);
            }
        });

        addChildClickViewIds(R.id.txt_delete);





        baseViewHolder.itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        lastX = (int) event.getRawX();
                        baseViewHolder.itemView.getParent().requestDisallowInterceptTouchEvent(true);
                        viewWindh = view1.getMeasuredWidth();
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        if (lastX > 500 && event.getRawX() < lastX){
                            baseViewHolder.itemView.getParent().requestDisallowInterceptTouchEvent(true);
                            if (lastItemView!=null && lastPosition!=position){
                                lastItemView.scrollTo(0,0);
                            }
                            lastPosition = position;
                            lastItemView = baseViewHolder.itemView;
                            scrollDiffx = - (int) (event.getRawX() - lastX);
                            Log.i("zrf", "onTouch: "+scrollDiffx);

                            if (scrollDiffx >= viewWindh){
                                baseViewHolder.itemView.scrollTo(scrollDiffx,0);
                            }

                            baseViewHolder.itemView.scrollTo(scrollDiffx,0);
                            return true;
                        }else {
//                            baseViewHolder.itemView.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                    case MotionEvent.ACTION_UP:

                        if (scrollDiffx < viewWindh/2){
                            lastItemView = null;
                            scrollDiffx = 0;
                            baseViewHolder.itemView.scrollTo(0,0);
                        }else {
                            baseViewHolder.itemView.scrollTo(viewWindh,0);
                        }

                        return true;
                }

                return false;
            }
        });
    }



}
