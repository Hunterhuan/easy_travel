package com.example.mrhan.maketravel.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mrhan.maketravel.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by Mr.Han on 2018/5/18.
 */

public class City_Adapter extends RecyclerView.Adapter<City_Adapter.MyHolder>{
    private LayoutInflater li;
    private List<String> citys= Collections.emptyList();
    public City_Adapter(Context context, List<String> citylist){
        citys = citylist;
        li = LayoutInflater.from(context);
    }
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v=li.inflate(R.layout.single_city, parent, false);
        final MyHolder myHolder = new MyHolder(v);
        myHolder.cityview.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int position = myHolder.getAdapterPosition();
                String tmp = citys.get(position);
                Toast.makeText(v.getContext(),"you click view "+tmp,Toast.LENGTH_SHORT).show();
            }
        });
        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder myHolder, int position){
        String p=citys.get(position);
        myHolder.city.setText(p);
    }
    @Override
    public int getItemCount(){
        return citys.size();
    }
    class MyHolder extends RecyclerView.ViewHolder{
        TextView city;
        View cityview;

        public MyHolder(View itemView){
            super(itemView);
            cityview=itemView;
            city = (TextView)itemView.findViewById(R.id.cityname);
        }
    }
}
