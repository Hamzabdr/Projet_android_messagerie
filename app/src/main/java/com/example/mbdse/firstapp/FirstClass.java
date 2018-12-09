package com.example.mbdse.firstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FirstClass extends AppCompatActivity {

    EditText loginBox;
    EditText passBox;
    Button validBtn;
    Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        loginBox = (EditText) findViewById(R.id.login_box);
        passBox = (EditText) findViewById(R.id.pass_box);
        validBtn = (Button) findViewById(R.id.valid_btn);
        registerBtn = (Button) findViewById(R.id.register_btn);
        final Intent intent = new Intent(this, RegisterActivity.class);
        final Intent intent2 = new Intent(this, MainActivity.class);

        validBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
