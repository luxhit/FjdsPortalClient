package com.fn.taxclientportal.ui.app;

import static com.fn.taxclientportal.ui.app.TaxConstants.Mail.FJDS_MAIL_ACCOUNT;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.StatFs;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.fn.taxclientportal.support.mail.Mail;
import com.fn.taxclientportal.ui.activity.R;

public class ErrorReporter implements Thread.UncaughtExceptionHandler {
	private static final String TAG = ErrorReporter.class.getSimpleName();

	private String versionName;
	private String packageName;
	private String filePath;
	private String phoneModel;
	private String androidVersion;
	private String Board;
	private String Brand;
	// String CPU_ABI;
	private String device;
	private String display;
	private String fingerPrint;
	private String host;
	private String iD;
	private String Model;
	private String Product;
	private String tags;
	private long time;
	private String type;
	private String user;

	// private Thread.UncaughtExceptionHandler previousHandler;

	private Context appContext;

	public ErrorReporter(Context appContext) {
		super();
		// previousHandler = Thread.getDefaultUncaughtExceptionHandler();
		Thread.setDefaultUncaughtExceptionHandler(this);
		this.appContext = appContext;

		this.fetchInformations();
	}

	public long getAvailableInternalMemorySize() {
		File path = Environment.getDataDirectory();
		StatFs stat = new StatFs(path.getPath());
		long blockSize = stat.getBlockSize();
		long availableBlocks = stat.getAvailableBlocks();
		return availableBlocks * blockSize;
	}

	public long getTotalInternalMemorySize() {
		File path = Environment.getDataDirectory();
		StatFs stat = new StatFs(path.getPath());
		long blockSize = stat.getBlockSize();
		long totalBlocks = stat.getBlockCount();
		return totalBlocks * blockSize;
	}

