/**
 * 
 */
package com.fn.taxclientportal.ui.fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.actionbarsherlock.app.SherlockFragment;
import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.fn.taxclientportal.ui.activity.R;
import com.fn.taxclientportal.ui.app.TaxConstants;

/**
 * @author luxiang
 *
 */
public class GlobalSummaryFragment extends SherlockFragment {
	private final String TAG = "HomeFragment";
	private WebView webView;

	

	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(
				R.layout.global_summary_layout, null);
		webView = (WebView) view.findViewById(R.id.webview);

		initData();
		return view;
	}

	public void initData() {
		final AQuery aquery = new AQuery(this.getActivity());
		aquery.ajax(TaxConstants.Url.ARTICLE_URL_PREFIX + "20131206000002", String.class,
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
