package com.messi.appshelper.adapter;

import java.util.List;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.messi.appshelper.R;
import com.messi.appshelper.dao.AppInfo;
import com.messi.appshelper.db.DataBaseUtil;
import com.messi.appshelper.util.ToastUtil;

public class EditCategoryGridViewAdapter extends BaseAdapter {

	private Context mContext; 
	private LayoutInflater mInflater;
	private List<AppInfo> mAppInfoList;
	private String category;
	
	public EditCategoryGridViewAdapter(Context mContext,LayoutInflater mInflater,List<AppInfo> mAppInfoList,String category){
		this.mContext = mContext;
		this.mInflater = mInflater;
		this.category = category;
		this.mAppInfoList = mAppInfoList;
	}
	
	
	@Override
	public int getCount() {
		if(mAppInfoList != null){
			return mAppInfoList.size();
		}else{
			return 0;
		}
	}

	@Override
	public AppInfo getItem(int position) {
		return mAppInfoList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.edit_category_gridview_adapter_item, null);
			holder = new ViewHolder();
			holder.check_layout = (LinearLayout) convertView.findViewById(R.id.check_layout);
			holder.app_cover = (FrameLayout) convertView.findViewById(R.id.app_cover);
			holder.app_icon = (ImageView) convertView.findViewById(R.id.app_icon);
			holder.app_name = (TextView) convertView.findViewById(R.id.app_name);
			holder.check_img = (ImageView) convertView.findViewById(R.id.check_img);
			holder.check_txt = (TextView) convertView.findViewById(R.id.check_txt);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		final AppInfo mAppInfo = mAppInfoList.get(position);
		if(mAppInfo.getAppIcon() != null){
			holder.app_icon.setImageDrawable(mAppInfo.getAppIcon());
		}
		if(!TextUtils.isEmpty(mAppInfo.getAppName())){
			holder.app_name.setText(mAppInfo.getAppName());
		}
		if(!TextUtils.isEmpty(mAppInfo.getDefaultType())){
			holder.check_layout.setVisibility(View.VISIBLE);
			if(mAppInfo.getDefaultType().equals(category)){
				holder.check_img.setVisibility(View.VISIBLE);
				holder.check_txt.setVisibility(View.GONE);
			}else{
				holder.check_img.setVisibility(View.GONE);
				holder.check_txt.setVisibility(View.VISIBLE);
				holder.check_txt.setText(mAppInfo.getTypeName());
			}
		}else{
			holder.check_layout.setVisibility(View.GONE);
		}
		holder.app_cover.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(!TextUtils.isEmpty(mAppInfo.getDefaultType())){
					mAppInfo.setTypeName("");
					mAppInfo.setDefaultType("");
				}else{
					mAppInfo.setDefaultType(category);
					mAppInfo.setTypeName(DataBaseUtil.getInstance().getTypeName(category));
				}
				DataBaseUtil.getInstance().update(mAppInfo);
				notifyDataSetChanged();
			}
		});
		return convertView;
	}

	static class ViewHolder {
		FrameLayout app_cover;
		ImageView app_icon;
		TextView app_name;
		LinearLayout check_layout;
		ImageView check_img;
		TextView check_txt;
	}
	
}
