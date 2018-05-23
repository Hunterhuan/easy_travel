package com.example.mrhan.maketravel.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mrhan.maketravel.R;

import java.util.Collections;
import java.util.List;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mrhan.maketravel.spots_detail;

import static java.security.AccessController.getContext;

/**
 * Created by Mr.Han on 2018/5/18.
 */

public class spots_Adapter extends RecyclerView.Adapter<spots_Adapter.MyHolder>{
    private LayoutInflater li;
    private List<String> citys= Collections.emptyList();
    private Context mcontext;
    public spots_Adapter(Context context, List<String> citylist){
        citys = citylist;
        mcontext = context;
        li = LayoutInflater.from(context);
    }
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v=li.inflate(R.layout.spot_cardview, parent, false);
        final MyHolder myHolder = new MyHolder(v);
        myHolder.cityview.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int position = myHolder.getAdapterPosition();
                String tmp = citys.get(position);
                Toast.makeText(v.getContext(),"you click view "+tmp,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(),spots_detail.class);
                v.getContext().startActivity(intent);
            }
        });
        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder myHolder, int position){
        String p=citys.get(position);
        myHolder.city.setText(p);
        Glide.with(mcontext).load(R.drawable.material_design_4).apply(new RequestOptions().fitCenter()).into(myHolder.image);
    }
    @Override
    public int getItemCount(){
        return citys.size();
    }
    class MyHolder extends RecyclerView.ViewHolder{
        TextView city;
        ImageView image;
        View cityview;

        public MyHolder(View itemView){
            super(itemView);
            cityview=itemView;
            city = (TextView)itemView.findViewById(R.id.spotname);
            image = (ImageView)itemView.findViewById(R.id.img_spot);
        }
    }
}
