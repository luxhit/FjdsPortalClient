/**
 * 
 */
package com.fn.taxclientportal.ui.fragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.fn.taxclientportal.support.rss.parse.DOMParser;
import com.fn.taxclientportal.support.rss.parse.RSSFeed;
import com.fn.taxclientportal.support.rss.parse.RSSItem;
import com.fn.taxclientportal.ui.activity.ItemDetailActivity;
import com.fn.taxclientportal.ui.activity.R;
import com.fn.taxclientportal.ui.app.Item;
import com.fn.taxclientportal.ui.app.TaxConstants;
import com.fn.taxclientportal.ui.util.AppUtil;

/**
 * @author luxiang
 *
 */
public class TaxGuideFragment extends SherlockFragment {
	private final String TAG = "NoticeFragment";
	private TaxGuideExpandableListAdapter listAdapter;
	private ExpandableListView expListView;
	private List<String> listDataHeader;
	private Map<String, List<Item>> listDataChild;

	private List<String> loadedList = new ArrayList<String>();

	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.tax_guide_layout, null);
		expListView = (ExpandableListView) view.findViewById(R.id.elview);

		// preparing list data
		prepareListData();

		listAdapter = new TaxGuideExpandableListAdapter(this.getActivity(),
				listDataHeader, listDataChild);

		// setting list adapter
		expListView.setAdapter(listAdapter);

		expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

			@Override
			public void onGroupExpand(int groupPosition) {
				if (!loadedList.contains(groupPosition + "")) {
					loadedList.add(groupPosition + "");
					// Adding child data
					final List<Item> list = new ArrayList<Item>();

					new LoadRSSFeed(
							getActivity(),
							"http://www.fj-l-tax.gov.cn/rss.sp?act=getRssData&id=20131012755980&rssType=AR&start=1&len=10&containAttach=true",
							list, groupPosition).execute();
				}
			}
		});

		expListView.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {

				Intent intent = new Intent(getActivity(),
						ItemDetailActivity.class);
				intent.putExtra(TaxConstants.Item.TITLE,
						((TextView) v.findViewById(R.id.lblListItem)).getText());
				intent.putExtra(TaxConstants.Item.URL, v.getTag().toString());
				startActivity(intent);
				return true;
			}
		});
		return view;
	}

	/*
	 * Preparing the list data
	 */
	private void prepareListData() {
		if (Log.isLoggable(TAG, Log.DEBUG))
			Log.d(TAG, "prepareListData notice");
		listDataHeader = new ArrayList<String>();
		listDataChild = new HashMap<String, List<Item>>();

		// Adding child data
		listDataHeader.add("办税指南");
		listDataHeader.add("税务登记");
		listDataHeader.add("认定管理");
		listDataHeader.add("税源管理");
		listDataHeader.add("核定管理");
		listDataHeader.add("土地税源管理");
		listDataHeader.add("建安税源管理");
		listDataHeader.add("不动产税源管理");
		listDataHeader.add("证明管理");
		listDataHeader.add("发票管理");
		listDataHeader.add("税收优惠管理");
		listDataHeader.add("申报征收");
		listDataHeader.add("涉税申请");
		listDataHeader.add("申报征收");
		listDataHeader.add("纳税担保申请");
		listDataHeader.add("税务行政救济");
		listDataHeader.add("规费管理");
		listDataHeader.add("网上办税");
		listDataHeader.add("网上申报");
		listDataHeader.add("上传下载");
	}

	public class TaxGuideExpandableListAdapter extends
			BaseExpandableListAdapter {

		private Context _context;
		private List<String> _listDataHeader; // header titles
		// child data in format of header title, child title
		private Map<String, List<Item>> _listDataChild;

		public TaxGuideExpandableListAdapter(Context context,
				List<String> listDataHeader,
				Map<String, List<Item>> listChildData) {
			this._context = context;
			this._listDataHeader = listDataHeader;
			this._listDataChild = listChildData;
		}

		public void addChildList(int groupPosition, List<Item> childList) {
			_listDataChild.put(listDataHeader.get(groupPosition), childList);

			this.notifyDataSetChanged();
		}

		@Override
		public Object getChild(int groupPosition, int childPosititon) {
			return this._listDataChild.get(
					this._listDataHeader.get(groupPosition))
					.get(childPosititon);
		}

		@Override
		public long getChildId(int groupPosition, int childPosition) {
			return childPosition;
		}

		@Override
		public View getChildView(int groupPosition, final int childPosition,
				boolean isLastChild, View convertView, ViewGroup parent) {
			Item item = (Item) getChild(groupPosition, childPosition);
			final String childText = item.getTitle();

			if (convertView == null) {
				LayoutInflater infalInflater = (LayoutInflater) this._context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = infalInflater.inflate(R.layout.list_group_item,
						null);
			}

			TextView txtListChild = (TextView) convertView
					.findViewById(R.id.lblListItem);

			txtListChild.setText(childText);
			String url = item.getUrl();
			if (url.indexOf("http:") == -1) {
				String id = AppUtil.getArticleId(url);
				url = TaxConstants.Url.ARTICLE_URL_PREFIX + id;
			}

			convertView.setTag(url);
			return convertView;
		}

		@Override
		public int getChildrenCount(int groupPosition) {
			if (this._listDataChild
					.get(this._listDataHeader.get(groupPosition)) == null)
				return 0;
			else
				return this._listDataChild.get(
						this._listDataHeader.get(groupPosition)).size();
		}

		@Override
		public Object getGroup(int groupPosition) {
			return this._listDataHeader.get(groupPosition);
		}

		@Override
		public int getGroupCount() {
			return this._listDataHeader.size();
		}

		@Override
		public long getGroupId(int groupPosition) {
			return groupPosition;
		}

		@Override
		public View getGroupView(int groupPosition, boolean isExpanded,
				View convertView, ViewGroup parent) {
			String headerTitle = (String) getGroup(groupPosition);
			if (convertView == null) {
				LayoutInflater infalInflater = (LayoutInflater) this._context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = infalInflater.inflate(R.layout.list_group, null);
			}

			TextView lblListHeader = (TextView) convertView
					.findViewById(R.id.lblListHeader);
			lblListHeader.setTypeface(null, Typeface.BOLD);
			lblListHeader.setText(headerTitle);

			return convertView;
		}

		@Override
		public boolean hasStableIds() {
			return false;
		}

		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition) {
			return true;
		}
	}

	public class LoadRSSFeed extends AsyncTask<Void, Void, Void> {

		// The parent context
		private Context parent;
		// Dialog displaying a loading message
		private ProgressDialog refreshDialog;
		// The RSSFeed object
		private RSSFeed feed;
		// The URL we're parsing from
		private String RSSFEEDURL;

		private List<Item> listDataChild;
		
		private int groupPosition;

		public LoadRSSFeed(Context c, String url, List<Item> listDataChild, int groupPostion) {
			// Set the parent
			parent = c;
			// Set the feed URL
			RSSFEEDURL = url;

			this.listDataChild = listDataChild;
			this.groupPosition = groupPostion;
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
			// refreshDialog = new ProgressDialog(new
			// ContextThemeWrapper(parent,
			// R.style.AlertBox));
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

			// Dismiss the dialog
			refreshDialog.dismiss();
			// Start the main fragment control

			for (int i = 0; i < feed.getItemCount(); i++) {
				RSSItem rssItem = feed.getItem(i);
				listDataChild
						.add(new Item(rssItem.getTitle(), rssItem.getURL()));
			}
			listAdapter.addChildList(groupPosition, listDataChild);
		}
	}
}
