/**
 * 
 */
package com.fn.taxclientportal.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.fn.taxclientportal.ui.activity.HomeFragment;
import com.fn.taxclientportal.ui.activity.LeviedInteractiveFragment;
import com.fn.taxclientportal.ui.activity.MobilePortalFragment;
import com.fn.taxclientportal.ui.activity.PublicPlaformFragment;
import com.fn.taxclientportal.ui.activity.TaxPlaformFragment;
import com.fn.taxclientportal.ui.app.TaxConstants;

/**
 * @author luxiang
 *
 */
public class TabsPagerAdapter extends FragmentStatePagerAdapter {
	

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		return super.instantiateItem(container, position);
	}

	public TabsPagerAdapter(FragmentManager fm) {
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
			return new MobilePortalFragment();
		case 1:
			return new LeviedInteractiveFragment();
		case 2:
			return new PublicPlaformFragment();
		case 3:
			return new TaxPlaformFragment();
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
