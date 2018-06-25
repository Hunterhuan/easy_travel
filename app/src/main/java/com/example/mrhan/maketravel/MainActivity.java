package com.example.mrhan.maketravel;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mrhan.maketravel.Selectcity;

import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle ab;
    private Toolbar toolbar;
    private NavigationView nv;
    private FloatingActionButton fab;
    private CircleImageView img;
    private TextView t1,t2;
    public static MyAlgorithm tst;
    public static UserManager userManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tst = new MyAlgorithm("上海");
        userManager = new UserManager();


        toolbar=(Toolbar) findViewById(R.id.toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        setSupportActionBar(toolbar);
        nv = (NavigationView) findViewById(R.id.nav_view);
        nv.setNavigationItemSelectedListener(this);

        View headerview = nv.getHeaderView(0);

        img = (CircleImageView)headerview.findViewById(R.id.icon_image);
        t1 = (TextView)headerview.findViewById(R.id.username);
        t2 = (TextView)headerview.findViewById(R.id.mail);

        ab = new ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close);
        mDrawerLayout.addDrawerListener(ab);
        ab.syncState();

        fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Snackbar.make(v,"floating button work",Snackbar.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this , Selectcity.class);
                startActivity(intent);
            }
        });

        ImageView bg = findViewById(R.id.bg_main);
        Glide.with(this).load(R.drawable.bg).apply(new RequestOptions().fitCenter()).into(bg);
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
        ab.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.nav_login:
                Intent intent = new Intent(this,Login.class);
                startActivity(intent);
                break;
            case R.id.nav_2:
/*                if(MainActivity.userManager.getState()){
                    Snackbar.make(mDrawerLayout,"请登陆后再操作！",Snackbar.LENGTH_SHORT).show();
                    break;
                }*/
                Intent intent2 = new Intent(this,person_page.class);
                startActivity(intent2);
                break;
            case R.id.nav_3:
/*                if(MainActivity.userManager.getState()){
                    Snackbar.make(mDrawerLayout,"请登陆后再操作！",Snackbar.LENGTH_SHORT).show();
                    break;
                }*/
                Intent intent3 = new Intent(this,About.class);
                startActivity(intent3);
                Snackbar.make(mDrawerLayout,"关于",Snackbar.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.settings:
                Toast.makeText(this,"1", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }
    @Override
    protected void onRestart(){
        super.onRestart();
        t1.setText("啦啦啦");
        t2.setText("ooo");
    }
}
