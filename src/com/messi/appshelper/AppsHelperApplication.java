package com.messi.appshelper;

import java.util.HashMap;

import android.app.Application;
import android.content.Context;

public class AppsHelperApplication extends Application {

	/** 可以用于所有activity页面之间传送数据的静态map **/
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
     * 取得DaoMaster 
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
     * 取得DaoSession 
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
