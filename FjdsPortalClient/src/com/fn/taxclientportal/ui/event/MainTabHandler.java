package com.fn.taxclientportal.ui.event;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;

import com.actionbarsherlock.app.ActionBar.Tab;



public class MainTabHandler implements
		com.actionbarsherlock.app.ActionBar.TabListener {
	private ViewPager viewpager;

	public MainTabHandler(ViewPager viewpager) {
		this.viewpager = viewpager;
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		viewpager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

}
