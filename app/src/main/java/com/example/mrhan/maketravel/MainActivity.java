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
import android.widget.Toast;

import com.example.mrhan.maketravel.Selectcity;
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle ab;
    private Toolbar toolbar;
    private NavigationView nv;
    private FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=(Toolbar) findViewById(R.id.toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        setSupportActionBar(toolbar);
        nv = (NavigationView) findViewById(R.id.nav_view);
        nv.setNavigationItemSelectedListener(this);
        ab = new ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close);
        mDrawerLayout.addDrawerListener(ab);
        ab.syncState();

        fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Snackbar.make(v,"floating button work",Snackbar.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this , travel_tab.class);
                startActivity(intent);
            }
        });
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
                Snackbar.make(mDrawerLayout,"1",Snackbar.LENGTH_SHORT).show();
                Intent intent = new Intent(this,Login.class);
                startActivity(intent);
                break;
            case R.id.nav_2:
                Snackbar.make(mDrawerLayout,"2",Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.nav_3:
                Snackbar.make(mDrawerLayout,"3",Snackbar.LENGTH_SHORT).show();
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

}
