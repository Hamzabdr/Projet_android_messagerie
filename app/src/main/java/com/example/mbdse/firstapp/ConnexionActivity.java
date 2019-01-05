package com.example.mbdse.firstapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ConnexionActivity extends AppCompatActivity {

    EditText loginBox;
    EditText passBox;
    Button validBtn;
    Button registerBtn;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        loginBox = (EditText) findViewById(R.id.login_box);
        passBox = (EditText) findViewById(R.id.pass_box);
        validBtn = (Button) findViewById(R.id.valid_btn);
        registerBtn = (Button) findViewById(R.id.register_btn);
        db = Database.getInstance(getApplicationContext());
        final Intent intent = new Intent(this, RegisterActivity.class);
        final Intent intent2 = new Intent(this, MainActivityFragments.class);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("Login","hamza");
        editor.putString("Password","hamza");
        editor.commit();
        String name = preferences.getString("Login","");
        String password = preferences.getString("Password","");
        loginBox.setText(name);
        passBox.setText(password);
        validBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("Login","hamza");
                editor.putString("Password","hamza");

                editor.commit();
        if ( !(db.checkloginpass(loginBox.getText().toString(),passBox.getText().toString())))
                startActivity(intent2);
            }
        });
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });

//        TextWatcher tw = new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                login();
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//            }
//        };
//
//
//        passBox.addTextChangedListener(tw);
//
//        loginBox.addTextChangedListener(tw);
    }
}
