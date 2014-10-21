/**
 * 
 */
package com.fn.taxclientportal.ui.activity;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.androidquery.AQuery;
import com.fn.taxclientportal.ui.app.TaxAppContext;
import com.fn.taxclientportal.ui.transformer.ZoomOutPageTransformer;
import com.fn.taxclientportal.ui.util.AppUtil;

/**
 * 主Activiy
 * 
 * @author luxiang
 * @version 1.0
 *
 */
public class MainActivity extends SherlockFragmentActivity {
	protected static final String TAG = MainActivity.class.getSimpleName();

	private AutoScrollViewPager mImageViewPager; // 图片导航页

	private List<ImageView> mListImageViews;
	private ImageView[] imageDots;
	private int currentIndex; // 当前图片顺序

	private AQuery aquery;

	private int[] menuTextViews = { R.id.home_textview,
			R.id.publicservice_textview, R.id.mobiletax_textview,
			R.id.usercenter_textview };

	private int[] menuImageViews = { R.id.home_imageview,
			R.id.publicservice_imageview, R.id.mobiletax_imageview,
			R.id.usercenter_imageview };

	private boolean isPublicServiceTabCreated = false;

	// 快速双击退出App
	private boolean doublePressBackToExitAtOnce = false;

	// 图片切换间隔时间
	private int imageScrollInterval = 5000;

	@SuppressLint("InflateParams")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		aquery = new AQuery(this);

		// 标题背景渐变
		// this.getSupportActionBar()
		// .setDisplayOptions(R.style.Theme_ActionBar_Styled);
		this.getSupportActionBar().setBackgroundDrawable(
				this.getResources().getDrawable(R.drawable.actionbar_bg_shape));

		// 关闭actionbar的icon
		this.getSupportActionBar().setDisplayUseLogoEnabled(false);

		this.setContentView(R.layout.main_layout);

		// 默认选择首页菜单
		aquery.id(R.id.home_frameLayout).click();

		// 标题头居中文本
		ActionBar abar = this.getSupportActionBar();
		View viewActionBar = getLayoutInflater().inflate(
				R.layout.actionbar_title_layout, null);
		ActionBar.LayoutParams params = new ActionBar.LayoutParams(
				ActionBar.LayoutParams.WRAP_CONTENT,
				ActionBar.LayoutParams.MATCH_PARENT, Gravity.CENTER);
		TextView textviewTitle = (TextView) viewActionBar
				.findViewById(R.id.actionbar_textview);
		textviewTitle.setText(R.string.app_name);
		abar.setCustomView(viewActionBar, params);
		abar.setDisplayShowCustomEnabled(true);
		abar.setDisplayShowTitleEnabled(false);

		mImageViewPager = (AutoScrollViewPager) aquery.id(R.id.image_viewpager)
				.getView();
		// 5秒滚动变换一次
		mImageViewPager.startAutoScroll(imageScrollInterval);
		mImageViewPager.setInterval(imageScrollInterval);
		mImageViewPager.setScrollDurationFactor(5);
		mImageViewPager.setSlideBorderMode(AutoScrollViewPager.SLIDE_BORDER_MODE_TO_PARENT);
		mImageViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
		// 初始化第一张图片简介
		aquery.id(R.id.imageDescriptionView).text(
				TaxAppContext.mainImageDescriptions[0]);
		
