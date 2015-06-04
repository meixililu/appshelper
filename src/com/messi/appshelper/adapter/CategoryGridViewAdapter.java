package com.messi.appshelper.adapter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.messi.appshelper.CategoryDetialActivity;
import com.messi.appshelper.R;
import com.messi.appshelper.dao.Category;
import com.messi.appshelper.util.KeyUtil;

public class CategoryGridViewAdapter extends BaseAdapter {

	private Context mContext; 
	private LayoutInflater mInflater;
	private List<Category> mCategoryList;
	
	public CategoryGridViewAdapter(Context mContext,LayoutInflater mInflater,List<Category> mAppInfoList){
		this.mContext = mContext;
		this.mInflater = mInflater;
		this.mCategoryList = mAppInfoList;
	}
	
	
	@Override
	public int getCount() {
		return mCategoryList.size();
	}

	@Override
	public Category getItem(int position) {
		return mCategoryList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.category_gridview_adapter_item, null);
			holder = new ViewHolder();
			holder.app_cover = (FrameLayout) convertView.findViewById(R.id.app_cover);
//			holder.app_icon = (ImageView) convertView.findViewById(R.id.app_icon);
			holder.app_name = (TextView) convertView.findViewById(R.id.app_name);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		final Category mCategory = mCategoryList.get(position);
		if(!TextUtils.isEmpty(mCategory.getName())){
			holder.app_name.setText(mCategory.getName());
		}
		holder.app_cover.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startIntent(mCategory.getCid(), mCategory.getName());
			}
		});
		return convertView;
	}
	
	private void startIntent(String code, String title){
		Intent intent = new Intent(mContext,CategoryDetialActivity.class);
		intent.putExtra(KeyUtil.CategoryCodeKey, code);
		intent.putExtra(KeyUtil.TitleKey, title);
		mContext.startActivity(intent);
	}

	static class ViewHolder {
		FrameLayout app_cover;
//		ImageView app_icon;
		TextView app_name;
	}
	
}
