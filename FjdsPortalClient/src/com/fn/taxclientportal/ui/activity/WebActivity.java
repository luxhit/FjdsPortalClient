/**
 * 
 */
package com.fn.taxclientportal.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

/**
 * @author luxiang
 *
 */
public class WebActivity extends TaxBasicActivity {
	private WebView webview;
	private String url = "http://www.baidu.com";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.getSupportActionBar().setBackgroundDrawable(
				this.getResources().getDrawable(R.drawable.actionbar_bg_shape));

		// 关闭actionbar的icon
		this.getSupportActionBar().setDisplayUseLogoEnabled(false);
		View webLayout = getLayoutInflater().inflate(R.layout.web_layout, null);

		this.setContentView(webLayout);

		webview = aquery.id(R.id.webview).getWebView();

		open();
	}

	@SuppressLint("SetJavaScriptEnabled")
	public void open() {
		webview.getSettings().setLoadsImagesAutomatically(true);
		webview.getSettings().setJavaScriptEnabled(true);
		webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
		webview.loadUrl(url);

	}
}
