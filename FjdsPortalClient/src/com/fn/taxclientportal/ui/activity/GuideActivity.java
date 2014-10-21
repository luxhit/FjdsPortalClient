/**
 * 
 */
package com.fn.taxclientportal.ui.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.fn.taxclientportal.ui.app.TaxConstants;

/**
 * 导航UI
 * 
 * @author luxiang
 *
 */
public class GuideActivity extends TaxBasicActivity {
	private ViewPager mViewPager; // 视图导航页

	private List<View> mListViews; // 导航视图集合
	private ImageView[] guideDots;

	private int[] images = { R.drawable.guide_page1, R.drawable.guide_page2,
			R.drawable.guide_page3 };

	private int currentIndex; // 当前顺序
	private Button startBtn;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.guide_layout);

		this.getSupportActionBar().hide();

		// initialize start
		this.createViews();
		this.createDots();
		// initialize end
	}

	private void createViews() {
		mListViews = new ArrayList<View>(3);

		mViewPager = (ViewPager) aquery.id(R.id.viewPager).getView();

		for (int image : images) {
			ImageView iv = new ImageView(GuideActivity.this);
			iv.setBackgroundResource(image);

			mListViews.add(iv);
		}

		View guideStartView = LayoutInflater.from(GuideActivity.this).inflate(
				R.layout.guide_start_layout, null);
		mListViews.add(guideStartView);

		startBtn = (Button) guideStartView.findViewById(R.id.start_btn);
		startBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				aquery.id(R.id.loading_progress).visible();
				Intent intent = new Intent(GuideActivity.this,
						MainActivity.class);
				startActivity(intent);
				GuideActivity.this.finish();
				// save installed completely
				SharedPreferences.Editor editor = PreferenceManager
						.getDefaultSharedPreferences(GuideActivity.this).edit();
				
				editor.putBoolean(TaxConstants.App.IS_INSTALLED, true);
				editor.commit();
			}
		});

		mViewPager.setAdapter(new GuildPagerAdapter());

		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				doCurrentDotChange(arg0);
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
	}

	private void createDots() {
		// 导航dots
		LinearLayout layout = (LinearLayout) aquery.id(R.id.guide_dots)
				.getView();

		// dots数组
		guideDots = new ImageView[mListViews.size()];

		// 初始化dot，默认为非选定
		if (mListViews.size() > 1) {
			for (int i = 0; i < mListViews.size(); i++) {
				guideDots[i] = (ImageView) layout.getChildAt(i);
				if (i == 0) {
					currentIndex = 0;
					guideDots[i].setSelected(true);
				} else {
					guideDots[i].setSelected(false);
				}
			}
		}
	}

	private void doCurrentDotChange(int position) {
		if (position < 0 || position == mListViews.size()
				|| currentIndex == position) {
			return;
		}

		guideDots[position].setSelected(true);
		guideDots[currentIndex].setSelected(false);

		currentIndex = position;
	}

	private class GuildPagerAdapter extends PagerAdapter {
		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView(mListViews.get(arg1));

		}

		@Override
		public int getCount() {
			return mListViews.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}

		@Override
		public int getItemPosition(Object object) {
			return super.getItemPosition(object);
		}

		@Override
		public Object instantiateItem(View collection, int position) {
			((ViewPager) collection).addView(mListViews.get(position), 0);

			return mListViews.get(position);
		}
	}
}
