package com.messi.appshelper.util;

public class ConversionUtil {
	//数据类型转换

	/**
	 * String转int
	 */
	public static int StringToInt(String str) {
		try {
			int num = Integer.parseInt(str.replace("+", ""));
			return num;
		} catch (Exception e) {
			outputException(e, "StringToInt");
		}
		return 0;
	}

	/**
	 * String转long
	 */
	public static long StringToLong(String str) {
		try {
			long num = Long.parseLong(str);
			return num;
		} catch (Exception e) {
			outputException(e, "StringToLong");
		}
		return 0;
	}

	/**
	 * String转double
	 */
	public static double StringToDouble(String str) {
		try {
			double num = Double.parseDouble(str);
			return num;
		} catch (Exception e) {
			outputException(e, "StringToDouble");
		}
		return 0;
	}

	/**
	 * String转float
	 */
	public static float StringToFloat(String str) {
		try {
			float num = Float.parseFloat(str);
			return num;
		} catch (Exception e) {
			outputException(e, "StringToFloat");
		}
		return 0;
	}
	
	/**
	 * 在测试的情况下进行Exception 抛出
	 * @param e 当前相关Exception
	 * @param prompt 提示的内�? 用方法名
	 */
	private static void outputException(Exception e, String prompt){
		if(false){
			LogUtil.ExceptionLog("ConversionUtil -- " + prompt);
			e.printStackTrace();
		}
	}
}
