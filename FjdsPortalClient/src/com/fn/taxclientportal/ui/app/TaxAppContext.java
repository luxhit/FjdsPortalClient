/**
 * 
 */
package com.fn.taxclientportal.ui.app;

import static com.fn.taxclientportal.ui.app.TaxConstants.Mail.FJDS_MAIL_ACCOUNT;
import static com.fn.taxclientportal.ui.app.TaxConstants.Mail.FJDS_MAIL_PASSWORD;

import java.net.UnknownHostException;
import java.util.Stack;

import android.app.Activity;
import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.androidquery.util.AQUtility;
import com.fn.taxclientportal.support.mail.Mail;
import com.fn.taxclientportal.ui.util.AppUtil;

/**
 * 全局变量
 * 
 * @author luxiang
 *
 */
public class TaxAppContext extends Application {
	protected static final String TAG = TaxAppContext.class.getSimpleName();

	private static Stack<Activity> activitiesStack = new Stack<Activity>();

	public static int versionCode;
	public static String versionName;

	public static String imei;
	public static String ip;
	public static String device;

	public static String umengChannel;

	public static boolean isDebug = true;
	public static boolean isAutoMail = true;
	public static Mail mail = new Mail(FJDS_MAIL_ACCOUNT, FJDS_MAIL_PASSWORD);

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
	public static ActionBar.Tab infomationPublicTabSelectedTab;

	@Override
	public void onCreate() {
		super.onCreate();
		// 若为生产模式，设置全局线程异常处理
		AQUtility.setDebug(isDebug);

		ErrorReporter errorReporter = new ErrorReporter(this);
		AQUtility.setExceptionHandler(errorReporter);

		// 获取包实例
		PackageManager pm = this.getPackageManager();
		PackageInfo pi = null;
		ApplicationInfo ai = null;
		try {
			// 获取包信息
			pi = pm.getPackageInfo(getPackageName(), 0);
			// 包版本编码
			TaxAppContext.versionCode = pi.versionCode;
			// 包版本名称
			TaxAppContext.versionName = pi.versionName;

			// Get TelephonyManager
			TelephonyManager mngr = (TelephonyManager) this
					.getSystemService(TELEPHONY_SERVICE);

			imei = mngr.getDeviceId();
			device = android.os.Build.DEVICE;

			ip = AppUtil.getIpAddress();

			// 获取应用信息
			ai = pm.getApplicationInfo(getPackageName(),
					PackageManager.GET_META_DATA);
			if (ai.metaData != null) {
				String _umengChannel = "" + ai.metaData.get("UMENG_CHANNEL");
				umengChannel = _umengChannel.trim();
			}
		} catch (NameNotFoundException e) {
			Log.e(TAG, e.getMessage(), e);
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
		}
	}

	public static Activity addActivity(Activity activity) {
		activitiesStack.push(activity);

		return activity;
	}

	public static Activity currentActivity() {
		return activitiesStack.lastElement();
	}

	public static Activity removeActivity(Activity activity) {
		activitiesStack.remove(activity);
		activity.finish();
		activity = null;

		return activity;
	}

	public static void removeAllActivities() {
		if (activitiesStack != null && !activitiesStack.isEmpty()) {
			for (Activity a = activitiesStack.pop(); !activitiesStack.isEmpty();) {
				if (a != null) {
					a.finish();
					a = null;
				}
			}
		}
	}
}
