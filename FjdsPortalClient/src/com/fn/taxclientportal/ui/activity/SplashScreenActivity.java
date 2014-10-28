/**
 * 
 */
package com.fn.taxclientportal.ui.activity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fjltax.sa.pojo.Gdtp;
import org.fjltax.sa.pojo.Khdcs;
import org.fjltax.sa.pojo.PcModuleCode;
import org.fjltax.sa.pojo.ResEnter;
import org.fjltax.sa.pojo.Tzgg;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.fn.taxclientportal.ui.app.TaxAppContext;
import com.fn.taxclientportal.ui.app.TaxConstants;
import com.fn.taxclientportal.ui.app.TaxConstants.App;
import com.google.gson.Gson;

/**
 * @author luxiang
 * @version 1.0
 */
public class SplashScreenActivity extends TaxBasicActivity {
	protected static final String TAG = SplashScreenActivity.class
			.getSimpleName();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.splash_layout);

		this.getSupportActionBar().hide();

		new Handler().postDelayed(new Runnable() {

			public void run() {
				initGlobalParams();

				// ProgressDialog myDialog = ProgressDialog.show(
				// SplashScreenActivity.this, "", "Loading", true);

				SharedPreferences sharedPreferences = PreferenceManager
						.getDefaultSharedPreferences(SplashScreenActivity.this);

				if (!sharedPreferences.getBoolean(App.IS_INSTALLED, false)) {
					Intent intent = new Intent(SplashScreenActivity.this,
							GuideActivity.class);
					SplashScreenActivity.this.startActivity(intent);
					// myDialog.dismiss();

				} else {
					Intent intent = new Intent(SplashScreenActivity.this,
							MainActivity.class);
					SplashScreenActivity.this.startActivity(intent);
				}
				String channelid = TaxAppContext.umengChannel;

				Map<String, String> map = new HashMap<String, String>();
				map.put("req", "enter");
				map.put("channelid", channelid);
				map.put("imei", TaxAppContext.imei);
				map.put("ip", TaxAppContext.ip);
				map.put("device", TaxAppContext.device);

				Log.d(TAG, "map:" + map.toString());

				aquery.ajax(TaxConstants.Url.API_URL, map, JSONObject.class,
						new AjaxCallback<JSONObject>() {

							@Override
							public void callback(String url, JSONObject object,
									AjaxStatus status) {
								String json = object.toString();
								Log.d(TAG, "json:" + json);
								ResEnter re = new Gson().fromJson(json,
										ResEnter.class);
								// 成功
								if (re.getCode() == 0) {
									// 滚动图片列表
									List<Gdtp> gdtpList = re.getGdtpList();
									// 客户端参数列表
									List<Khdcs> khdcsList = re.getKhdcsList();
									// 模块列表
									List<PcModuleCode> pcModuleCodeList = re
											.getPcModuleCodeList();
									// 通知公告列表
									List<Tzgg> tzggList = re.getTzggList();
								}
								SplashScreenActivity.this.finish();
							}

						});

			}

		}, 3000l);
	}

	@SuppressLint("NewApi")
	private void initGlobalParams() {
		Point size = new Point();
		WindowManager w = getWindowManager();

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			w.getDefaultDisplay().getSize(size);

			TaxAppContext.screenWidth = size.x;
			TaxAppContext.screenHeight = size.y;
		} else {
			DisplayMetrics metrics = new DisplayMetrics();
			getWindowManager().getDefaultDisplay().getMetrics(metrics);

			TaxAppContext.screenHeight = metrics.heightPixels;
			TaxAppContext.screenWidth = metrics.widthPixels;
		}
		Log.i(TAG, "screenWidth:" + TaxAppContext.screenWidth + ","
				+ TaxAppContext.screenHeight);
	}
}
