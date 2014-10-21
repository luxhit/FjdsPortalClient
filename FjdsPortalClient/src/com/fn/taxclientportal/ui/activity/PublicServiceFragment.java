/**
 * 
 */
package com.fn.taxclientportal.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragment;
import com.fn.taxclientportal.ui.activity.HomeFragment.HomeAdapter;
import com.fn.taxclientportal.ui.adapter.TabsPagerAdapter;
import com.fn.taxclientportal.ui.app.TaxAppContext;
import com.fn.taxclientportal.ui.event.MainTabHandler;

/**
 * @author luxiang
 *
 */
public class PublicServiceFragment extends SherlockFragment {

	private final String TAG = "PublicServiceFragment";
	private View view;
	private HomeAdapter myAdapter;
	private AutoScrollViewPager publicServiceAutoScrollViewPager;

	private FragmentManager fragmentManager;
	private ActionBar actionBar;

	public PublicServiceFragment(FragmentManager fragmentManager,
			ActionBar actionBar) {
		this.fragmentManager = fragmentManager;
		this.actionBar = actionBar;
	}

	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = LayoutInflater.from(getActivity()).inflate(
				R.layout.public_service_fragment_layout, null);
		publicServiceAutoScrollViewPager = (AutoScrollViewPager) view
				.findViewById(R.id.public_service_viewpager);

		ActionBar.Tab tab1 = actionBar.newTab().setText(R.string.mobile_portal);
		ActionBar.Tab tab2 = actionBar.newTab().setText(
				R.string.levied_interactive);
		ActionBar.Tab tab3 = actionBar.newTab().setText(
				R.string.public_platform);
		ActionBar.Tab tab4 = actionBar.newTab().setText(R.string.tax_platform);

		MainTabHandler mth = new MainTabHandler(
				publicServiceAutoScrollViewPager);
		tab1.setTabListener(mth);
		tab2.setTabListener(mth);
		tab3.setTabListener(mth);
		tab4.setTabListener(mth);
		actionBar.removeAllTabs();
		actionBar.addTab(tab1, 0);
		actionBar.addTab(tab2, 1);
		actionBar.addTab(tab3, 2);
		actionBar.addTab(tab4, 3);

		if (TaxAppContext.publicServiceTabSelectedTab != null)
			actionBar.selectTab(TaxAppContext.publicServiceTabSelectedTab);

		publicServiceAutoScrollViewPager.setAdapter(new TabsPagerAdapter(
				this.fragmentManager));

		publicServiceAutoScrollViewPager
				.setOnPageChangeListener(new OnPageChangeListener() {

					@Override
					public void onPageSelected(int arg0) {
						actionBar.setSelectedNavigationItem(arg0);
						TaxAppContext.publicServiceTabSelectedTab = actionBar
								.getSelectedTab();
					}

					@Override
					public void onPageScrolled(int arg0, float arg1, int arg2) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onPageScrollStateChanged(int arg0) {
						// TODO Auto-generated method stub

					}
				});

		// initData();
		return view;
	}

}
