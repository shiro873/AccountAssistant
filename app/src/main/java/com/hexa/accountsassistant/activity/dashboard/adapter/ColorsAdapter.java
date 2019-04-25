package com.hexa.accountsassistant.activity.dashboard.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hexa.accountsassistant.R;
import com.hexa.accountsassistant.db.model.AccountHeads;

import java.util.List;

public class ColorsAdapter extends RecyclerView.Adapter<ColorsAdapter.ColorViewHolder> {
    private List<AccountHeads> list;

    public ColorsAdapter(List<AccountHeads> headsList){
        this.list = headsList;
    }

    @NonNull
    @Override
    public ColorViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_color, viewGroup, false);
        return new ColorViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ColorViewHolder colorViewHolder, int i) {
        AccountHeads heads = list.get(i);
        colorViewHolder.head.setText(heads.getHeadName());
        colorViewHolder.color.setBackgroundColor(heads.getColorCode());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ColorViewHolder extends RecyclerView.ViewHolder{
        LinearLayout color;
        TextView head;

        public ColorViewHolder(@NonNull View itemView) {
            super(itemView);
            color = itemView.findViewById(R.id.color);
            head = itemView.findViewById(R.id.head);
        }
    }
}
