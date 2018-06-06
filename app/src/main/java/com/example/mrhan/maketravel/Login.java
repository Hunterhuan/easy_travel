package com.example.mrhan.maketravel;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;


public class Login extends AppCompatActivity {
    private Toolbar tb;
    Button login_btn,reg_btn;
    EditText edit_user,edit_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tb = (Toolbar)findViewById(R.id.login_tb);
        setSupportActionBar(tb);
        tb.setTitleTextColor(Color.WHITE);

        login_btn = (Button)findViewById(R.id.login_btn);
        reg_btn = (Button)findViewById(R.id.reg_btn);

        edit_user=(EditText)findViewById(R.id.login_user);
        edit_password=(EditText)findViewById(R.id.login_password);

        final ActionBar supportActionBar = getSupportActionBar();
        if(supportActionBar !=null){
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        login_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String str_user=edit_user.getText().toString();
                String str_password=edit_password.getText().toString();
                Snackbar.make(v ,str_user ,Snackbar.LENGTH_SHORT).show();
            }
        });
        reg_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Login.this , Registe.class);
                startActivity(intent);
            }
        });
    }
}
