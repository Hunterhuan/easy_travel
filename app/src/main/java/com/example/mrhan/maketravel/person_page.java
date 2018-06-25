package com.example.mrhan.maketravel;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.roundview.RoundTextView;


public class person_page extends AppCompatActivity {
    LinearLayout ll;
    RoundTextView rt;
    TextView t1,t2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_page);
        ll = (LinearLayout) findViewById(R.id.show_my_travel);

        t1 = (TextView)findViewById(R.id.person_page_name);
        t2 = (TextView)findViewById(R.id.person_page_mail);

        rt = (RoundTextView)findViewById(R.id.edit_info_btn);

        t1.setText(MainActivity.userManager.getUsername());
        t2.setText(MainActivity.userManager.getEmail());

        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(person_page.this, my_route.class);
                startActivity(intent);
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

    @Override
    protected void onRestart(){
        super.onRestart();
        t1.setText(MainActivity.userManager.getUsername());
        t2.setText(MainActivity.userManager.getEmail());
    }
}
