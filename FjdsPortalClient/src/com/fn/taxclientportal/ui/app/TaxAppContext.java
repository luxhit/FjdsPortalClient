/**
 * 
 */
package com.fn.taxclientportal.ui.app;

import com.actionbarsherlock.app.ActionBar;

import android.app.Application;

/**
 * 全局变量
 * 
 * @author luxiang
 *
 */
public class TaxAppContext extends Application {

	public static String version;

	public static String[] mainImageFilePaths = {
			TaxConstants.Foler.IMAGES_FOLDER + "20140906_173330.jpg",
			TaxConstants.Foler.IMAGES_FOLDER + "20140906_173339.jpg",
			TaxConstants.Foler.IMAGES_FOLDER + "20140906_173351.jpg",
			TaxConstants.Foler.IMAGES_FOLDER + "20140906_173414.jpg" };

	public static String[] mainImageDescriptions = { "福建地税0", "福建地税1", "福建地税2",
			"福建地税3", "福建地税4" };

	public static int screenWidth;
	public static int screenHeight;

	public static ActionBar.Tab publicServiceTabSelectedTab;
}
