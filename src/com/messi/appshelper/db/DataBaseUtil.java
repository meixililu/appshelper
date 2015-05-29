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
	
	public void insertCategoryList(List<Category> beans){
		mCategoryDao.insertInTx(beans);
	}
	
	public long insert(AppInfo bean){
		return mAppInfoDao.insert(bean);
	}
	
	public void insertAppInfoList(List<AppInfo> beans){
		mAppInfoDao.insertInTx(beans);
	}
	
	public void update(Category bean){
		mCategoryDao.update(bean);
	}
	
	public void update(AppInfo bean){
		mAppInfoDao.update(bean);
	}
	
	public void update(List<AppInfo> beans){
		mAppInfoDao.updateInTx(beans);
	}
	
	public void insertOrUpdate(AppInfo bean){
		AppInfo old = isExit(bean);
		if(old != null){
			
		}else{
			insert(bean);
		}
	}
	
	public void insertOrUpdate(List<AppInfo> beans){
		for(AppInfo mAppInfo : beans){
			AppInfo old = isExit(mAppInfo);
			if(old != null){
				
			}else{
				insert(mAppInfo);
			}
		}
	}

	public AppInfo isExit(AppInfo bean){
		QueryBuilder<AppInfo> qb = mAppInfoDao.queryBuilder();
		qb.where(AppInfoDao.Properties.PackageName.eq(bean.getPackageName()));
		int size = qb.list().size();
		if(size > 0){
			return qb.list().get(0);
		}else{
			return null;
		}
	}
	
	public AppInfo isExit(String packageName){
		QueryBuilder<AppInfo> qb = mAppInfoDao.queryBuilder();
		qb.where(AppInfoDao.Properties.PackageName.eq(packageName));
		int size = qb.list().size();
		if(size > 0){
			return qb.list().get(0);
		}else{
			return null;
		}
	}
	
	public String getTypeName(String code){
		QueryBuilder<Category> qb = mCategoryDao.queryBuilder();
		qb.where(CategoryDao.Properties.Cid.eq(code));
		int size = qb.list().size();
		if(size > 0){
			return qb.list().get(0).getName();
		}else{
			return "";
		}
	}
	
	public List<Category> getDataListCategory(int offset, int maxResult) {
		QueryBuilder<Category> qb = mCategoryDao.queryBuilder();
		qb.orderAsc(CategoryDao.Properties.Cid);
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
