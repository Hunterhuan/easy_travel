package com.example.mrhan.maketravel;

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


public class Registe extends AppCompatActivity {
    private Toolbar tb;
    Button reg_btn;
    EditText reg_user,reg_password,reg_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registe);

        tb = (Toolbar)findViewById(R.id.reg_tb);
        setSupportActionBar(tb);
        tb.setTitleTextColor(Color.WHITE);

        reg_btn = (Button)findViewById(R.id.registe_btn);
        reg_user=(EditText)findViewById(R.id.registe_user);
        reg_email=(EditText)findViewById(R.id.registe_email);
        reg_password=(EditText)findViewById(R.id.registe_password);

        final ActionBar supportActionBar = getSupportActionBar();
        if(supportActionBar !=null){
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        reg_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String str_user=reg_user.getText().toString();
                String str_password=reg_password.getText().toString();
                String str_email=reg_password.getText().toString();
                boolean flag = MainActivity.userManager.register(str_email,str_password,str_user);
                if(flag){
                    MainActivity.userManager.login(str_email,str_password);
                    Snackbar.make(v ,"注册成功，已经为您自动登陆，即将返回主页" ,Snackbar.LENGTH_SHORT).show();
                    finish();
                }
                else{
                    Snackbar.make(v ,"注册失败，请重试" ,Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }
}
