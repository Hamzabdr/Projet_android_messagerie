package com.example.mbdse.firstapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mbdse.firstapp.service.MessageService;
import com.example.mbdse.firstapp.service.RetrofitClient;
import com.example.mbdse.firstapp.service.ServiceApi;

public class ConnexionActivity extends AppCompatActivity {

    EditText loginBox;
    EditText passBox;
    Button validBtn;
    Button registerBtn;
    Database db;
    private ServiceApi mService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        loginBox = (EditText) findViewById(R.id.login_box);
        passBox = (EditText) findViewById(R.id.pass_box);
        validBtn = (Button) findViewById(R.id.valid_btn);
        registerBtn = (Button) findViewById(R.id.register_btn);
        mService = RetrofitClient.getServiceAPI();

        db = Database.getInstance(getApplicationContext());
        final Intent intent = new Intent(this, RegisterActivity.class);
        final Intent intent2 = new Intent(this, MainActivityFragments.class);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("Login", "hamza");
        editor.putString("Password", "hamza");
        editor.commit();
        String name = preferences.getString("Login", "");
        final String password = preferences.getString("Password", "");
        loginBox.setText(name);
        passBox.setText(password);
        //startService(new Intent(this, MessageService.class));
        validBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("Login", loginBox.getText().toString());
                editor.putString("Password", passBox.getText().toString());
                editor.commit();

                if (!(db.checkloginpass(loginBox.getText().toString(), passBox.getText().toString())))
                    startActivity(intent2);
            }
        });
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });

    }
}
