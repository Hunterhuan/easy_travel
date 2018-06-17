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
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import com.example.mrhan.maketravel.MainActivity;
import com.example.mrhan.maketravel.MyAlgorithm;
import com.example.mrhan.maketravel.R;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mrhan.maketravel.TravelDB;
import com.example.mrhan.maketravel.spots_detail;

import static java.security.AccessController.getContext;

/**
 * Created by Mr.Han on 2018/5/18.
 */

public class spots_Adapter extends RecyclerView.Adapter<spots_Adapter.MyHolder>{
    private LayoutInflater li;
    private List<String> citys= Collections.emptyList();
    private Context mcontext;
    private Map<String,Boolean> map;
    private AlphaAnimation alphaAnimation, alphaAnimationShowIcon;

    public spots_Adapter(Context context, List<String> citylist){
        citys = citylist;
        mcontext = context;
        li = LayoutInflater.from(context);
        map = new HashMap<String, Boolean>();
        for(String obj:citylist){
            map.put(obj,false);
        }
        alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(700);
        alphaAnimationShowIcon = new AlphaAnimation(0.2f, 1.0f);
        alphaAnimationShowIcon.setDuration(500);

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
                intent.putExtra("String_data",tmp);
                v.getContext().startActivity(intent);
            }
        });
        myHolder.fav_bt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int position = myHolder.getAdapterPosition();
                String tmp = citys.get(position);
                if(map.get(tmp)==false){
                    myHolder.fav_bt.setImageResource(R.drawable.ic_favorite_black_24dp);
                    myHolder.fav_bt.startAnimation(alphaAnimationShowIcon);
                    myHolder.fav_bt.startAnimation(alphaAnimationShowIcon);
                    MainActivity.tst.addScene(tmp);
                    Toast.makeText(v.getContext(),"you add scene "+tmp,Toast.LENGTH_SHORT).show();
                    map.put(tmp,true);
                }
                else
                {
                    myHolder.fav_bt.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                    myHolder.fav_bt.startAnimation(alphaAnimationShowIcon);
                    myHolder.fav_bt.startAnimation(alphaAnimationShowIcon);
                    MainActivity.tst.removeScene(tmp);
                    Toast.makeText(v.getContext(),"you remove scene "+tmp,Toast.LENGTH_SHORT).show();
                    map.put(tmp,false);
                }

            }
        });
        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder myHolder, int position){
        String p=citys.get(position);
        myHolder.city.setText(p);
        Glide.with(mcontext).load(MainActivity.tst.db.getImage(p)).apply(new RequestOptions().fitCenter()).into(myHolder.image);

    }

    @Override
    public int getItemCount(){
        return citys.size();
    }
    class MyHolder extends RecyclerView.ViewHolder{
        TextView city;
        ImageView image, fav_bt;
        View cityview;


        public MyHolder(View itemView){
            super(itemView);
            cityview=itemView;
            city = (TextView)itemView.findViewById(R.id.spotname);
            image = (ImageView)itemView.findViewById(R.id.img_spot);
            fav_bt = (ImageView)itemView.findViewById(R.id.img_favorite);
        }
    }

    public spots_Adapter loadMore(Collection<String> collection){
        if(collection.size()==0)
            return this;
        citys.addAll(collection);
        notifyDataSetChanged();
        return this;
    }
}
