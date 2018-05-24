package com.example.mrhan.maketravel.Adapter;

        import android.content.Context;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.example.mrhan.maketravel.MainActivity;
        import com.example.mrhan.maketravel.MyAlgorithm;
        import com.example.mrhan.maketravel.R;

        import java.util.Collections;
        import java.util.List;
        import com.bumptech.glide.Glide;
        import com.bumptech.glide.request.RequestOptions;
        import static java.security.AccessController.getContext;

/**
 * Created by Mr.Han on 2018/5/18.
 */

public class hotels_Adapter extends RecyclerView.Adapter<hotels_Adapter.MyHolder>{
    private LayoutInflater li;
    private List<String> citys= Collections.emptyList();
    private Context mcontext;
    public hotels_Adapter(Context context, List<String> citylist){
        citys = citylist;
        mcontext = context;
        li = LayoutInflater.from(context);
    }
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v=li.inflate(R.layout.hotel_cardview, parent, false);
        final MyHolder myHolder = new MyHolder(v);
        myHolder.cityview.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int position = myHolder.getAdapterPosition();
                String tmp = citys.get(position);
                Toast.makeText(v.getContext(),"you click view "+tmp,Toast.LENGTH_SHORT).show();
            }
        });
        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder myHolder, int position){
        String p=citys.get(position);
        myHolder.city.setText(p);
        myHolder.city_adress.setText(MainActivity.tst.db.getAddr(p));
        String imgurl = MainActivity.tst.db.getImage(p);
        System.out.println(imgurl);

        Glide.with(mcontext).load(imgurl).apply(new RequestOptions().fitCenter()).into(myHolder.image);
    }
    @Override
    public int getItemCount(){
        return citys.size();
    }
    class MyHolder extends RecyclerView.ViewHolder{
        TextView city,city_adress;
        ImageView image;
        View cityview;

        public MyHolder(View itemView){
            super(itemView);
            cityview=itemView;
            city = (TextView)itemView.findViewById(R.id.hotel_name);
            city_adress = (TextView)itemView.findViewById(R.id.hotel_detail);
            image = (ImageView)itemView.findViewById(R.id.hotel_img);
        }
    }
}
