package com.example.mrhan.maketravel;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Edit_person_info extends AppCompatActivity {

    Button submit_btn;
    EditText edit_name, edit_code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_person_info);
        submit_btn = (Button) findViewById(R.id.change_info_btn);
        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(view,edit_name.getText().toString()+edit_code.getText().toString()+" 修改信息成功",Snackbar.LENGTH_SHORT).show();
            }
        });
        edit_name = (EditText)findViewById(R.id.edit_nickname);
        edit_code = (EditText)findViewById(R.id.edit_code);
    }
}