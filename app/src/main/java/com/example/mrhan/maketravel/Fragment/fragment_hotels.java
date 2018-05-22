package com.example.mrhan.maketravel.Fragment;

import android.animation.ObjectAnimator;
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
import com.example.mrhan.maketravel.R;

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
        List<String> hotels_name= new ArrayList<>();
        hotels_name.add("1");
        hotels_name.add("2");
        hotels_name.add("3");
        rv.setAdapter(new hotels_Adapter(rv.getContext(),hotels_name));

    }
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }

}
