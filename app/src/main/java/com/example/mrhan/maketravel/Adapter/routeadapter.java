package com.example.mrhan.maketravel.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mrhan.maketravel.MainActivity;
import com.example.mrhan.maketravel.R;
import com.example.mrhan.maketravel.Route;
import com.example.mrhan.maketravel.SelectDate;
import com.example.mrhan.maketravel.result;
import com.example.mrhan.maketravel.travel_tab;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Mr.Han on 2018/6/25.
 */

public class routeadapter extends RecyclerView.Adapter<routeadapter.MyHolder>{
    private LayoutInflater li;
    private ArrayList<Route> route_list;
    public routeadapter(Context context, ArrayList<Route> citylist){
        route_list = citylist;
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
                ArrayList<ArrayList<String>> tmp = route_list.get(position).getList();
                Intent intent = new Intent(v.getContext() , result.class);
                intent.putExtra("route", tmp);
                v.getContext().startActivity(intent);
                //Toast.makeText(v.getContext(),"you click view "+tmp,Toast.LENGTH_SHORT).show();
            }
        });
        myHolder.cityview.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                int position = myHolder.getAdapterPosition();
                removeData(position);
                return true;
            }
        });
        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder myHolder, int position){
        String p=route_list.get(position).getRouteId();
        myHolder.city.setText(p);
    }
    @Override
    public int getItemCount(){
        return route_list.size();
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
    public void removeData(int position){
        MainActivity.userManager.deleteRoute(route_list.get(position).getRouteId());
        route_list.remove(position);
        notifyItemChanged(position);
        notifyDataSetChanged();
    }
}
