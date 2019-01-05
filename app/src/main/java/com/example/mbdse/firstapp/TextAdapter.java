package com.example.mbdse.firstapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class TextAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private List<String> strs;

    public TextAdapter(List<String> strs) {
        this.strs = strs;
    }

        public String getItemByIndex(int i){
        return strs.get(i);
    }

    public void updateReceiptsList(List<String> newlist) {
        strs.clear();
        strs.addAll(newlist);
        this.notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cellule_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtv.setText(strs.get(position));

    }

    @Override
    public int getItemCount() {
        return strs.size();
    }



}