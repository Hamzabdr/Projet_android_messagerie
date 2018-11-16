package com.example.mbdse.firstapp;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

public class PopUpDemo extends Activity {
        Button Close;
        Button Create;

        /** Called when the activity is first created. */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.main);
            Create = (Button) findViewById(R.id.button1);
            Create.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
// TODO Auto-generated method stub
                    showPopup();
                }
            });
        }

        private PopupWindow pw;
        private void showPopup() {
            try {
// We need to get the instance of the LayoutInflater
                LayoutInflater inflater = (LayoutInflater) PopUpDemo.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View layout = inflater.inflate(R.layout.popup,
                        (ViewGroup) findViewById(R.id.popup_1));
                pw = new PopupWindow(layout, 300, 370, true);
                pw.showAtLocation(layout, Gravity.CENTER, 0, 0);
                Close = (Button) layout.findViewById(R.id.close_popup);
                Close.setOnClickListener(cancel_button);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private View.OnClickListener cancel_button = new View.OnClickListener() {
            public void onClick(View v) {
                pw.dismiss();
            }
        };

}
