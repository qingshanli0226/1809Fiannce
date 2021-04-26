package com.example.fiannce.adapter;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.fiannce.R;
import com.example.fiannce.custom_view.CustomView_HomeFragment;
import com.example.net.mode.AllBean;

import java.util.ArrayList;
import java.util.List;

public class AllAdapter_AllFragment extends BaseQuickAdapter<AllBean.ResultBean, BaseViewHolder> {

    private int lastPosition;
    private int Scx;
    private View lastView;
    private int Screen;
    private int maxWidth;

    public AllAdapter_AllFragment(int layoutResId, @Nullable List<AllBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AllBean.ResultBean item) {
        helper.setText(R.id.tit,item.getName()+"");
        helper.setText(R.id.money,item.getMoney()+"万");
        helper.setText(R.id.Year,item.getYearRate()+"%");
        helper.setText(R.id.date,item.getSuodingDays()+"");
        helper.setText(R.id.TouMoney,item.getMinTouMoney()+"");
        helper.setText(R.id.num,item.getMemberNum()+"");

        Button remBtn = helper.getView(R.id.remBtn);
        CustomView_HomeFragment cusView = helper.getView(R.id.cusView);

        int progress = Integer.parseInt(item.getProgress());
        int position = helper.getPosition();

        remBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastView.scrollTo(0,0);
                lastView=null;
                lastPosition=-1;
                remove(position);
            }
        });

        cusView.SealedProgress(progress,false);


        helper.itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){

                    case MotionEvent.ACTION_DOWN:
                        Scx= (int) event.getRawX();
                        helper.itemView.getParent().requestDisallowInterceptTouchEvent(true);
                        maxWidth = remBtn.getMeasuredWidth();

                        return true;
                    case MotionEvent.ACTION_MOVE:
                        if (Scx>500 && event.getRawX()<Scx){
                            helper.itemView.getParent().requestDisallowInterceptTouchEvent(true);
                            if (lastView!=null&&lastPosition!=position){
                                lastView.scrollTo(0,0);
                            }

                            lastView=helper.itemView;
                            lastPosition=position;
//                            if (length/2>X){
//                                Toast.makeText(mContext, "大于", Toast.LENGTH_SHORT).show();
//                                lastView.scrollTo(length,0);
//                            }else {
//                                Toast.makeText(mContext, "小于", Toast.LENGTH_SHORT).show();
//                                lastView.scrollTo(0,0);
//                            }
                            Screen = - (int) (event.getRawX() - Scx);
                            helper.itemView.scrollTo(Screen,0);

                            return true;
                        }else {
                            helper.itemView.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        if (Screen<maxWidth/2){
                            lastView=null;
                            lastPosition=-1;
                            helper.itemView.scrollTo(0,0);
                        }else {
                            helper.itemView.scrollTo(maxWidth,0);
                        }

                        break;
                }
                return false;
            }
        });
    }


}