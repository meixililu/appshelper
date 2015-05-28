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
import android.widget.TextView;

import com.messi.appshelper.R;
import com.messi.appshelper.dao.AppInfo;
import com.messi.appshelper.util.LogUtil;
import com.messi.appshelper.util.ToastUtil;

public class OftenUseAppGridViewAdapter extends BaseAdapter {

	private Context mContext; 
	private LayoutInflater mInflater;
	private List<AppInfo> mAppInfoList;
	
	public OftenUseAppGridViewAdapter(Context mContext,LayoutInflater mInflater,List<AppInfo> mAppInfoList){
		this.mContext = mContext;
		this.mInflater = mInflater;
		this.mAppInfoList = mAppInfoList;
	}
	
	
	@Override
	public int getCount() {
		return mAppInfoList.size();
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
			convertView = mInflater.inflate(R.layout.often_use_app_gridview_adapter_item, null);
			holder = new ViewHolder();
			holder.app_cover = (FrameLayout) convertView.findViewById(R.id.app_cover);
			holder.app_icon = (ImageView) convertView.findViewById(R.id.app_icon);
			holder.app_name = (TextView) convertView.findViewById(R.id.app_name);
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
		holder.app_cover.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					mContext.startActivity(mContext.getPackageManager().getLaunchIntentForPackage(mAppInfo.getPackageName()));
				} catch (Exception e) {
					ToastUtil.diaplayMesLong(mContext, "启动失败！");
					e.printStackTrace();
				}
			}
		});
		return convertView;
	}

	static class ViewHolder {
		FrameLayout app_cover;
		ImageView app_icon;
		TextView app_name;
	}
	
}
