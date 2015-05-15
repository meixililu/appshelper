package com.messi.appshelper.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.text.TextUtils;

public class SDCardUtil {

	/**sd卡保存文件夹名称**/
	public static final String sdPath = "/zyhy/audio/";
	public static final String ImgPath = "/DCIM/zyhy/";
	public static final String PracticePath = "/zyhy/audio/practice/en/";
	public static final String Delimiter = "/";
	
	/**sdcard路径
	 * @return
	 */
	public static String getDownloadPath(String sdCardPath) {
		File SDdir = null;
		boolean sdCardExist = Environment.getExternalStorageState().equals( android.os.Environment.MEDIA_MOUNTED);
		if (sdCardExist) {
			SDdir = Environment.getExternalStorageDirectory();
		}
		if (SDdir != null) {
			String path = SDdir.getPath() + sdCardPath;
			isFileExists(path);
			return path;
		} else {
			return null;
		}
	}
	
	public static void isFileExists(String path){
		File sdDir = new File(path);
		if(!sdDir.exists()){
			boolean isCreate = sdDir.mkdirs();
			LogUtil.DefalutLog("isFileExists-isCreate:"+isCreate);
		}
	}
	
	public static String isDirExits(Context mContext,String path) throws IOException{
		String sdcard = getDownloadPath(path);
		if(!TextUtils.isEmpty(path)){
			File sdDir = new File(sdcard + path);
			if(!sdDir.exists()){
				sdDir.mkdirs();
			}
			return sdcard + path;
		}else{
			return "";
		}
	}
	
	public static String saveBitmap(Context mContext, Bitmap bitmap) throws IOException {
		String sdcardDir = isDirExits(mContext, ImgPath);
		String filePath = "";
		if(!TextUtils.isEmpty(sdcardDir)){
			filePath = sdcardDir + "image_" + System.currentTimeMillis() + ".png";
			File file = new File(filePath);
			file.createNewFile();
			FileOutputStream out;
			try {
				out = new FileOutputStream(file);
				if (bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)) {
					out.flush();
					out.close();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			ToastUtil.diaplayMesShort(mContext, "请插入SD�?");
		}
		return filePath;
	}
	
	/**删除内部存储中之前下载的文件
	 * @param mContext
	 */
	public static void deleteOldFile(){
		String path = getDownloadPath(SDCardUtil.sdPath);
		File file = new File(path);
		deleteFileInDir(file);
	}
	
	/**删除文件夹里面的�?有文�?
	 * @param cacheDir
	 */
	public static void deleteFileInDir(File cacheDir){
		if(cacheDir.isDirectory()){
			File[] files = cacheDir.listFiles();  
			for (int i = 0; i < files.length; i++) {  
				if (files[i].isFile()) {  
					boolean flag = deleteFile(files[i].getAbsolutePath());  
					if (!flag) break;  
				} 
			}
		}
	}
	
	/**删除文件夹里面的单个文件
	 * @param sPath
	 * @return
	 */
	public static boolean deleteFile(String sPath) {  
		File file = new File(sPath);  
	    /**路径为文件且不为空则进行删除**/  
	    if (file.isFile() && file.exists()) {  
	        return file.delete();  
	    }  
	    return false;  
	}  
}
