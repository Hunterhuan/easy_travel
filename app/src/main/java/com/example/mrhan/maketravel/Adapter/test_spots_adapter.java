package com.example.mrhan.maketravel.Adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.mrhan.maketravel.MainActivity;
import com.example.mrhan.maketravel.MyAlgorithm;
import com.example.mrhan.maketravel.R;

import java.util.List;

/**
 * Created by Mr.Han on 2018/6/15.
 */

public class test_spots_adapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public test_spots_adapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        //可链式调用赋值
        //Glide.with(mcontext).load(MainActivity.tst.db.getImage(item)).apply(new RequestOptions().fitCenter()).into(myHolder.image);
        helper.setText(R.id.spotname, item)
                //.setImageResource(R.id.img_spot, MainActivity.tst.db.getImage(item))
                //.setImageResource(R.id.img_spot, R.drawable.ic_favorite_border_black_24dp)
                .addOnClickListener(R.id.img_favorite)
                .setImageResource(R.id.img_favorite, R.drawable.ic_favorite_border_black_24dp);
        Glide.with(mContext).load(MainActivity.tst.db.getImage(item)).apply(new RequestOptions().fitCenter()).into((ImageView)helper.getView(R.id.img_spot));
        //获取当前条目position
        //int position = helper.getLayoutPosition();
    }
}