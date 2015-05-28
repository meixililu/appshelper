package com.messi.appshelper;

import java.util.HashMap;

import android.app.Application;
import android.content.Context;

import com.messi.appshelper.dao.DaoMaster;
import com.messi.appshelper.dao.DaoMaster.OpenHelper;
import com.messi.appshelper.dao.DaoSession;
import com.messi.appshelper.db.DBContract;
import com.messi.appshelper.util.PackageInfoUtil;

public class BaseApplication extends Application {

	public static HashMap<String, Object> dataMap = new HashMap<String, Object>();
	private static DaoMaster daoMaster;  
    private static DaoSession daoSession; 
    public static BaseApplication mInstance;
    
    @Override  
    public void onCreate() {  
        super.onCreate();  
        mInstance = this; 
        PackageInfoUtil.initCategoryData();
    }  
    
	/** 
     * 取得DaoMaster 
     * @param context 
     * @return 
     */  
    public static DaoMaster getDaoMaster(Context context) {  
        if (daoMaster == null) {  
            OpenHelper helper = new DaoMaster.DevOpenHelper(context,DBContract.DATABASE_NAME, null);  
            daoMaster = new DaoMaster(helper.getWritableDatabase());  
        }  
        return daoMaster;  
    }  
      
    /** 
     * 取得DaoSession 
     * @param context 
     * @return 
     */  
    public static DaoSession getDaoSession(Context context) {  
        if (daoSession == null) {  
            if (daoMaster == null) {  
                daoMaster = getDaoMaster(context);  
            }  
            daoSession = daoMaster.newSession();  
        }  
        return daoSession;  
    }  

}
