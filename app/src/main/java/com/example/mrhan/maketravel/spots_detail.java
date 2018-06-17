package com.example.mrhan.maketravel;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.flaviofaria.kenburnsview.KenBurnsView;

public class spots_detail extends AppCompatActivity {
    String spotname;
    TextView name, intro;
    KenBurnsView img;
    Button btn;
    EditText time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spots_detail);
        spotname = getIntent().getStringExtra("String_data");
        name = (TextView)findViewById(R.id.spot_name_detail);
        intro = (TextView)findViewById(R.id.spot_intro_detail);
        img =(KenBurnsView)findViewById(R.id.spot_img_detail);
        Glide.with(this).load(MainActivity.tst.db.getImage(spotname)).apply(new RequestOptions().fitCenter()).into(img);

        time = (EditText)findViewById(R.id.edit_time);
        btn = (Button)findViewById(R.id.change_time_btn);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String str_time=time.getText().toString();
                Snackbar.make(v ,"你修改"+spotname+"的时间为"+str_time ,Snackbar.LENGTH_SHORT).show();
            }
        });


        //String image = MainActivity.tst.db.getImage(spotname);
        String info = MainActivity.tst.db.getIntro(spotname);

        name.setText(spotname+"\n门票价格："+MainActivity.tst.db.getPrice(spotname)+"\n参考游玩时间："+MainActivity.tst.get_time_duration(MainActivity.tst.db.getVisitTime(spotname))+"\n热门程度："+MainActivity.tst.db.getPop(spotname).toString());
        intro.setText(info);

    }
}
