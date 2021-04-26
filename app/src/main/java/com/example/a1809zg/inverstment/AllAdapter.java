package com.example.a1809zg.inverstment;

import android.view.MotionEvent;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.a1809zg.myview.MyView;
import com.example.a1809zg.R;
import com.example.net.bean.ProductBean;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AllAdapter extends BaseQuickAdapter<ProductBean.ResultBean, BaseViewHolder> {
    private int lastX;
    private int lastposition;
    private View lastitemView;
    private int position;
    private int remenX;
    public AllAdapter(@Nullable List<ProductBean.ResultBean> data) {
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
       addChildClickViewIds(R.id.abb);

        View view1 = baseViewHolder.findView(R.id.abb);
        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastitemView.scrollTo(0,0);
                lastitemView=null;
                lastposition=-1;
                remove(baseViewHolder.getPosition());
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
                             remenX= -(int) ((event.getRawX()-lastX));
                            baseViewHolder.itemView.scrollTo(remenX,0);
                            if (remenX>=230){
                                baseViewHolder.itemView.scrollTo(230,0);
                            }


                            return true;
                        }else {
                            baseViewHolder.itemView.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        if (lastX>500&&event.getRawX()>lastX){
                            baseViewHolder.itemView.getParent().requestDisallowInterceptTouchEvent(true);
                            remenX= (int) ((lastX-event.getRawX()));
                            if (Math.abs(remenX)>100){
                                baseViewHolder.itemView.scrollTo(0,0);
                            }

                        }
                    case MotionEvent.ACTION_UP:
                        baseViewHolder.itemView.getParent().requestDisallowInterceptTouchEvent(true);
                        if (remenX>115){
                            baseViewHolder.itemView.scrollTo(230,0);

                        }else {
                            baseViewHolder.itemView.scrollTo(0,0);
                        }
                        return true;

                }
                return false;
            }
        });
    }
}
