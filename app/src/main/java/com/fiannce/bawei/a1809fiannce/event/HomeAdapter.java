package com.fiannce.bawei.a1809fiannce.event;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fiannce.bawei.a1809fiannce.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {
    private List<String> data = new ArrayList<>();
    private View lastItemView;
    private int lastPosition;
    private int scrollDiffX;

    public void update(List<String> stringList) {
        data.addAll(stringList);
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home,parent,false);
        return new HomeViewHolder(rootView);
    }

    private int lastX;

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {


        holder.tv.setText(data.get(position));
        holder.delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击删除按钮就隐藏该删除按钮
                lastItemView.scrollTo(0,0);
                lastItemView = null;
                lastPosition = -1;
            }
        });

        holder.itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        lastX = (int) event.getRawX();
                        //请求外部不要拦截事件，之后的滑动事件就可以收到了
                        holder.itemView.getParent().requestDisallowInterceptTouchEvent(true);
                        return true;//返回true代表的是消费该事件，如果返回false系统就不会把之后的事件返回了
                    case MotionEvent.ACTION_MOVE:
                        if (lastX>500 && event.getRawX()<lastX) {//判断如果是在右侧滑动，且是向左滑动，就请求外部不要拦截事件，否则就允许外部拦截事件
                            holder.itemView.getParent().requestDisallowInterceptTouchEvent(true);
                            if (lastItemView!=null&&position!=lastPosition) {//如果之前有删除按钮显示就先隐藏掉
                                lastItemView.scrollTo(0,0);
                            }
                            lastPosition = position;
                            lastItemView = holder.itemView;
                            scrollDiffX = -(int) ((event.getRawX()-lastX));//记住手指在X方向的滑动距离
                            holder.itemView.scrollTo(scrollDiffX,0);//让ItemView向做滑动
                            return true;//消费该事件
                        } else {
                            holder.itemView.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                }
                return false;//如果不是我们关注的事件，就不消费了
            }
        });
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class HomeViewHolder extends RecyclerView.ViewHolder {
        protected TextView tv;
        protected View itemView;
        protected Button delBtn;
        public HomeViewHolder(View rootView) {
            super(rootView);
            itemView = rootView;
            delBtn = rootView.findViewById(R.id.delBtn);
            tv = rootView.findViewById(R.id.itemTv);
        }
    }
}
