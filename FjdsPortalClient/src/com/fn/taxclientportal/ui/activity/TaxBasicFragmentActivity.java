/**
 * 
 */
package com.fn.taxclientportal.ui.activity;

import android.annotation.SuppressLint;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.androidquery.AQuery;
import com.fn.taxclientportal.ui.app.TaxAppContext;

/**
 * UI基类
 * 
 * @author luxiang
 * @version 1.0
 */
public class TaxBasicFragmentActivity extends SherlockFragmentActivity {
	protected AQuery aquery;

	@SuppressLint("InflateParams")
	@Override
	protected void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		aquery = new AQuery(this);

		TaxAppContext.addActivity(this);
		// abar.setDisplayHomeAsUpEnabled(true);
	};

	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		TaxAppContext.removeActivity(this);
	}
}
