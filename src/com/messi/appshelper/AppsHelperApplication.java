package com.messi.appshelper;

import java.util.HashMap;

import android.app.Application;
import android.content.Context;

public class AppsHelperApplication extends Application {

	/** ������������activityҳ��֮�䴫�����ݵľ�̬map **/
	public static HashMap<String, Object> dataMap = new HashMap<String, Object>();
    public static AppsHelperApplication mInstance;
//	private static DaoMaster daoMaster;  
//    private static DaoSession daoSession; 
    
    @Override  
    public void onCreate() {  
        super.onCreate();  
        if(mInstance == null)  
            mInstance = this;  
    }  
    
	/** 
     * ȡ��DaoMaster 
     * @param context 
     * @return 
     */  
//    public static DaoMaster getDaoMaster(Context context) {  
//        if (daoMaster == null) {  
//            OpenHelper helper = new DaoMaster.DevOpenHelper(context,LHContract.DATABASE_NAME, null);  
//            daoMaster = new DaoMaster(helper.getWritableDatabase());  
//        }  
//        return daoMaster;  
//    }  
      
    /** 
     * ȡ��DaoSession 
     * @param context 
     * @return 
     */  
//    public static DaoSession getDaoSession(Context context) {  
//        if (daoSession == null) {  
//            if (daoMaster == null) {  
//                daoMaster = getDaoMaster(context);  
//            }  
//            daoSession = daoMaster.newSession();  
//        }  
//        return daoSession;  
//    }
}
