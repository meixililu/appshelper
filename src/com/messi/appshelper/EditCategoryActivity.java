package com.messi.appshelper;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.widget.GridView;

import com.gc.materialdesign.views.ProgressBarCircularIndeterminate;
import com.messi.appshelper.adapter.EditCategoryGridViewAdapter;
import com.messi.appshelper.util.KeyUtil;
import com.messi.appshelper.util.PackageInfoUtil;

public class EditCategoryActivity extends BaseActivity {

	private ProgressBarCircularIndeterminate mProgressbar;
	private GridView mGridView;
	private EditCategoryGridViewAdapter mAdapter;
	private String category;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_category);
		initData();
		init();
	}

	private void initData(){
		category = getIntent().getStringExtra(KeyUtil.CategoryCodeKey);
		if(TextUtils.isEmpty(category)){
			finish();
		}
	}
	
	private void init(){
		getSupportActionBar().setTitle(getResources().getString(R.string.title_edit_category));
		mProgressbar = (ProgressBarCircularIndeterminate) findViewById(R.id.progressbar);
		mGridView = (GridView) findViewById(R.id.gridview);
		LayoutInflater mInflater = LayoutInflater.from(this);
		mAdapter = new EditCategoryGridViewAdapter(this, mInflater, PackageInfoUtil.mAppInfoList, category);
		mGridView.setAdapter(mAdapter);
	}
}
