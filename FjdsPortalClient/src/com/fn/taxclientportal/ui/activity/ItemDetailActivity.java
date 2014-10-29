package com.fn.taxclientportal.ui.activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.webkit.WebView;

import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.fn.taxclientportal.ui.app.TaxConstants;

public class ItemDetailActivity extends TaxBasicActivity {

	private static final String TAG = ItemDetailActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.item_detail_layout);

		Intent intent = this.getIntent();

		String title = intent.getStringExtra(TaxConstants.Item.TITLE);
		String pulisDate = intent.getStringExtra(TaxConstants.Item.PULISH_DATE);
		String url = intent.getStringExtra(TaxConstants.Item.URL);

		aquery.id(R.id.title_textview).text(title);
		aquery.id(R.id.date_textview).text(pulisDate);
		final WebView webView = aquery.id(R.id.content_webview).getWebView();
		webView.getSettings().setJavaScriptEnabled(true);

		aquery.id(R.id.loading_progress).visible();
		Log.i(TAG, "url:" + url);
		aquery.ajax(url, String.class,
				new AjaxCallback<String>() {
					@Override
					public void callback(String url, String html,
							AjaxStatus status) {
						Log.i("returnValue", html);
						JSONArray jsonArray;
						try {
							jsonArray = new JSONArray(html);

							String arContent;
							JSONObject json = jsonArray.getJSONObject(0);
							arContent = json.optString("arContent");
							StringBuffer page = new StringBuffer("<html><body>")
									.append(arContent).append("</body></html>");
							webView.loadDataWithBaseURL("x-data://base",
									page.toString(),
									TaxConstants.Web.CONTENT_TYPE,
									TaxConstants.Web.DEFAULT_ENCODING, null);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						aquery.id(R.id.loading_progress).gone();

					}
				});

	}
}
