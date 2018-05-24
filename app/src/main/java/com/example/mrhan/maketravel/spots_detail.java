package com.example.mrhan.maketravel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.flaviofaria.kenburnsview.KenBurnsView;

public class spots_detail extends AppCompatActivity {
    String spotname;
    TextView name, intro;
    KenBurnsView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spots_detail);
        spotname = getIntent().getStringExtra("String_data");
        name = (TextView)findViewById(R.id.spot_name_detail);
        intro = (TextView)findViewById(R.id.spot_intro_detail);
        img =(KenBurnsView)findViewById(R.id.spot_img_detail);
        Glide.with(this).load(MainActivity.tst.db.getImage(spotname)).apply(new RequestOptions().fitCenter()).into(img);




        //String image = MainActivity.tst.db.getImage(spotname);
        String info = MainActivity.tst.db.getIntro(spotname);

        name.setText(spotname+"\n门票价格："+MainActivity.tst.db.getPrice(spotname)+"\n参考游玩时间："+MainActivity.tst.get_time_duration(MainActivity.tst.db.getVisitTime(spotname))+"\n热门程度："+MainActivity.tst.db.getPop(spotname).toString().substring(0,4));
        intro.setText(info);

    }
}
