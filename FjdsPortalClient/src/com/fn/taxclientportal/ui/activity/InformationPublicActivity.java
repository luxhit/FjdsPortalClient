/**
 * 
 */
package com.fn.taxclientportal.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;

import com.actionbarsherlock.app.ActionBar;
import com.fn.taxclientportal.ui.app.TaxAppContext;
import com.fn.taxclientportal.ui.app.TaxConstants;
import com.fn.taxclientportal.ui.event.MainTabHandler;
import com.fn.taxclientportal.ui.fragment.GlobalSummaryFragment;
import com.fn.taxclientportal.ui.fragment.NoticeFragment;
import com.fn.taxclientportal.ui.fragment.TaxGuideFragment;

/**
 * @author luxiang
 *
 */
public class InformationPublicActivity extends TaxBasicFragmentActivity {

	private final String TAG = "InformationPublicFragment";
	private View view;
	private AutoScrollViewPager informationPublicAutoScrollViewPager;

	@SuppressLint("InflateParams")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		view = LayoutInflater.from(this).inflate(
				R.layout.information_public_fragment_layout, null);
		this.setContentView(view);
		informationPublicAutoScrollViewPager = (AutoScrollViewPager) view
				.findViewById(R.id.information_public_viewpager);

		ActionBar.Tab tab1 = this.getSupportActionBar().newTab()
				.setText(R.string.ip_global_summary);
		ActionBar.Tab tab2 = this.getSupportActionBar().newTab()
				.setText(R.string.ip_tax_guide);
		ActionBar.Tab tab3 = this.getSupportActionBar().newTab()
				.setText(R.string.ip_notice);

		MainTabHandler mth = new MainTabHandler(
				informationPublicAutoScrollViewPager);
		tab1.setTabListener(mth);
		tab2.setTabListener(mth);
		tab3.setTabListener(mth);
		this.getSupportActionBar().removeAllTabs();
		this.getSupportActionBar().addTab(tab1, 0);
		this.getSupportActionBar().addTab(tab2, 1);
		this.getSupportActionBar().addTab(tab3, 2);

		if (TaxAppContext.infomationPublicTabSelectedTab != null)
			this.getSupportActionBar().selectTab(
					TaxAppContext.infomationPublicTabSelectedTab);

		informationPublicAutoScrollViewPager
				.setAdapter(new InfomationPublicTabsPagerAdapter(this
						.getSupportFragmentManager()));

		informationPublicAutoScrollViewPager
				.setOnPageChangeListener(new OnPageChangeListener() {

					@Override
					public void onPageSelected(int arg0) {
						getSupportActionBar().setSelectedNavigationItem(arg0);
						TaxAppContext.infomationPublicTabSelectedTab = getSupportActionBar()
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
	}

	public class InfomationPublicTabsPagerAdapter extends
			FragmentStatePagerAdapter {

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			return super.instantiateItem(container, position);
		}

		public InfomationPublicTabsPagerAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.support.v4.app.FragmentPagerAdapter#getItem(int)
		 */
		@Override
		public Fragment getItem(int index) {
			switch (index) {
			case 0:
				return new GlobalSummaryFragment();
			case 1:
				return new TaxGuideFragment();
			case 2:
				return new NoticeFragment();
			}
			return new HomeFragment();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.support.v4.view.PagerAdapter#getCount()
		 */
		@Override
		public int getCount() {
			return TaxConstants.App.PUBLIC_SERVICE_TABS_COUNT;
		}

	}
}
