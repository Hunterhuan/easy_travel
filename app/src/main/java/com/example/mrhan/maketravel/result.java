package com.example.mrhan.maketravel;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.os.Bundle;

import com.baoyachi.stepview.VerticalStepView;

import java.util.ArrayList;
import java.util.List;

public class result extends AppCompatActivity {
    private VerticalStepView mSetpview;
    private FloatingActionButton fab;
    private ArrayList<ArrayList<String>> route;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        mSetpview = (VerticalStepView) findViewById(R.id.step_view);
        route = (ArrayList<ArrayList<String>>) getIntent().getSerializableExtra("route");

        ArrayList<String> route2 = new ArrayList<String>();
        route2.add("预计天数"+route.get(0).get(0)+"天              "+"预计花费"+route.get(0).get(1)+"元");
        for(String obj: route.get(0)){
            route2.add(obj);
        }

        List<String> list0 = new ArrayList<>();
        for(String obj:route2){
            list0.add(obj);
        }

        mSetpview.setStepsViewIndicatorComplectingPosition(list0.size() - 2)//设置完成的步数
                .reverseDraw(false)//default is true
                .setStepViewTexts(list0)//总步骤
                .setLinePaddingProportion(0.85f)//设置indicator线与线间距的比例系数
                .setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(this, android.R.color.white))//设置StepsViewIndicator完成线的颜色
                .setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(this, R.color.uncompleted_text_color))//设置StepsViewIndicator未完成线的颜色
                .setStepViewComplectedTextColor(ContextCompat.getColor(this, android.R.color.white))//设置StepsView text完成线的颜色
                .setStepViewUnComplectedTextColor(ContextCompat.getColor(this, R.color.uncompleted_text_color))//设置StepsView text未完成线的颜色
                .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(this, R.drawable.complted))//设置StepsViewIndicator CompleteIcon
                .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(this, R.drawable.default_icon))//设置StepsViewIndicator DefaultIcon
                .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(this, R.drawable.attention));//设置StepsViewIndicator AttentionIcon
        fab = (FloatingActionButton)findViewById(R.id.to_map_bt);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Snackbar.make(v,"floating button work",Snackbar.LENGTH_SHORT).show();
                Intent intent = new Intent(result.this , map_path.class);
                intent.putExtra("mappath",route.get(1));
                startActivity(intent);
            }
        });
    }

}
