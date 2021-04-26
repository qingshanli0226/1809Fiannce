package com.example.myfinancial.fragment.inves.fragment;


import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.framework.myview.PregressMyView;
import com.example.myfinancial.R;
import com.example.net.bean.AllMoneyBean;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AllAdapter extends BaseQuickAdapter<AllMoneyBean.ResultBean, BaseViewHolder> {
    private int lastX;
    private int lastposition;
    private int position;
    private int scrodiffx;//滑动距离
    private View lastitemView;
    private boolean isUpOrDown = true;//判断左滑还是右滑


    public AllAdapter(@Nullable List<AllMoneyBean.ResultBean> data) {
        super(R.layout.all_rec_lay, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder viewHolder, AllMoneyBean.ResultBean resultBean) {
        viewHolder.setText(R.id.allOneneMoney, resultBean.getMoney());
        viewHolder.setText(R.id.allTitle, resultBean.getName());
        viewHolder.setText(R.id.allOneNum, resultBean.getMinTouMoney());
        viewHolder.setText(R.id.allTwoPercentage, resultBean.getYearRate());
        viewHolder.setText(R.id.allThreeDay, resultBean.getSuodingDays());
        viewHolder.setText(R.id.allThreeNum, resultBean.getMemberNum() + "");
        PregressMyView pregressMyView = viewHolder.getView(R.id.allProView);
        pregressMyView.getProgressNum(Integer.parseInt(resultBean.getProgress()), false);
        pregressMyView.settextColor(Color.BLUE);
        pregressMyView.setCirCleWidth(10);
        //添加点及
        addChildClickViewIds(R.id.delbtn);

        if (!resultBean.isDel()) {
            viewHolder.itemView.scrollTo(0, 0);
        }

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastposition = -1;
                lastitemView = null;
                viewHolder.itemView.scrollTo(0, 0);
            }
        });
        viewHolder.itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        lastX = (int) event.getRawX();
                        viewHolder.itemView.getParent().requestDisallowInterceptTouchEvent(true);
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        if (lastX > 500 && event.getRawX() < lastX) {
                            isUpOrDown = true;
                            viewHolder.itemView.getParent().requestDisallowInterceptTouchEvent(true);
                            if (lastitemView != null && lastposition != viewHolder.getAdapterPosition()) {
                                lastitemView.scrollTo(0, 0);//回去
                            }
                            lastposition = viewHolder.getAdapterPosition();
                            lastitemView = viewHolder.itemView;
                            scrodiffx = (int) (lastX - event.getRawX());
                            if (scrodiffx > 200) {
                                scrodiffx = 200;
                            }
                            viewHolder.itemView.scrollTo(scrodiffx, 0);
                            return true;
                        } else if (lastX > 500 && event.getRawX() > lastX) {//滑回去
                            Log.d("AllAdapter", "lala");
                            isUpOrDown = false;
                            Log.d("AllAdapter", "22");
                            viewHolder.itemView.getParent().requestDisallowInterceptTouchEvent(true);
                            scrodiffx = (int) (lastX - event.getRawX());
                            if (scrodiffx < 0) {
                                Log.d("AllAdapter", "scrodiffx:" + scrodiffx);
                                viewHolder.itemView.scrollTo(scrodiffx, 0);
                            }
                        } else {
                            viewHolder.itemView.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                    case MotionEvent.ACTION_UP:

                        if (isUpOrDown) {

                            if (Math.abs(scrodiffx) < 100) {
                                Log.d("AllAdapter", "1");
                                viewHolder.itemView.scrollTo(0, 0);
                            } else {
                                resultBean.setDel(true);
                                Log.d("AllAdapter", "2");
                                viewHolder.itemView.scrollTo(200, 0);
                            }
                        } else {
                            resultBean.setDel(false);
                            viewHolder.itemView.scrollTo(0, 0);
                        }
                        scrodiffx = 0;
                        return true;
                }
                return false;
            }
        });
    }
}
