package com.example.mrhan.maketravel;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.mrhan.maketravel.Adapter.FragmentAdapter;
import com.example.mrhan.maketravel.Adapter.spots_Adapter;
import com.example.mrhan.maketravel.Fragment.fragment_hotels;
import com.example.mrhan.maketravel.Fragment.fragment_spots;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class travel_tab extends AppCompatActivity {

    ViewPager viewpager;
    TabLayout tl;
    FloatingActionButton fab;
    fragment_hotels fh;
    fragment_spots fs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_tab);

/*        AsyncTask<MyAlgorithm, Integer, MyAlgorithm > asyncTask = new AsyncTask<MyAlgorithm, Integer, MyAlgorithm >() {

            @Override
            protected MyAlgorithm doInBackground(MyAlgorithm[] params) {//此函数是在任务被线程池执行时调用 运行在子线程中，在此处理比较耗时的操作 比如执行下载
                MyAlgorithm tst = params[0];
                tst = new MyAlgorithm("上海");
                return tst;
            }
        };
        try {
            tst = asyncTask.execute(tst).get();
        } catch (Exception ex){
            ex.printStackTrace();
            tst = new MyAlgorithm("上海");
        }*/



        fab = (FloatingActionButton) findViewById(R.id.start_bt);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Snackbar.make(v,"floating button work",Snackbar.LENGTH_SHORT).show();
                List<String> route = MainActivity.tst.getRoute();
                ArrayList<ArrayList<String>> route_line = MainActivity.tst.get_route_in_line(route);
                if(MainActivity.userManager.getLoginStatus()){
                    MainActivity.userManager.saveRoute(route_line);
                }
                if(fh.decided==false){
                    Toast.makeText(v.getContext(),"您还没有选择居住地点，请长按选定！", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(travel_tab.this , result.class);
                    intent.putExtra("route", route_line);
                    startActivity(intent);
                }
            }
        });
        viewpager= (ViewPager) findViewById(R.id.vp);
        tl = (TabLayout)findViewById(R.id.tl);


        List<String> titles = new ArrayList<>();
        titles.add("景点");
        titles.add("酒店");
        tl.addTab(tl.newTab().setText(titles.get(0)));
        tl.addTab(tl.newTab().setText(titles.get(1)));

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(fs = new fragment_spots());
        fragments.add(fh = new fragment_hotels());

        viewpager.setOffscreenPageLimit(0);

        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(),fragments,titles);
        viewpager.setAdapter(fragmentAdapter);
        tl.setupWithViewPager(viewpager);
    }
    protected void onDestroy() {
        super.onDestroy();
        MainActivity.tst.clearall();
    }
};