	public void fetchInformations() {
		Context context = appContext;
		PackageManager pm = context.getPackageManager();
		try {
			PackageInfo pi;
			// Version
			pi = pm.getPackageInfo(context.getPackageName(), 0);
			versionName = pi.versionName;
			// Package name
			packageName = pi.packageName;
			// Files dir for storing the stack traces
			filePath = context.getFilesDir().getAbsolutePath();
			// Device model
			phoneModel = android.os.Build.MODEL;
			// Android version
			androidVersion = android.os.Build.VERSION.RELEASE;

			Board = android.os.Build.BOARD;
			Brand = android.os.Build.BRAND;
			// CPU_ABI = android.os.Build.;
			device = android.os.Build.DEVICE;
			display = android.os.Build.DISPLAY;
			fingerPrint = android.os.Build.FINGERPRINT;
			host = android.os.Build.HOST;
			iD = android.os.Build.ID;
			// Manufacturer = android.os.Build.;
			Model = android.os.Build.MODEL;
			Product = android.os.Build.PRODUCT;
			tags = android.os.Build.TAGS;
			time = android.os.Build.TIME;
			type = android.os.Build.TYPE;
			user = android.os.Build.USER;

		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
	}

	public String createInformationString() {
		String ReturnVal = "";
		ReturnVal += "Version : " + versionName;
		ReturnVal += "\n";
		ReturnVal += "Package : " + packageName;
		ReturnVal += "\n";
		ReturnVal += "FilePath : " + filePath;
		ReturnVal += "\n";
		ReturnVal += "Phone Model" + phoneModel;
		ReturnVal += "\n";
		ReturnVal += "Android Version : " + androidVersion;
		ReturnVal += "\n";
		ReturnVal += "Board : " + Board;
		ReturnVal += "\n";
		ReturnVal += "Brand : " + Brand;
		ReturnVal += "\n";
		ReturnVal += "Device : " + device;
		ReturnVal += "\n";
		ReturnVal += "Display : " + display;
		ReturnVal += "\n";
		ReturnVal += "Finger Print : " + fingerPrint;
		ReturnVal += "\n";
		ReturnVal += "Host : " + host;
		ReturnVal += "\n";
		ReturnVal += "ID : " + iD;
		ReturnVal += "\n";
		ReturnVal += "Model : " + Model;
		ReturnVal += "\n";
		ReturnVal += "Product : " + Product;
		ReturnVal += "\n";
		ReturnVal += "Tags : " + tags;
		ReturnVal += "\n";
		ReturnVal += "Time : " + time;
		ReturnVal += "\n";
		ReturnVal += "Type : " + type;
		ReturnVal += "\n";
		ReturnVal += "User : " + user;
		ReturnVal += "\n";
		ReturnVal += "Total Internal memory : " + getTotalInternalMemorySize();
		ReturnVal += "\n";
		ReturnVal += "Available Internal memory : "
				+ getAvailableInternalMemorySize();
		ReturnVal += "\n";

		return ReturnVal;
	}

	public void uncaughtException(Thread t, Throwable e) {
		Log.e(TAG, e.getMessage(), e);
		final StringBuffer report = new StringBuffer();
		Date curDate = new Date();
		report.append("Error Report collected on : " + curDate.toString());
		report.append("\n");
		report.append("\n");
		report.append("Informations :");
		report.append("\n");
		report.append("==============");
		report.append("\n");
		report.append("\n");
		report.append(createInformationString());

		report.append("\n\n");
		report.append("Stack : \n");
		report.append("======= \n");
		String stacktrace = Log.getStackTraceString(e);
		report.append(stacktrace);
		// final Writer result = new StringWriter();
		// final PrintWriter printWriter = new PrintWriter(result);
		// e.printStackTrace(printWriter);
		// String stacktrace = result.toString();
		// Reportstacktrace;
		//
		// Report"\n";
		// Report"Cause : \n";
		// Report"======= \n";
		//
		// // If the exception was thrown in a background thread inside
		// // AsyncTask, then the actual exception can be found with getCause
		// Throwable cause = e.getCause();
		// while (cause != null) {
		// cause.printStackTrace(printWriter);
		// Reportresult.toString();
		// cause = cause.getCause();
		// }
		// printWriter.close();
		report.append("****  End of current Report ***");

		Log.d(TAG, report.toString());

		saveAsFile(report.toString());
		new Thread() {
			public void run() {
				Looper.prepare();
				checkErrorAndSendMail(TaxAppContext.currentActivity());
				Looper.loop();
			}

		}.start();

		// SendErrorMail( Report );
		// previousHandler.uncaughtException(t, e);
	}

	/**
	 * 异常发送邮箱
	 * 
	 * @param _context
	 * @param errorContent
	 */
	private void sendErrorMail(final Context _context, final String errorContent) {

		final AlertDialog.Builder builder = new AlertDialog.Builder(_context);
		final AlertDialog alertDialog = builder.create();
		// builder.setIcon(android.R.drawable.ic_dialog_info);
		View layoutView = alertDialog.getLayoutInflater().inflate(
				R.layout.error_report_layout, null);
		builder.setView(layoutView);

		final AQuery aq = new AQuery(layoutView);
		builder.setTitle(R.string.exception_subject);

		builder.setNegativeButton(R.string.exit,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						// 退出

						TaxAppContext.removeAllActivities();
						System.exit(0);
					}
				}).show();

