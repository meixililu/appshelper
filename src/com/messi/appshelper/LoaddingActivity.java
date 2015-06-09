package com.messi.appshelper;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;

import com.messi.appshelper.util.PackageInfoUtil;

public class LoaddingActivity extends Activity {

	private boolean isFinishLoadingData;
	private boolean isFinishWaiting;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loading);
		new GetAppListTask().execute();
		waittingTask();
	}
	
	private void toMainActivity(){
		if(isFinishLoadingData && isFinishWaiting){
			Intent intent = new Intent(this,MainActivity.class);
			startActivity(intent);
			finish();
		}
	}
	
	private void waittingTask(){
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				isFinishWaiting = true;
				toMainActivity();
			}
		}, 2000);
	}
	
	class GetAppListTask extends AsyncTask<Void, Void, Void>{

		@Override
		protected Void doInBackground(Void... params) {
			PackageInfoUtil.getPackageInfoList(LoaddingActivity.this);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			isFinishLoadingData = true;
			toMainActivity();
		}
	}
}
