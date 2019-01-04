package com.example.mbdse.firstapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SecActivityFragment extends Fragment {
    public SecActivityFragment(){}
    TextView tv;
    String txt;

    @Override
    public void onResume() {
        super.onResume();
        tv.setText(txt);

    }

    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container, Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_sec,container, false);
        tv = (TextView) v.findViewById(R.id.TXT);
        return v;
    }
    public void setText(String txt)
    {
        this.txt= txt;
        if (tv !=null)
            tv.setText(txt);
    }




}