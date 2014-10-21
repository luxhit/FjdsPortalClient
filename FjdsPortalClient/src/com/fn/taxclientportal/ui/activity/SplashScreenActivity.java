/**
 * 
 */
package com.fn.taxclientportal.ui.activity;

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

import com.fn.taxclientportal.ui.app.TaxAppContext;
import com.fn.taxclientportal.ui.app.TaxConstants.App;

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

//				ProgressDialog myDialog = ProgressDialog.show(
//						SplashScreenActivity.this, "", "Loading", true);

				SharedPreferences sharedPreferences =  PreferenceManager
						.getDefaultSharedPreferences(SplashScreenActivity.this);
				
				if (!sharedPreferences.getBoolean(App.IS_INSTALLED, false)) {
					Intent intent = new Intent(SplashScreenActivity.this,
							GuideActivity.class);
					SplashScreenActivity.this.startActivity(intent);
	//				myDialog.dismiss();
					
				} else {
					Intent intent = new Intent(SplashScreenActivity.this,
							MainActivity.class);
					SplashScreenActivity.this.startActivity(intent);
				}
				SplashScreenActivity.this.finish();
			}

		}, 2000);// 3 Seconds
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
