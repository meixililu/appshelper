package com.messi.appshelper;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.gc.materialdesign.views.ProgressBarCircularIndeterminate;
import com.messi.appshelper.adapter.MainPageAdapter;
import com.messi.appshelper.listener.FragmentProgressbarListener;
import com.messi.appshelper.util.PackageInfoUtil;
import com.messi.appshelper.view.PagerSlidingTabStrip;

public class MainActivity extends BaseActivity implements FragmentProgressbarListener{

	private ViewPager viewPager;
	private PagerSlidingTabStrip indicator;
	private ProgressBarCircularIndeterminate mProgressbar;
	private MainPageAdapter mAdapter;
	private long exitTime = 0;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}
	
	private void init(){
		if (toolbar != null) {
			getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//			toolbar.setNavigationIcon(R.drawable.ic_menu_white_24dp);
		}
		viewPager = (ViewPager) findViewById(R.id.pager);
		indicator = (PagerSlidingTabStrip) findViewById(R.id.indicator);
		mProgressbar = (ProgressBarCircularIndeterminate) findViewById(R.id.progressbar);
		mAdapter = new MainPageAdapter(this.getSupportFragmentManager(),this);
		viewPager.setAdapter(mAdapter);
		viewPager.setOffscreenPageLimit(4);
		indicator.setViewPager(viewPager);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void showProgressbar(){
		mProgressbar.setVisibility(View.VISIBLE);
	}
	
	public void hideProgressbar(){
		mProgressbar.setVisibility(View.GONE);
	}
	
	@Override
	public void onBackPressed() {
    	if ((System.currentTimeMillis() - exitTime) > 2000) {
			Toast.makeText(getApplicationContext(), this.getResources().getString(R.string.exit_program), 0).show();
			exitTime = System.currentTimeMillis();
		} else {
			finish();
		}
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		PackageInfoUtil.mAppInfoList.clear();
		PackageInfoUtil.mAppInfoList = null;
	}
}
