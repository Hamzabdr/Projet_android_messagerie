package com.example.mbdse.firstapp;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText loginBox;
    EditText nameBox;
    EditText passBox;
    Button inscrBtn;
    Button retourBtn;
    Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db = Database.getInstance(getApplicationContext());
        loginBox = (EditText) findViewById(R.id.login_box);
        nameBox = (EditText) findViewById(R.id.name_box);
        passBox = (EditText) findViewById(R.id.pass_box);
        inscrBtn = (Button) findViewById(R.id.inscr_btn);
        retourBtn = (Button) findViewById(R.id.retour_btn);
        final Intent intent = new Intent(this,FirstClass.class);
        retourBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        inscrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = loginBox.getText().toString();
                String pass = passBox.getText().toString();
                String name = nameBox.getText().toString();
                if (db.cheklogin(login) == true){
                    long insert = db.addUser(login,name,pass);
                    if (insert == -1 ){
                        Toast.makeText(getApplicationContext(),"Login Already exists ! ",Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(),"Registred Succesfully !",Toast.LENGTH_SHORT).show();
                    loginBox.setText("");
                    passBox.setText("");
                    nameBox.setText("");
                    finish();
                    //Fragment currentFragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.container);

                }
            }
        });
    }

}
