/**
 * 
 */
package com.fn.taxclientportal.ui.activity;

import android.annotation.SuppressLint;

import com.actionbarsherlock.app.SherlockActivity;
import com.androidquery.AQuery;

/**
 * UI基类
 * 
 * @author luxiang
 * @version 1.0
 */
public class TaxBasicActivity extends SherlockActivity {
	protected AQuery aquery;

	@SuppressLint("InflateParams")
	@Override
	protected void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		aquery = new AQuery(this);

		
		// abar.setDisplayHomeAsUpEnabled(true);
	};

}
