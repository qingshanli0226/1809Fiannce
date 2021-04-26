package com.example.a1809zg.myview;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a1809zg.R;

public class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView content;
    public TextView delete;
    public LinearLayout layout;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        content = (TextView) itemView.findViewById(R.id.item_content);
        delete = (TextView) itemView.findViewById(R.id.item_delete);
        layout = (LinearLayout) itemView.findViewById(R.id.item_layout);
    }
}
