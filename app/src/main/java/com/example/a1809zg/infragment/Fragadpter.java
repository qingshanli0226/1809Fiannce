package com.example.a1809zg.infragment;

import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.a1809zg.myview.MyView;
import com.example.a1809zg.R;
import com.example.net.bean.ProductBean;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Fragadpter extends BaseQuickAdapter<ProductBean.ResultBean, BaseViewHolder> {
    private int lastX;
    private int lastposition;
    private View lastitemView;
    private int position;
    public Fragadpter( @Nullable List<ProductBean.ResultBean> data) {
        super(R.layout.item_money_view, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, ProductBean.ResultBean resultBean) {
       baseViewHolder.setText(R.id.text,resultBean.getName());
       baseViewHolder.setText(R.id.money,resultBean.getMoney()+"元");
       baseViewHolder.setText(R.id.year,resultBean.getYearRate()+"%");
       baseViewHolder.setText(R.id.day,resultBean.getSuodingDays()+"天");
       baseViewHolder.setText(R.id.food,resultBean.getMinTouMoney());
       baseViewHolder.setText(R.id.people,resultBean.getMemberNum());

        View view1 = baseViewHolder.findView(R.id.abb);
        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastitemView.scrollTo(0,0);
                lastitemView=null;
                lastposition=-1;
            }
        });



        MyView view = baseViewHolder.findView(R.id.aaa);
        int i = Integer.parseInt(resultBean.getProgress());
        view.saprogress(i,false);

        baseViewHolder.itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        lastX= (int) event.getRawX();
                        baseViewHolder.itemView.getParent().requestDisallowInterceptTouchEvent(true);

                        return true;
                    case MotionEvent.ACTION_MOVE:
                        if (lastX>500&&event.getRawX()<lastX){
                            baseViewHolder.itemView.getParent().requestDisallowInterceptTouchEvent(true);
                            if (lastitemView!=null&&baseViewHolder.getPosition()!=lastposition){
                                lastitemView.scrollTo(0,0);
                            }
                            lastposition=position;
                            lastitemView=baseViewHolder.itemView;
                            int remenX= -(int) ((event.getRawX()-lastX));
                            baseViewHolder.itemView.scrollTo(remenX,0);

                            return true;
                        }else {
                            baseViewHolder.itemView.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                }
                return false;
            }
        });
    }
}
