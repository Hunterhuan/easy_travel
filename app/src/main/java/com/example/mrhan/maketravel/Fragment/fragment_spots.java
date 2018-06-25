package com.example.mrhan.maketravel.Fragment;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
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
import android.widget.ImageView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.mrhan.maketravel.Adapter.City_Adapter;
import com.example.mrhan.maketravel.Adapter.spots_Adapter;
import com.example.mrhan.maketravel.Adapter.test_spots_adapter;
import com.example.mrhan.maketravel.MainActivity;
import com.example.mrhan.maketravel.MyAlgorithm;
import com.example.mrhan.maketravel.R;
import com.example.mrhan.maketravel.spots_detail;
import com.example.mrhan.maketravel.travel_tab;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.mrhan.maketravel.MainActivity.tst;

/**
 * Created by Mr.Han on 2018/5/21.
 */

public class fragment_spots extends Fragment implements View.OnClickListener, View.OnTouchListener{


    RecyclerView rv;
    RefreshLayout refreshLayout;
    private test_spots_adapter mAdapter;
    private List<String> spots_name;
    private Map<String,Boolean> map;
    private Button interest_bt, transportation_bt;
    String[] interesting = {"人文","自然"};
    boolean[] checkedItems ={true,true};

    String[] transportation={"驾车","公交","智能选择"};
    int itemSelected = 0;
    //private SwipeRefreshLayout swipeRefresh;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        CoordinatorLayout nestedScrollView = (CoordinatorLayout) inflater.inflate(R.layout.fragment_spots, container, false);
        rv = (RecyclerView) nestedScrollView.findViewById(R.id.recycler_spot);
        interest_bt = (Button)nestedScrollView.findViewById(R.id.interest_bt);
        transportation_bt = (Button)nestedScrollView.findViewById(R.id.transportation_bt);

        interest_bt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                new AlertDialog.Builder(getContext())
                        .setTitle("选择你的景点喜好")
                        .setMultiChoiceItems(interesting, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                                checkedItems[i] = b;
                            }
                        })
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                MainActivity.tst.addInterest(checkedItems);
                            }
                        })
                        .setNegativeButton("cancle",null)
                        .show();
            }
        });
        transportation_bt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                new AlertDialog.Builder(getContext())
                        .setTitle("选择你的交通工具")
                        .setSingleChoiceItems(transportation, itemSelected, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                MainActivity.tst.setTransportation(i);
                                itemSelected = i;
                                dialogInterface.dismiss();
                            }
                        })
                        .setNegativeButton("cancle",null)
                        .show();
            }
        });
        //rv.setNestedScrollingEnabled(false);

        map = new HashMap<String, Boolean>();

        refreshLayout =(RefreshLayout)nestedScrollView.findViewById(R.id.spot_refreshlayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(2000);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore(1000);
                List<String> tmp = tst.getRecommandScene();
                mAdapter.addData(tmp);
                for(String obj:tmp){
                    map.put(obj,false);
                }
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


        spots_name= new ArrayList<>();
        List<String> result = tst.getRecommandScene();
        //System.out.println(spots_name);
        for(String obj : result){
            spots_name.add(obj);
            map.put(obj,false);
        }

        mAdapter = new test_spots_adapter(R.layout.spot_cardview, spots_name);
        rv.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String tmp = mAdapter.getData().get(position);
                Intent intent = new Intent(view.getContext(),spots_detail.class);
                intent.putExtra("String_data",tmp);
                view.getContext().startActivity(intent);
            }
        });
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                String tmp = mAdapter.getData().get(position);
                ImageView img = (ImageView)adapter.getViewByPosition(rv, position, R.id.img_favorite);
                if(map.get(tmp)==false){
                    img.setImageResource(R.drawable.ic_favorite_black_24dp);
                    MainActivity.tst.addScene(tmp);
                    map.put(tmp,true);
                    Toast.makeText(view.getContext(), "你添加了景点：" + tmp, Toast.LENGTH_SHORT).show();
                }
                else{
                    MainActivity.tst.removeScene(tmp);
                    map.put(tmp,false);
                    Toast.makeText(view.getContext(), "你移除了景点：" + tmp, Toast.LENGTH_SHORT).show();
                    img.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                }

            }
        });
/*        tst.addScene("外滩");
        tst.addScene("上海杜莎夫人蜡像馆(南京西路)");
        tst.removeScene("外滩");
        tst.addScene("泰晤士小镇");
        tst.addScene("上海城隍庙");
        tst.addScene("外滩");*/

        //rv.setAdapter(mAdapter = new spots_Adapter(rv.getContext(),spots_name));

    }
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }

}
