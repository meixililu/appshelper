package com.messi.appshelper;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridView;
import android.widget.TextView;

import com.gc.materialdesign.views.ProgressBarCircularIndeterminate;
import com.messi.appshelper.adapter.OftenUseAppGridViewAdapter;
import com.messi.appshelper.dao.AppInfo;
import com.messi.appshelper.db.DataBaseUtil;
import com.messi.appshelper.util.KeyUtil;
import com.messi.appshelper.util.PackageInfoUtil;

public class CategoryDetialActivity extends BaseActivity {

	private ProgressBarCircularIndeterminate mProgressbar;
	private GridView mGridView;
	private OftenUseAppGridViewAdapter mAdapter;
	private List<AppInfo> mAppInfoList;
	private int requestCode = 10001;
	private TextView no_result;
	private String category;
	private String title;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category_detial);
		initData();
		init();
	}

	private void initData(){
		category = getIntent().getStringExtra(KeyUtil.CategoryCodeKey);
		title = getIntent().getStringExtra(KeyUtil.TitleKey);
		mAppInfoList =  new ArrayList<AppInfo>();
		if(TextUtils.isEmpty(category)){
			finish();
		}
		if(TextUtils.isEmpty(title)){
			finish();
		}
	}
	
	private void init(){
		getSupportActionBar().setTitle(title);
		mProgressbar = (ProgressBarCircularIndeterminate) findViewById(R.id.progressbar);
		mGridView = (GridView) findViewById(R.id.gridview);
		no_result = (TextView) findViewById(R.id.no_result);
		LayoutInflater mInflater = LayoutInflater.from(this);
		mAdapter = new OftenUseAppGridViewAdapter(this, mInflater, mAppInfoList);
		mGridView.setAdapter(mAdapter);
		no_result.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startIntent(category);
			}
		});
		new GetAppListTask().execute();
	}
	
	private void startIntent(String code){
		Intent intent = new Intent(this,EditCategoryActivity.class);
		intent.putExtra(KeyUtil.CategoryCodeKey, code);
		intent.putExtra(KeyUtil.TitleKey, title);
		startActivityForResult(intent, requestCode);
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit, menu);
        return true;
    }
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_edit:
			startIntent(category);
			break;
		case android.R.id.home:
			finish();
			break;
		}
       return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		super.onActivityResult(arg0, arg1, arg2);
		new GetAppListTask().execute();
	}
	
	class GetAppListTask extends AsyncTask<Void, Void, Void>{

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			showProgressbar();
		}
		
		@Override
		protected Void doInBackground(Void... params) {
			List<AppInfo> mList = PackageInfoUtil.getAppInfoByCategory(category);
			if(mList != null){
				mAppInfoList.clear();
				mAppInfoList.addAll(mList);
			}
			return null;
		}


		@Override
		protected void onPostExecute(Void result) {
			hideProgressbar();
			if(mAppInfoList.size() > 0){
				no_result.setVisibility(View.GONE);
				mAdapter.notifyDataSetChanged();
			}else{
				no_result.setVisibility(View.VISIBLE);
			}
		}
	}
	
	public void showProgressbar(){
		mProgressbar.setVisibility(View.VISIBLE);
	}
	
	public void hideProgressbar(){
		mProgressbar.setVisibility(View.GONE);
	}
}
