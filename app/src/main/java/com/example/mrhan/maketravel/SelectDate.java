package com.example.mrhan.maketravel;

import android.app.DatePickerDialog;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import java.text.DateFormat;
import java.util.Calendar;

public class SelectDate extends AppCompatActivity {
    Button date_go_btn, date_back_btn;
    Calendar calendar_go,calendar_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_date);
        calendar_go = Calendar.getInstance();
        calendar_back = Calendar.getInstance();
        date_go_btn = (Button) findViewById(R.id.date_go_button);
        date_back_btn=(Button) findViewById(R.id.date_back_button);
        date_go_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DatePickerDialog datePickerDialog = new DatePickerDialog(SelectDate.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        calendar_go.set(Calendar.YEAR, year);
                        calendar_go.set(Calendar.MONTH, monthOfYear);
                        calendar_go.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        String date = DateFormat.getDateInstance(DateFormat.MEDIUM).format(calendar_go.getTime());
                        date_go_btn.setText("出发日期"+date);
                    }
                }, calendar_go.get(Calendar.YEAR), calendar_go.get(Calendar.MONTH), calendar_go.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });
        date_back_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DatePickerDialog datePickerDialog = new DatePickerDialog(SelectDate.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        calendar_back.set(Calendar.YEAR, year);
                        calendar_back.set(Calendar.MONTH, monthOfYear);
                        calendar_back.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        String date = DateFormat.getDateInstance(DateFormat.MEDIUM).format(calendar_back.getTime());
                        date_back_btn.setText("返回日期"+date);
                    }
                }, calendar_back.get(Calendar.YEAR), calendar_back.get(Calendar.MONTH), calendar_back.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });
    }
}
