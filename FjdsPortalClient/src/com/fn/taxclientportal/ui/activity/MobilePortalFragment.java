/**
 * 
 */
package com.fn.taxclientportal.ui.activity;

import static com.fn.taxclientportal.ui.app.TaxConstants.App.MOBILE_PORTAL_GUIDE;
import static com.fn.taxclientportal.ui.app.TaxConstants.App.MOBILE_PORTAL_HUMAN_INFO;
import static com.fn.taxclientportal.ui.app.TaxConstants.App.MOBILE_PORTAL_NEWS;
import static com.fn.taxclientportal.ui.app.TaxConstants.App.MOBILE_PORTAL_NOTICE;
import static com.fn.taxclientportal.ui.app.TaxConstants.App.MOBILE_PORTAL_ORG_INCOME;
import static com.fn.taxclientportal.ui.app.TaxConstants.App.MOBILE_PORTAL_PLAN_SUMMARY;
import static com.fn.taxclientportal.ui.app.TaxConstants.App.MOBILE_PORTAL_POLICY;
import static com.fn.taxclientportal.ui.app.TaxConstants.App.MOBILE_PORTAL_SUMMARY;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.fn.taxclientportal.ui.app.Item;
import com.fn.taxclientportal.ui.app.TaxAppContext;
import com.fn.taxclientportal.ui.app.TaxConstants;
import com.fn.taxclientportal.ui.task.LoadRSSFeed;

/**
 * @author luxiang
 *
 */
public class MobilePortalFragment extends SherlockFragment {
	private final String TAG = "MobilePortalFragment";
	private MobilePortal myAdapter;

	private GridView gridView;

	private int[] images = { R.drawable.receipt_query,
			R.drawable.receipt_getawards, R.drawable.tax_firm_mix,
			R.drawable.tax_12366_advisory, R.drawable.tax_weixin,
			R.drawable.policy_law, R.drawable.policy_law, R.drawable.policy_law };
	private String[] titles = { "新闻动态", "全局概括", "办税指南", "通知公告", "政策法规", "组织收入",
			"计划总结", "人事信息" };
	private int[] ids = { MOBILE_PORTAL_NEWS, MOBILE_PORTAL_SUMMARY,
			MOBILE_PORTAL_GUIDE, MOBILE_PORTAL_NOTICE, MOBILE_PORTAL_POLICY,
			MOBILE_PORTAL_ORG_INCOME, MOBILE_PORTAL_PLAN_SUMMARY,
			MOBILE_PORTAL_HUMAN_INFO };

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.public_service_grid_layout,
				container, false);

		gridView = (GridView) rootView
				.findViewById(R.id.public_service_gridview);
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// Intent intent = new Intent(MobilePortalFragment.this
				// .getActivity().getBaseContext(), WebActivity.class);
				// startActivity(intent);
				Item item = (Item) view.getTag();
				switch (Integer.parseInt(item.getId())) {
				case MOBILE_PORTAL_NEWS:
					new LoadRSSFeed(
							getActivity(),
							"http://www.fj-l-tax.gov.cn/rss.sp?act=getRssData&id=20131012247341&rssType=AR&start=1&len=10&containAttach=true")
							.execute();
					break;
				case MOBILE_PORTAL_SUMMARY:
					new LoadRSSFeed(
							getActivity(),
							"http://www.fj-l-tax.gov.cn/rss.sp?act=getRssData&id=20131012247341&rssType=AR&start=1&len=10&containAttach=true")
							.execute();

					break;
				case MOBILE_PORTAL_GUIDE:
					new LoadRSSFeed(
							getActivity(),
							"http://www.fj-l-tax.gov.cn/rss.sp?act=getRssData&id=20131012755980&rssType=AR&start=1&len=10&containAttach=true")
							.execute();
				case MOBILE_PORTAL_POLICY:
					new LoadRSSFeed(
							getActivity(),
							"http://www.fj-l-tax.gov.cn/rss.sp?act=getRssData&id=2014012214000051&rssType=AR&start=1&len=10&containAttach=true")
							.execute();
					break;
					default :
						Intent i = new Intent(Intent.ACTION_SEND);
						i.setType("message/rfc822"); // 真机
						i.putExtra(Intent.EXTRA_EMAIL,
								new String[] { "fjds2014@126.com" });
						i.putExtra(Intent.EXTRA_SUBJECT, "test");
						i.putExtra(Intent.EXTRA_TEXT, "test");
						try {
							TaxAppContext.currentActivity().startActivity(Intent.createChooser(i, "发送错误报告"));
					    } catch (android.content.ActivityNotFoundException ex) {
					        Toast.makeText(TaxAppContext.currentActivity(), "No email clients installed.", Toast.LENGTH_SHORT).show();
					    }
				}

			}
		});
		// 关闭滚动条
		gridView.setVerticalScrollBarEnabled(false);
		this.initData();
		return rootView;
	}

	public void initData() {
		Log.d(TAG, "init data");
		List<Map<String, Object>> list = getData();
		myAdapter = new MobilePortal(list);
		gridView.setAdapter(myAdapter);
		myAdapter.notifyDataSetChanged();
	}

	public List<Map<String, Object>> getData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < images.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("icon", images[i]);
			map.put("title", titles[i]);
			map.put("id", ids[i]);
			list.add(map);
		}
		return list;
	}

	class MobilePortal extends BaseAdapter {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		public MobilePortal(List<Map<String, Object>> list) {
			this.list = list;
		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int arg0) {
			return list.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			return arg0;
		}

		@SuppressLint("InflateParams")
		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			View view = null;
			if (null == arg1) {
				view = LayoutInflater.from(getActivity()).inflate(
						R.layout.grid_item_layout, null);
			} else {
				view = arg1;
			}
			ImageView image = (ImageView) view.findViewById(R.id.itemImage);
			TextView text = (TextView) view.findViewById(R.id.itemText);
			Item item = new Item();
			image.setImageResource((Integer) list.get(arg0).get("icon"));

			text.setText(list.get(arg0).get("title").toString());

			item.setTitle(list.get(arg0).get("title").toString());
			item.setId(list.get(arg0).get("id").toString());
			view.setTag(item);

			return view;
		}

	}

}
