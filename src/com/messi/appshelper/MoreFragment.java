package com.messi.appshelper;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;


public class MoreFragment extends BaseFragment implements OnClickListener {
	
	public static MoreFragment mBaseFragment;
	
	public static BaseFragment getInstance(){
		if(mBaseFragment == null){
			mBaseFragment = new MoreFragment();
		}
		return mBaseFragment;
	}

	@Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
        }
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.category_fragment, null);
		init();
		return view;
	}
	
	private void init() {
		
	}

	@Override
	public void onClick(View v) {
		
	}
}
