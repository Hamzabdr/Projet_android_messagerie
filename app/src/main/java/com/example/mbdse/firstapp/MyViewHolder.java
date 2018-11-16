package com.example.mbdse.firstapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView txtv;
    public MyViewHolder(View itemView)
    {
        super(itemView);
        txtv = (TextView)itemView.findViewById(R.id.cellulite);
    }

}
