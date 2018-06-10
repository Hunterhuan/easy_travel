package com.example.mrhan.maketravel.Fragment;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.example.mrhan.maketravel.Adapter.City_Adapter;
import com.example.mrhan.maketravel.Adapter.spots_Adapter;
import com.example.mrhan.maketravel.MainActivity;
import com.example.mrhan.maketravel.MyAlgorithm;
import com.example.mrhan.maketravel.R;
import com.example.mrhan.maketravel.travel_tab;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.mrhan.maketravel.MainActivity.tst;

/**
 * Created by Mr.Han on 2018/5/21.
 */

public class fragment_spots extends Fragment implements View.OnClickListener, View.OnTouchListener{


    RecyclerView rv;
    RefreshLayout refreshLayout;
    //private SwipeRefreshLayout swipeRefresh;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        NestedScrollView nestedScrollView = (NestedScrollView) inflater.inflate(R.layout.fragment_spots, container, false);
        rv = (RecyclerView) nestedScrollView.findViewById(R.id.recycler_spot);
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
                refreshLayout.finishLoadMore(2000);
            }
        });
/*        swipeRefresh = (SwipeRefreshLayout)nestedScrollView.findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });*/
        return nestedScrollView;
    }


    @Override
    public void onClick(View view) {
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));






/*        AsyncTask<MyAlgorithm, Integer, List<String> > asyncTask = new AsyncTask<MyAlgorithm, Integer, List<String> >() {

            @Override
            protected List<String> doInBackground(MyAlgorithm[] params) {//此函数是在任务被线程池执行时调用 运行在子线程中，在此处理比较耗时的操作 比如执行下载
                MyAlgorithm tst = params[0];
                List<String> result = tst.getRecommandScene();

                return result;
            }
        };

        List<String> result;
        try {
            result = asyncTask.execute(tst).get();
            result.add("lajilaji");
            System.out.println(result);
        } catch (Exception ex){
            ex.printStackTrace();
            result = new ArrayList<>();
            result.add("laji");
        }*/


        List<String> spots_name= new ArrayList<>();
        List<String> result = tst.getRecommandScene();
        //System.out.println(spots_name);
        for(String obj : result){
            spots_name.add(obj);
        }
/*        tst.addScene("外滩");
        tst.addScene("上海杜莎夫人蜡像馆(南京西路)");
        tst.removeScene("外滩");
        tst.addScene("泰晤士小镇");
        tst.addScene("上海城隍庙");
        tst.addScene("外滩");*/

        rv.setAdapter(new spots_Adapter(rv.getContext(),spots_name));

    }
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }

}
