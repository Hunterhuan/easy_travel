package com.example.mrhan.maketravel.Fragment;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;

import com.example.mrhan.maketravel.Adapter.hotels_Adapter;
import com.example.mrhan.maketravel.MainActivity;
import com.example.mrhan.maketravel.MyAlgorithm;
import com.example.mrhan.maketravel.R;
import com.example.mrhan.maketravel.travel_tab;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr.Han on 2018/5/21.
 */

public class fragment_hotels extends Fragment implements View.OnClickListener, View.OnTouchListener{

    RecyclerView rv;
    RefreshLayout refreshLayout;
    private Button sort_bt;
    String[] sorts = {"综合","价格","评分","距离"};
    int itemSelected = 0;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        CoordinatorLayout nestedScrollView = (CoordinatorLayout) inflater.inflate(R.layout.fragment_hotels, container, false);
        rv = (RecyclerView) nestedScrollView.findViewById(R.id.recycler_hotel);
        sort_bt = (Button) nestedScrollView.findViewById(R.id.sort_bt);
        sort_bt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                new AlertDialog.Builder(getContext())
                        .setTitle("选择排序方式")
                        .setSingleChoiceItems(sorts, itemSelected, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                dialogInterface.dismiss();
                            }
                        })
                        .setNegativeButton("cancle",null)
                        .show();
            }
        });
        refreshLayout =(RefreshLayout)nestedScrollView.findViewById(R.id.hotel_refreshlayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(2000);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore(2000);
            }
        });
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

        List<String> hotels_name= MainActivity.tst.getRecommendHotel(4);

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
