package com.example.mrhan.maketravel.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mrhan.maketravel.R;
import com.example.mrhan.maketravel.SelectDate;

import java.util.Collections;
import java.util.List;

/**
 * Created by Mr.Han on 2018/6/25.
 */

public class routeadapter extends RecyclerView.Adapter<routeadapter.MyHolder>{
    private LayoutInflater li;
    private List<String> citys= Collections.emptyList();
    public routeadapter(Context context, List<String> citylist){
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
                removeData(position);
/*                String tmp = citys.get(position);
                Intent intent = new Intent(v.getContext(), SelectDate.class);
                intent.putExtra("cityname",tmp);
                v.getContext().startActivity(intent);*/
                //Toast.makeText(v.getContext(),"you click view "+tmp,Toast.LENGTH_SHORT).show();
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
    public void removeData(int position){
        citys.remove(position);
        notifyItemChanged(position);
        notifyDataSetChanged();
    }
}
