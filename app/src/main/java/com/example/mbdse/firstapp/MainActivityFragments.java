package com.example.mbdse.firstapp;

import android.content.res.Configuration;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivityFragments extends AppCompatActivity implements ICallable{

    FrameLayout fl;
    FrameLayout fl1;
    EditText login;
    EditText mdp;

    boolean a = true;

    ContactsFragment maf = new ContactsFragment();
    MessagesFragment sec = new MessagesFragment();
    private TextAdapter mDbHelper;

    private Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mDbHelper = new TextAdapter(new ArrayList<String>());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fl = (FrameLayout) findViewById(R.id.fragmentHolder);
        fl1 = (FrameLayout) findViewById(R.id.fragmentHolder1);
        db = Database.getInstance(getApplicationContext());
        login = (EditText) findViewById(R.id.login_box);
        mdp = (EditText) findViewById(R.id.pass_box);
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)//double fragments
        {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(fl.getId(),maf);
            fragmentTransaction.replace(fl1.getId(),sec);
            fragmentTransaction.commit();}
            else {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(fl.getId(),maf);
            fragmentTransaction.commit();
        }
            return;
        }


    protected void onRestart(){
        super.onRestart();
       /* Toast.makeText(getApplicationContext(),"fragment actif",Toast.LENGTH_SHORT).show();
        mDbHelper.notifyDataSetChanged();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.show(maf);
*/
}
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                addData();
////                a= !a;
////                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
////                if (a)
////                {
////                    fragmentTransaction.replace(fl.getId(),maf);
////                } else {
////                    fragmentTransaction.replace(fl.getId(),sec);
////                }
////                fragmentTransaction.commit();
//            }
//        });



    @Override
    public void transferData(String s) {
        sec.setText(s);
    }


}

