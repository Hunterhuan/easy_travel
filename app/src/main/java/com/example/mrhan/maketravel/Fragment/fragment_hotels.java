package com.example.mrhan.maketravel.Fragment;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.example.mrhan.maketravel.Adapter.hotels_Adapter;
import com.example.mrhan.maketravel.MainActivity;
import com.example.mrhan.maketravel.MyAlgorithm;
import com.example.mrhan.maketravel.R;
import com.example.mrhan.maketravel.travel_tab;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr.Han on 2018/5/21.
 */

public class fragment_hotels extends Fragment implements View.OnClickListener, View.OnTouchListener{

    RecyclerView rv;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        NestedScrollView nestedScrollView = (NestedScrollView) inflater.inflate(R.layout.fragment_hotels, container, false);
        rv = (RecyclerView) nestedScrollView.findViewById(R.id.recycler_hotel);
        return nestedScrollView;
    }
    @Override
    public void onClick(View view) {
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));

        List<String> recommand_hotels = new ArrayList<>();

        List<String> hotels_name= MainActivity.tst.getRecommendHotel("default");

        for(String obj : recommand_hotels){
            hotels_name.add(obj);
        }
        MainActivity.tst.decideHotel("上海徐汇瑞峰酒店");
        rv.setAdapter(new hotels_Adapter(rv.getContext(),hotels_name));
    }
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }
}
