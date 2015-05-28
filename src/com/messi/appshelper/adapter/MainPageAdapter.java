package com.messi.appshelper.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.messi.appshelper.CategoryFragment;
import com.messi.appshelper.OftenFragment;
import com.messi.appshelper.R;
import com.messi.appshelper.MoreFragment;

public class MainPageAdapter extends FragmentPagerAdapter {

	public static String[] CONTENT;
	
    public MainPageAdapter(FragmentManager fm,Context mContext) {
        super(fm);
        CONTENT = new String[] { 
        		mContext.getResources().getString(R.string.title_category),
        		mContext.getResources().getString(R.string.title_often),
        		mContext.getResources().getString(R.string.title_more),
        };
    }

    @Override
    public Fragment getItem(int position) {
        if( position == 0 ){
        	return CategoryFragment.getInstance();
        }else if( position == 1 ){
        	return OftenFragment.getInstance();
        }else if( position == 2 ){
        	return MoreFragment.getInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return CONTENT.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return CONTENT[position].toUpperCase();
    }
}