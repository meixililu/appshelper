package com.messi.appshelper.db;

import java.util.List;

import android.content.Context;

import com.messi.appshelper.BaseApplication;
import com.messi.appshelper.dao.AppInfo;
import com.messi.appshelper.dao.AppInfoDao;
import com.messi.appshelper.dao.Category;
import com.messi.appshelper.dao.CategoryDao;
import com.messi.appshelper.dao.DaoSession;

import de.greenrobot.dao.query.QueryBuilder;

public class DataBaseUtil {

	private static DataBaseUtil instance;  
    private static Context appContext;  
    private DaoSession mDaoSession;  
    private CategoryDao mCategoryDao;  
    private AppInfoDao mAppInfoDao;  

	public DataBaseUtil() {
	}
	
	public static DataBaseUtil getInstance() {  
        if (instance == null) {  
            instance = new DataBaseUtil();  
            if (appContext == null){  
                appContext = BaseApplication.mInstance;  
            }  
            instance.mDaoSession = BaseApplication.getDaoSession(appContext);  
            instance.mCategoryDao = instance.mDaoSession.getCategoryDao();  
            instance.mAppInfoDao = instance.mDaoSession.getAppInfoDao();  
        }  
        return instance;  
    }  
	
	public long insert(Category bean){
		return mCategoryDao.insert(bean);
	}
	
	public void insert(List<Category> beans){
		mCategoryDao.insertInTx(beans);
	}
	
	public long insert(AppInfo bean){
		return mAppInfoDao.insert(bean);
	}
	
	public void update(Category bean){
		mCategoryDao.update(bean);
	}
	
	public void update(AppInfo bean){
		mAppInfoDao.update(bean);
	}

	public List<Category> getDataListCategory(int offset, int maxResult) {
		QueryBuilder<Category> qb = mCategoryDao.queryBuilder();
		qb.orderDesc(CategoryDao.Properties.Id);
		qb.limit(maxResult);
		return qb.list();
	}
	
	public List<AppInfo> getDataListAppInfo(int offset, int maxResult) {
		QueryBuilder<AppInfo> qb = mAppInfoDao.queryBuilder();
		qb.orderDesc(AppInfoDao.Properties.Id);
		qb.limit(maxResult);
		return qb.list();
	}
	
	public void dele(Category bean) {
		mCategoryDao.delete(bean);
	}
	
	public void dele(AppInfo bean) {
		mAppInfoDao.delete(bean);
	}
	
	public void clearAllCategory(){
		mCategoryDao.deleteAll();
	}
	
	public void clearAllAppInfo() {
		mAppInfoDao.deleteAll();
	}

	public long getCategoryCount() {
		return mCategoryDao.count();
	}
	
	public long getAppInfoCount(){
		return mAppInfoDao.count();
	}
	
//	public boolean isExist(long cid){
//		QueryBuilder<EveryDaySentence> qb = mEveryDaySentenceDao.queryBuilder();
//		qb.where(EveryDaySentenceDao.Properties.Cid.eq(cid));
//		int size = qb.list().size();
//		return size > 0;
//	}
	
}