		mImageViewPager.getViewTreeObserver().addOnGlobalLayoutListener(
				new ViewTreeObserver.OnGlobalLayoutListener() {
					@Override
					public void onGlobalLayout() {
						// Remove it here unless you want to get this callback
						// for EVERY
						// layout pass, which can get you into infinite loops if
						// you ever
						// modify the layout from within this method.
						mImageViewPager.getViewTreeObserver()
								.removeGlobalOnLayoutListener(this);

						initImageViews();
						initDots();

					}
				});

	}

	/**
	 * 初始化图片点
	 */
	private void initDots() {
		// 图片dots
		LinearLayout layout = (LinearLayout) aquery.id(R.id.images_dots)
				.getView();

		// dots数组
		imageDots = new ImageView[mListImageViews.size()];

		// 初始化dot，默认为非选定
		if (mListImageViews.size() > 1) {
			for (int i = 0; i < mListImageViews.size(); i++) {
				imageDots[i] = (ImageView) layout.getChildAt(i);
				if (i == 0) {
					currentIndex = 0;
					imageDots[i].setSelected(true);
				} else {
					imageDots[i].setSelected(false);
				}
			}
		}
	}

	@Override
	public void onBackPressed() {
		Log.d(TAG, "the flag of double press back to exit is:"
				+ this.doublePressBackToExitAtOnce);
		// 双击回退键退出App
		if (this.doublePressBackToExitAtOnce) {
			Log.i(TAG, "double press back to exit at once");
			super.onBackPressed();
			return;
		}

		// 更新开关变量
		this.doublePressBackToExitAtOnce = true;

		// 提示
		Toast.makeText(this, R.string.press_again_to_exit, Toast.LENGTH_LONG)
				.show();

		// 2秒后重置开关
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				doublePressBackToExitAtOnce = false;
			}
		}, 2000L);
	}

	/**
	 * 初始化图片视图
	 */
	@SuppressLint("ClickableViewAccessibility")
	private void initImageViews() {
		aquery.id(R.id.image_viewpager).getView();

		mListImageViews = new ArrayList<ImageView>(5);
		/*
		 * final String[] mainImageFilePaths = TaxAppContext.mainImageFilePaths;
		 * 
		 * for (String imageFilePath : mainImageFilePaths) { ImageView iv = new
		 * ImageView(MainActivity.this); Log.d(TAG, "imageFilePath:" +
		 * imageFilePath);
		 * 
		 * if (imageFilePath != null) { File imgFile = new File(imageFilePath);
		 * if (imgFile.exists()) {
		 * 
		 * // AppUtil.scaleImage(imageFilePath, //
		 * mImageViewPager.getMeasuredWidth(), //
		 * mImageViewPager.getMeasuredHeight(), imageFilePath); Log.i(TAG,
		 * "width:" + mImageViewPager.getMeasuredWidth() + ", height:" +
		 * mImageViewPager.getMeasuredHeight()); Bitmap bitmapOriginal =
		 * BitmapFactory.decodeFile(imgFile .getAbsolutePath());
		 * 
		 * Log.i(TAG, "image width:" + bitmapOriginal.getWidth() + ", height:" +
		 * bitmapOriginal.getHeight()); iv.setScaleType(ScaleType.CENTER);
		 * iv.setImageBitmap(AppUtil.adaptive(bitmapOriginal,
		 * mImageViewPager.getMeasuredWidth(),
		 * mImageViewPager.getMeasuredHeight())); // bitmapOriginal.recycle(); }
		 * // iv.setBackgroundDrawable(Drawable.createFromPath(imageFilePath));
		 * }
		 * 
		 * mListImageViews.add(iv); }
		 */
		int[] imageIds = { R.drawable.main_image0, R.drawable.main_image1,
				R.drawable.main_image2, R.drawable.main_image3,
				R.drawable.main_image4 };
		for (int imageId : imageIds) {
			ImageView iv = new ImageView(MainActivity.this);
			Bitmap bitmapOriginal = AppUtil.readBitMap(this.getBaseContext(),
					imageId);

			iv.setImageBitmap(AppUtil.adaptive(bitmapOriginal,
					mImageViewPager.getMeasuredWidth(),
					mImageViewPager.getMeasuredHeight()));

			mListImageViews.add(iv);
		}
		mImageViewPager.setAdapter(new ImageViewPagerAdapter());

		mImageViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				doCurrentDotChange(arg0);
				
				// 更新图片简介
				aquery.id(R.id.imageDescriptionView).text(
						TaxAppContext.mainImageDescriptions[arg0]);
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

	private void doCurrentDotChange(int position) {
		if (position < 0 || position == mListImageViews.size()
				|| currentIndex == position) {
			return;
		}

		imageDots[position].setSelected(true);
		imageDots[currentIndex].setSelected(false);

		currentIndex = position;
	}

	private class ImageViewPagerAdapter extends PagerAdapter {
		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView(mListImageViews.get(arg1));

		}

		@Override
		public int getCount() {
			return mListImageViews.size();
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
			((ViewPager) collection).addView(mListImageViews.get(position), 0);

			return mListImageViews.get(position);
		}
	}

	public void homeClick(View view) {
		aquery.id(R.id.image_viewpager_layout).visible();

		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);

		this.changeMenuWidgetState(0);
		HomeFragment homeFragment = new HomeFragment();
		android.support.v4.app.FragmentTransaction fragmentTransaction = this
				.getSupportFragmentManager().beginTransaction();
		fragmentTransaction.replace(R.id.frame_content, homeFragment);
		fragmentTransaction.commit();

	}

	public void publicServiceClick(View view) {
		aquery.id(R.id.image_viewpager_layout).visibility(View.GONE);

		this.changeMenuWidgetState(1);

		final ActionBar actionBar = getSupportActionBar();

		// actionBar.setDisplayShowHomeEnabled(true);
		// actionBar.setLogo(null); // forgot why this one but it helped

		// View homeIcon = findViewById(Build.VERSION.SDK_INT >=
		// Build.VERSION_CODES.HONEYCOMB ? android.R.id.home
		// : R.id.abs__home);
		// ((View) homeIcon.getParent()).setVisibility(View.GONE);
		// ((View) homeIcon).setVisibility(View.GONE);

		// actionBar.setDisplayShowTitleEnabled(false);

		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		// actionBar.setStackedBackgroundDrawable(this.getResources().getDrawable(
		// R.drawable.ab_stacked_bg));
		// Fragment.instantiate(this, fname);
		PublicServiceFragment publicServiceFragment = new PublicServiceFragment(
				this.getSupportFragmentManager(), this.getSupportActionBar());
		android.support.v4.app.FragmentTransaction fragmentTransaction = this
				.getSupportFragmentManager().beginTransaction();
		fragmentTransaction.replace(R.id.frame_content, publicServiceFragment,
				"publicServiceFragment");
		fragmentTransaction.commit();

	}

	public void mobileTaxClick(View view) {
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);

		aquery.id(R.id.image_viewpager_layout).visibility(View.GONE);

		this.changeMenuWidgetState(2);
		MobileTaxFragment mobileTaxFragment = new MobileTaxFragment();
		android.support.v4.app.FragmentTransaction fragmentTransaction = this
				.getSupportFragmentManager().beginTransaction();
		fragmentTransaction.replace(R.id.frame_content, mobileTaxFragment);
		fragmentTransaction.commit();
	}

	public void userCenterClick(View view) {
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);

		this.changeMenuWidgetState(3);

	}

	/**
	 * 变更菜单控件选择状态
	 */
	private void changeMenuWidgetState(int selectCondition) {
		for (int id : menuTextViews) {
			if (menuTextViews[selectCondition] == id) {
				aquery.id(id).enabled(true);
			} else {
				aquery.id(id).enabled(false);
			}
		}
		for (int id : menuImageViews) {
			if (menuImageViews[selectCondition] == id) {
				aquery.id(id).enabled(true);
			} else {
				aquery.id(id).enabled(false);
			}
		}
	}

	/**
	 * 创建菜单
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.menu.option_menu, menu);

		return super.onCreateOptionsMenu(menu);
	}

	/**
	 * 菜单项选择处理
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.exit:
			this.showExitConfirmDialog();
			return true;
		case R.id.about:
			this.showAboutInfoDialog();
			return true;
		default:
			return false;
		}
	}

	/**
	 * 显示“退出”确认对话窗体
	 */
	private void showExitConfirmDialog() {
		final AlertDialog.Builder builder = new AlertDialog.Builder(this);

		builder.setTitle(R.string.confirm)
				.setMessage(R.string.option_menu_whether_or_not_exit_message)
				.setCancelable(true)
				.setNegativeButton(R.string.cancel, new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				}).setPositiveButton(R.string.confirm, new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {

						doExit();
					}
				}).show();
	}

	/**
	 * 退出
	 */
	protected void doExit() {
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
	}

	/**
	 * 显示“关于”信息窗体
	 */
	private void showAboutInfoDialog() {
		final AlertDialog.Builder builder = new AlertDialog.Builder(this);

		builder.setTitle(R.string.about)
				.setMessage(R.string.option_menu_about_info_message)
				.setPositiveButton(R.string.confirm, new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				}).show();
	}
}
