/**
 * 
 */
package com.fn.taxclientportal.ui.task;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.preference.PreferenceManager;

import com.fn.taxclientportal.support.rss.parse.DOMParser;
import com.fn.taxclientportal.support.rss.parse.RSSFeed;
import com.fn.taxclientportal.support.rss.util.WriteObjectFile;
import com.fn.taxclientportal.ui.activity.ItemListActivity;
import com.fn.taxclientportal.ui.util.RSSUtil;

/**
 * @author luxiang
 *
 */
public class LoadRSSFeed extends AsyncTask<Void, Void, Void> {

	// The parent context
	private Context parent;
	// Dialog displaying a loading message
	private ProgressDialog refreshDialog;
	// The RSSFeed object
	private RSSFeed feed;
	// The URL we're parsing from
	private String RSSFEEDURL;

	public LoadRSSFeed(Context c, String url) {
		// Set the parent
		parent = c;
		// Set the feed URL
		RSSFEEDURL = url;
	}

	@Override
	protected Void doInBackground(Void... params) {
		// Parse the RSSFeed and save the object
		try {
			feed = new DOMParser().parseXML(RSSFEEDURL);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPreExecute() {
		// Create a new dialog
//		refreshDialog = new ProgressDialog(new ContextThemeWrapper(parent,
//				R.style.AlertBox));
		refreshDialog = new ProgressDialog(this.parent);
		// Inform of the refresh
		refreshDialog.setMessage("加载中");
		// Spin the wheel whilst the dialog exists
		refreshDialog.setIndeterminate(false);
		// Don't exit the dialog when the screen is touched
		refreshDialog.setCanceledOnTouchOutside(false);
		// Don't exit the dialog when back is pressed
		refreshDialog.setCancelable(true);
		// Show the dialog
		refreshDialog.show();
	}

	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);
		// Write the feed object to a file for better caching
		new WriteObjectFile(parent).writeObject(feed, RSSUtil.getFeedName());
		// Set as loaded
		PreferenceManager.getDefaultSharedPreferences(parent).edit()
				.putBoolean("newsSetup", true).apply();
		// Dismiss the dialog
		refreshDialog.dismiss();
		// Start the main fragment control
		parent.startActivity(new Intent(parent, ItemListActivity.class));
	}
}
