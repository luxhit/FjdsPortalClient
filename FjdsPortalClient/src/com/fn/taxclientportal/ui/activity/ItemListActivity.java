/**
 * 
 */
package com.fn.taxclientportal.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockActivity;
import com.fn.taxclientportal.support.rss.parse.RSSFeed;
import com.fn.taxclientportal.support.rss.util.WriteObjectFile;
import com.fn.taxclientportal.ui.adapter.ItemListAdapter;
import com.fn.taxclientportal.ui.app.TaxConstants;
import com.fn.taxclientportal.ui.util.RSSUtil;

/**
 * @author luxiang
 *
 */
public class ItemListActivity extends SherlockActivity {
	// Check if we refreshed
	private boolean isRefresh = false;
	// The adapter for the list
	private ListAdapter adapter;
	// The list to display it in
	private ListView list;
	// The RSSFeed of the site
	private RSSFeed feed;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Create a new ViewGroup for the fragment
		setContentView(R.layout.feed_list);
		// If we're above Honeycomb
		if (android.os.Build.VERSION.SDK_INT >= 11) {
			// Remove the icon from the ActionBar
			getActionBar().setDisplayShowHomeEnabled(false);
		}

		// Get feed from the passed bundle
		feed = (RSSFeed) new WriteObjectFile(this).readObject(RSSUtil
				.getFeedName());

		// Find the ListView we're using
		list = (ListView) findViewById(R.id.listView);
		// Set the vertical edges to fade when scrolling
		list.setVerticalFadingEdgeEnabled(true);

		// Create a new adapter
		adapter = new ItemListAdapter(this, feed);
		// Set the adapter to the list
		list.setAdapter(adapter);

		// Set on item click listener to the ListView
		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// Start the new activity and pass on the feed item
				// startActivity(new Intent(getBaseContext(),
				// PostActivity.class).putExtra("pos", arg2));

				Intent intent = new Intent(getBaseContext(),
						ItemDetailActivity.class);
				intent.putExtra(TaxConstants.Item.TITLE,
						((TextView) arg1.findViewById(R.id.title)).getText());
				intent.putExtra(TaxConstants.Item.PULISH_DATE,
						((TextView) arg1.findViewById(R.id.date)).getText());
				intent.putExtra(TaxConstants.Item.ID, arg1.getTag().toString());
				startActivity(intent);
			}
		});
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		// Exit instead of going to splash screen
		((BaseAdapter) adapter).notifyDataSetChanged();
	}

	@Override
	public void onResume() {
		super.onResume();
		// This is awful, but don't change it until I work out another way
		if (isRefresh) {
			feed = (RSSFeed) new WriteObjectFile(this).readObject(RSSUtil
					.getFeedName());
			adapter = new ItemListAdapter(ItemListActivity.this, feed);
			list.setAdapter(adapter);
			isRefresh = false;
		}
	}
}
