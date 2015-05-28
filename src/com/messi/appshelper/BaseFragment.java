package com.messi.appshelper;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.View;

import com.messi.appshelper.listener.FragmentProgressbarListener;


public class BaseFragment extends Fragment {
	
	protected FragmentProgressbarListener mProgressbarListener;
	protected View view;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mProgressbarListener = (FragmentProgressbarListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement FragmentProgressbarListener");
        }
	}
	
	/**
	 * 通过接口回调activity执行进度条显示控制
	 */
	protected void loadding(){
		if(mProgressbarListener != null){
			mProgressbarListener.showProgressbar();
		}
	}
	
	/**
	 * 通过接口回调activity执行进度条显示控制
	 */
	protected void finishLoadding(){
		if(mProgressbarListener != null){
			mProgressbarListener.hideProgressbar();
		}
	}
}
