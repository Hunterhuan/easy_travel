package com.example.mrhan.maketravel.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Mr.Han on 2018/5/21.
 */

public class FragmentAdapter extends FragmentStatePagerAdapter{
    private List<Fragment> fragmentList;
    private List<String> titles;

    public FragmentAdapter(FragmentManager fm, List<Fragment> fragments, List<String> title){
        super(fm);
        fragmentList = fragments;
        titles = title;
    }

    @Override
    public Fragment getItem(int position){
        return fragmentList.get(position);
    }

    @Override
    public int getCount(){
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position){
        return titles.get(position);
    }
}
