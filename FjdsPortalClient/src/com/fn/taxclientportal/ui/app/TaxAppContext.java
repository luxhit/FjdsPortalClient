/**
 * 
 */
package com.fn.taxclientportal.ui.app;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;

/**
 * 全局变量
 * 
 * @author luxiang
 *
 */
public class TaxAppContext extends Application {
	protected static final String TAG = TaxAppContext.class.getSimpleName();

	public static int versionCode;
	public static String versionName;

	public static String[] mainImageFilePaths = {
			TaxConstants.Folder.IMAGES_FOLDER + "20140906_173330.jpg",
			TaxConstants.Folder.IMAGES_FOLDER + "20140906_173339.jpg",
			TaxConstants.Folder.IMAGES_FOLDER + "20140906_173351.jpg",
			TaxConstants.Folder.IMAGES_FOLDER + "20140906_173414.jpg" };

	public static String[] mainImageDescriptions = { "福建地税0", "福建地税1", "福建地税2",
			"福建地税3", "福建地税4" };

	public static int screenWidth;
	public static int screenHeight;

	public static ActionBar.Tab publicServiceTabSelectedTab;

	@Override
	public void onCreate() {
		super.onCreate();

		// 获取包实例
		PackageManager pm = this.getPackageManager();
		PackageInfo pi = null;
		try {
			// 获取包信息
			pi = pm.getPackageInfo(getPackageName(), 0);
			// 包版本编码
			TaxAppContext.versionCode = pi.versionCode;
			// 包版本名称
			TaxAppContext.versionName = pi.versionName;
		} catch (NameNotFoundException e) {
			Log.e(TAG, e.getMessage(), e);
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
		}

	}

}
