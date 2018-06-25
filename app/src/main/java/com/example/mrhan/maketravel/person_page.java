package com.example.mrhan.maketravel;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.flyco.roundview.RoundTextView;


public class person_page extends AppCompatActivity {
    LinearLayout ll;
    RoundTextView rt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_page);
        ll = (LinearLayout) findViewById(R.id.show_my_travel);
        rt = (RoundTextView)findViewById(R.id.edit_info_btn);

        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view ,"展示我的所有行程" ,Snackbar.LENGTH_SHORT).show();
            }
        });
        rt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(person_page.this , Edit_person_info.class);
                startActivity(intent);
                Snackbar.make(view ,"修改我的信息" ,Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
