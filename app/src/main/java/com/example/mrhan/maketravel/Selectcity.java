package com.example.mrhan.maketravel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.mrhan.maketravel.Adapter.City_Adapter;

import java.util.ArrayList;
import java.util.List;

public class Selectcity extends AppCompatActivity {
    private RecyclerView rv;
    private Toolbar tb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.citys);
        tb=(Toolbar)findViewById(R.id.city_tb);
        rv=(RecyclerView)findViewById(R.id.city_recycler);
        setSupportActionBar(tb);
        List<String> city_name= new ArrayList<>();
        city_name.add("上海");
        city_name.add("北京");
        city_name.add("昆明");
        City_Adapter city_adapter=new City_Adapter(this,city_name);
        rv.setAdapter(city_adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }
}
