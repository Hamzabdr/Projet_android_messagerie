package com.example.mbdse.firstapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnItemTouchListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivityFragment extends Fragment implements ICallable {

    ICallable mCallback;
    TextAdapter mAdapter;
    RecyclerView recyclerView;
    ImageButton btn;
    Database db;

    List<String> strs = new ArrayList<String>();
    List<Person> prs = new ArrayList<Person>();
    PopUpDemo popup = new PopUpDemo();
    public MainActivityFragment() {
        mAdapter = new TextAdapter(strs);
    }

    @Override
    public void transferData(String s) {
        mCallback.transferData(s);
    }
@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //db.delPerson();
}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_main, container, false);
        btn = (ImageButton) v.findViewById(R.id.btnTxt);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity() ,PopUpDemo.class);
                startActivity(intent);
            }
        });
        //getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addOnItemTouchListener(new OnItemTouchListener() {

            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                View child = rv.findChildViewUnder(e.getX(), e.getY());
                int pos = rv.getChildAdapterPosition(child);
                if (pos >= 0)
                    transferData(mAdapter.getItemByIndex(pos));
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean b) {

            }

        });
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        db = Database.getInstance(context);
        prs = db.readPerson();
        for (Person p : prs) {
            strs.add(p.getNom()+" "+p.getPrenom());
        }
        mAdapter.notifyDataSetChanged();
        getFragmentManager().beginTransaction().detach(this).attach(this).commit();


        if (context instanceof ICallable) {
            mCallback = (ICallable) context;

        } else {
            throw new ClassCastException(context.toString() + " must implement iCallable");
        }
    }

    public void addData() {
        if(db.readPerson() == null) {
            long id = db.addPerson("Hamza", "Boudradar");
            db.addPerson("Mehdi", "Saad");
            db.addPerson("Aime", "Nikeka");
            db.addPerson("Grace  ", "Bocou");
            db.addPerson("Nadir", "ibegh");
            if (id == -1) {
                Toast.makeText(this.getContext(), "access failed", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(this.getContext(), "access success", Toast.LENGTH_SHORT).show();
            }
        }
    }


}
