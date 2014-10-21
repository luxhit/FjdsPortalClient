/**
 * 
 */
package com.fn.taxclientportal.ui.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;

/**
 * @author luxiang
 *
 */
public class MobileTaxFragment extends SherlockFragment {
	private final String TAG = "HomeFragment";
	private View view;
	private MobileTaxAdapter myAdapter;
	private GridView gridView;

	private int[] images = { R.drawable.basic_info, R.drawable.declare_case,
			R.drawable.receipt_case, R.drawable.tax_progress,
			R.drawable.tax_declare, R.drawable.tax_firm_mix };
	private String[] titles = { "基本信息", "申报情况", "发票情况", "涉税进度", "纳税申报", "税款缴纳",
			"税企通" };

	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(
				R.layout.public_service_fragment_layout, null);
		gridView = (GridView) view.findViewById(R.id.public_service_gridview);

		// 关闭滚动条
		gridView.setVerticalScrollBarEnabled(false);
		initData();
		return view;
	}

	public void initData() {
		Log.d(TAG, "init data");
		List<Map<String, Object>> list = getData();
		myAdapter = new MobileTaxAdapter(list);
		gridView.setAdapter(myAdapter);
		myAdapter.notifyDataSetChanged();
	}

	public List<Map<String, Object>> getData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < images.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("icon", images[i]);
			map.put("title", titles[i]);
			map.put("activityName", "name" + i);
			list.add(map);
		}
		return list;
	}

	class MobileTaxAdapter extends BaseAdapter {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		public MobileTaxAdapter(List<Map<String, Object>> list) {
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
			image.setImageResource((Integer) list.get(arg0).get("icon"));
			text.setText((String) list.get(arg0).get("title"));
			view.setTag(list.get(arg0).get("activityName"));
			return view;
		}

	}
}
