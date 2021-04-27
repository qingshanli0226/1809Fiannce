package com.example.myfinancial.inves.all;


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
    private int lastPosition;
    private int scrodiffX;//滑动距离
    private View lastItemView;
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

        Button view = viewHolder.getView(R.id.delbtn);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastPosition = -1;
                lastItemView = null;
                viewHolder.itemView.scrollTo(0, 0);
                remove(viewHolder.getPosition());
            }
        });

        viewHolder.itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        lastX = (int) event.getRawX();
                        viewHolder.itemView.getParent().requestDisallowInterceptTouchEvent(true);
                        if (lastItemView != null && lastPosition != viewHolder.getAdapterPosition()) {
                            lastItemView.scrollTo(0, 0);//回去
                            resultBean.setDel(false);
                        }

                        lastPosition = viewHolder.getAdapterPosition();
                        lastItemView = viewHolder.itemView;
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        if (lastX > 500 && event.getRawX() < lastX) {
                                isUpOrDown = true;//判断是左滑
                                viewHolder.itemView.getParent().requestDisallowInterceptTouchEvent(true);
                                if (lastItemView != null && lastPosition != viewHolder.getAdapterPosition()) {
                                    lastItemView.scrollTo(0, 0);//回去
                                    resultBean.setDel(false);
                                }

                                lastPosition = viewHolder.getAdapterPosition();
                                lastItemView = viewHolder.itemView;
                                scrodiffX = (int) (lastX - event.getRawX());

                                if (scrodiffX > 200) {//200为删除按钮宽度
                                    scrodiffX = 200;
                                }

                                viewHolder.itemView.scrollTo(scrodiffX, 0);

                            return true;
                        } else if (resultBean.isDel()&&event.getRawX() > lastX) {//滑回去
                            isUpOrDown = false;//判断是右滑
                            viewHolder.itemView.getParent().requestDisallowInterceptTouchEvent(true);
                            if (lastItemView != null && lastPosition != viewHolder.getAdapterPosition()) {
                                lastItemView.scrollTo(0, 0);//回去
                                resultBean.setDel(false);
                            }
                            lastPosition = viewHolder.getAdapterPosition();
                            lastItemView = viewHolder.itemView;
                            scrodiffX = (int) (event.getRawX() - lastX);

                            if (resultBean.isDel()) {
                                lastItemView.scrollTo(view.getMeasuredWidth() - scrodiffX, 0);
                            }

                            if (scrodiffX > 200) {
                                scrodiffX = 200;
                            }

                        } else {
                            viewHolder.itemView.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        return true;
                    case MotionEvent.ACTION_UP:
                        if (isUpOrDown) {
                            if (Math.abs(scrodiffX) < 100) {
                                viewHolder.itemView.scrollTo(0, 0);
                                resultBean.setDel(false);
                            } else {
                                viewHolder.itemView.scrollTo(200, 0);
                                resultBean.setDel(true);
                            }
                        } else {
                            if (Math.abs(scrodiffX) < 100&&resultBean.isDel()) {
                                viewHolder.itemView.scrollTo(200, 0);
                                resultBean.setDel(true);
                            } else {
                                viewHolder.itemView.scrollTo(0, 0);
                                resultBean.setDel(false);
                            }
                        }
                        scrodiffX = 0;
                        return true;
                }
                return false;
            }
        });
    }
}
