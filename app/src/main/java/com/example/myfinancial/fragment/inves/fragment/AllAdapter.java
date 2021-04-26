package com.example.myfinancial.fragment.inves.fragment;


import android.graphics.Color;
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

    private View lastitemView;
    public AllAdapter( @Nullable List<AllMoneyBean.ResultBean> data) {
        super(R.layout.all_rec_lay, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder viewHolder, AllMoneyBean.ResultBean resultBean) {
        viewHolder.setText(R.id.allonemoney,resultBean.getMoney());
        viewHolder.setText(R.id.alltitle,resultBean.getName());
        viewHolder.setText(R.id.allonenum,resultBean.getMinTouMoney());
        viewHolder.setText(R.id.alltwopercentage,resultBean.getYearRate());
        viewHolder.setText(R.id.allthreeday,resultBean.getSuodingDays());
        viewHolder.setText(R.id.allthreenum,resultBean.getMemberNum()+"");
         PregressMyView pregressMyView = viewHolder.getView(R.id.allmyview);
         pregressMyView.getProgressNum(Integer.parseInt(resultBean.getProgress()),false);
         pregressMyView.settextColor(Color.BLUE);
         pregressMyView.setCirCleWidth(10);



        viewHolder.itemView.setOnTouchListener(new View.OnTouchListener(){
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 switch (event.getAction()){
                     case MotionEvent.ACTION_DOWN:
                         lastX= (int) event.getX();
                         break;
                     case MotionEvent.ACTION_MOVE:
                         if (lastX>500&&event.getRawX()<lastX){
                             if (lastitemView!=null&&lastposition!=position){
                                 lastitemView.scrollTo(0,0);//回去
                             }
                             lastposition=position;
                             lastitemView=viewHolder.itemView;

                         }
                         break;
                 }
                 return false;
             }
         });
    }
}
