package com.example.mbdse.firstapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

public class AddContact extends Activity {
        Button Close;
        Button Create;
        EditText Name;
        Database db;
        TextAdapter adapter;
        /** Called when the activity is first created. */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.main);
            db = Database.getInstance(getApplicationContext());
            Name = (EditText) findViewById(R.id.login_box);
            Create = (Button) findViewById(R.id.button1);
            final Intent intent2 = new Intent(this, MainActivityFragments.class);
            Create.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
// TODO Auto-generated method stub
                    double res = db.addPerson(Name.getText().toString(), "");

                    if (res == -1)
                        Toast.makeText(getApplicationContext(),"Contact add failed",Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getApplicationContext(),"Contact add Succesful",Toast.LENGTH_SHORT).show();
                    startActivity(intent2);

                    //showPopup();
                    finish();
                }
            });
        }

        private PopupWindow pw;
        private void showPopup() {
            try {
// We need to get the instance of the LayoutInflater
                LayoutInflater inflater = (LayoutInflater) AddContact.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View layout = inflater.inflate(R.layout.popup,
                        (ViewGroup) findViewById(R.id.popup_1));
                pw = new PopupWindow(layout, 600, 400, true);
                pw.showAtLocation(layout, Gravity.CENTER, 0, 0);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

}
