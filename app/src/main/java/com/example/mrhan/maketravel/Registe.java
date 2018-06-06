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
    EditText reg_user,reg_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registe);

        tb = (Toolbar)findViewById(R.id.reg_tb);
        setSupportActionBar(tb);
        tb.setTitleTextColor(Color.WHITE);

        reg_btn = (Button)findViewById(R.id.registe_btn);

        reg_user=(EditText)findViewById(R.id.registe_user);
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
                Snackbar.make(v ,str_user ,Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