		// 发送异常报告
		if (TaxAppContext.isAutoMail) {
			sendMailAuto(errorContent, aq);
		} else {
			sendMailManual(_context, errorContent, aq);
		}

	}

	/**
	 * 自动发送邮件
	 * 
	 */
	private void sendMailAuto(final String errorContent, final AQuery aq) {
		Mail m = TaxAppContext.mail;
		String[] toArr = { FJDS_MAIL_ACCOUNT }; // This is an array, you
												// can add more emails,
												// just separate them
												// with a coma
		m.setTo(toArr); // load array to setTo function
		m.setFrom(FJDS_MAIL_ACCOUNT); // who is sending the email
		m.setSubject("subject");
		m.setBody(errorContent);
		try {

			if (m.send()) {
				Log.i(TAG, "Email was sent successfully.");
				// success
				new Handler().postDelayed(new Runnable() {

					@Override
					public void run() {
						aq.id(R.id.loading_progress).invisible();
						aq.id(R.id.send_error_report_message).text(
								R.string.send_error_report_success);
					}
				}, 1500l);
			} else {
				Log.i(TAG, "Email was not sent.");
				// success
				new Handler().postDelayed(new Runnable() {

					@Override
					public void run() {
						aq.id(R.id.loading_progress).invisible();
						aq.id(R.id.send_error_report_message).text(
								R.string.send_error_report_fail);
					}
				}, 1500l);
			}

		} catch (final Exception e) {
			Log.e(TAG, e.getMessage(), e);
			// some other problem
			new Handler().postDelayed(new Runnable() {

				@Override
				public void run() {
					aq.id(R.id.loading_progress).invisible();
					aq.id(R.id.send_error_report_message).text(
							R.string.send_error_report_fail + ":" + e.getMessage());
				}
			}, 1500l);
		}
		// progress.dismiss();
	}

	/**
	 * 人工手动发送错误报告邮件
	 * 
	 * @param _context
	 * @param errorContent
	 */
	private void sendMailManual(final Context _context,
			final String errorContent, final AQuery aq) {
		Intent i = new Intent(Intent.ACTION_SEND);

		String subject = _context.getResources().getString(
				R.string.exception_subject);
		String body = _context.getResources().getString(
				R.string.send_error_report_ing)
				+ "\n\n" + errorContent + "\n\n";
		// i.setType("text/plain"); //模拟器
		i.setType("message/rfc822"); // 真机
		i.putExtra(Intent.EXTRA_EMAIL, new String[] { "fjds2014@126.com" });
		i.putExtra(Intent.EXTRA_SUBJECT, subject);
		i.putExtra(Intent.EXTRA_TEXT, body);
		try {
			TaxAppContext.currentActivity().startActivity(
					Intent.createChooser(i, "发送错误报告"));
		} catch (android.content.ActivityNotFoundException ex) {
			Toast.makeText(TaxAppContext.currentActivity(),
					"No email clients installed.", Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * 保存文件
	 * 
	 * @param ErrorContent
	 */
	private void saveAsFile(String ErrorContent) {

		FileOutputStream trace = null;
		try {
			Random generator = new Random();
			int random = generator.nextInt(99999);
			String fileName = "stack-" + new Date().getTime() + "-" + random
					+ ".stacktrace";
			Log.d(TAG, "save file:" + fileName);
			trace = TaxAppContext.currentActivity().openFileOutput(fileName,
					Context.MODE_PRIVATE);

			trace.write(ErrorContent.getBytes());
			trace.flush();
			Log.d(TAG, "save file completely.("
					+ TaxAppContext.currentActivity().getFilesDir() + ")");
		} catch (IOException ioe) {
			Log.e(TAG, ioe.getMessage());
		} finally {
			if (trace != null) {
				try {
					trace.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	private String[] getErrorFileList() {
		File dir = new File(filePath + "");
		Log.d(TAG, "filepath:" + filePath);
		// Try to create the files folder if it doesn't exist
		dir.mkdir();
		// Filter for ".stacktrace" files
		FilenameFilter filter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.endsWith(".stacktrace");
			}
		};
		return dir.list(filter);
	}

	private boolean bIsThereAnyErrorFile() {
		return getErrorFileList().length > 0;
	}

	public void checkErrorAndSendMail(Context _context) {
		try {
			if (bIsThereAnyErrorFile()) {
				String WholeErrorText = "";
				String[] ErrorFileList = getErrorFileList();
				int curIndex = 0;
				// We limit the number of crash reports to send ( in order not
				// to be too slow )
				final int MaxSendMail = 5;
				for (String curString : ErrorFileList) {
					if (curIndex++ <= MaxSendMail) {
						WholeErrorText += "New Trace collected :\n";
						WholeErrorText += "=====================\n ";
						String ifilePath = TaxAppContext.currentActivity()
								.getFilesDir() + "/" + curString;
						BufferedReader input = new BufferedReader(
								new FileReader(ifilePath));
						String line;
						while ((line = input.readLine()) != null) {
							WholeErrorText += line + "\n";
						}
						input.close();
					}

					// DELETE FILES !!!!
					File curFile = new File(filePath + "/" + curString);
					curFile.delete();
				}
				sendErrorMail(_context, WholeErrorText);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}