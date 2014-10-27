/**
 * 
 */
package com.fn.taxclientportal.ui.app;

import java.io.Serializable;

import android.os.Environment;

/**
 * 	常量
 * 	@author luxiang
 *	@version 1.0
 */
public final class TaxConstants implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5126445456973563871L;
	
	public static class App {
		public static final String IS_INSTALLED = "is_installed";
		
		public static final String VERSION_CODE = "versionCode";
		public static final String VERSION_NAME = "versionName";
		
		public static final int PUBLIC_SERVICE_TABS_COUNT = 4;
		
		public static final int MOBILE_PORTAL_NEWS = 0x0101;
		public static final int MOBILE_PORTAL_SUMMARY = 0x0102;
		public static final int MOBILE_PORTAL_GUIDE = 0x0103;
		public static final int MOBILE_PORTAL_NOTICE = 0x0104;
		public static final int MOBILE_PORTAL_POLICY = 0x0105;
		public static final int MOBILE_PORTAL_ORG_INCOME = 0x0106;
		public static final int MOBILE_PORTAL_PLAN_SUMMARY = 0x0107;
		public static final int MOBILE_PORTAL_HUMAN_INFO = 0x0108;
	}
	
	public static class Url {
		public static final String PROTOCEL_PREFIX = "http://";
		public static final String BASS_URL = PROTOCEL_PREFIX + "www.fj-l-tax.gov.cn/";
		public static final String ARTICLE_URL_PREFIX = BASS_URL + "servlet/ArticleServlet?arId=";
	}
	
	public static class Web {
		public static final String CONTENT_TYPE = "text/html";
		public static final String DEFAULT_ENCODING = "utf8";
	}
	
	public static class Rss {
		public static final String DEFAULT_ENCODING = "utf8";
	}
	
	public static class Item {
		public static final String TITLE = "title";
		public static final String PULISH_DATE = "pulish_date";
		public static final String CONTENT = "content";
		public static final String ID = "id";
	}
	
	public static class Folder {
		/**
		 * 扩展卡根目录
		 */
		public static final String ROOT_FOLDER = Environment.getExternalStorageDirectory().getAbsolutePath()
				+ "/fjds/";
		
		/** APK存放目录 */
		public static final String APKS_FOLDER =  ROOT_FOLDER + "apks/";
		
		/** 图片存放目录 */
		public static final String IMAGES_FOLDER =  ROOT_FOLDER + "images/";
	}

}
