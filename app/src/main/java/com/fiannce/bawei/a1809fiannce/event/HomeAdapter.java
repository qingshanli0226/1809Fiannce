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
                        //holder.itemView.getParent().requestDisallowInterceptTouchEvent(true);
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        if (lastX>500 && event.getRawX()<lastX) {
                            //holder.itemView.getParent().requestDisallowInterceptTouchEvent(true);
                            if (lastItemView!=null&&position!=lastPosition) {
                                lastItemView.scrollTo(0,0);
                            }
                            lastPosition = position;
                            lastItemView = holder.itemView;
                            scrollDiffX = -(int) ((event.getRawX()-lastX));
                            holder.itemView.scrollTo(scrollDiffX,0);
                            return true;
                        } else {
                            //holder.itemView.getParent().requestDisallowInterceptTouchEvent(false);
                        }

                        break;

                }

                return false;
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
