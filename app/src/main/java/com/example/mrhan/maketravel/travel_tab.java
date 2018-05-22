package com.example.mrhan.maketravel;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.mrhan.maketravel.Adapter.FragmentAdapter;
import com.example.mrhan.maketravel.Adapter.spots_Adapter;
import com.example.mrhan.maketravel.Fragment.fragment_hotels;
import com.example.mrhan.maketravel.Fragment.fragment_spots;

import java.util.ArrayList;
import java.util.List;

public class travel_tab extends AppCompatActivity {

    ViewPager viewpager;
    TabLayout tl;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_tab);
        //fab = (FloatingActionButton) findViewById(R.id.start_bt);
        viewpager= (ViewPager) findViewById(R.id.vp);
        tl = (TabLayout)findViewById(R.id.tl);


        List<String> titles = new ArrayList<>();
        titles.add("景点");
        titles.add("酒店");
        tl.addTab(tl.newTab().setText(titles.get(0)));
        tl.addTab(tl.newTab().setText(titles.get(1)));

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new fragment_spots());
        fragments.add(new fragment_hotels());

        viewpager.setOffscreenPageLimit(0);

        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(),fragments,titles);
        viewpager.setAdapter(fragmentAdapter);
        tl.setupWithViewPager(viewpager);
    }
    };
