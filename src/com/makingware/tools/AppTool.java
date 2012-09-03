package com.makingware.tools;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;

/**
 * APP管理类
 * Android安装和删除(卸载)应用软件程序(apk/app)
 * 获取系统已安装应用软件程序(apk)信息
 * @author liux (http://my.oschina.net/liux)
 * @version 1.0
 * @created 2012-8-24
 */
public class AppTool {
	/**
	 * 安装app
	 */
	public static void openAPK(File f, Context context) {
	    context.startActivity(getInstallApp(f, context));
	}

	public static Intent getInstallApp(File f, Context context) {
	    Intent intent = new Intent();
	    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	        //设置应用的安装来源，例如谷歌市场
	    intent.putExtra("android.intent.extra.INSTALLER_PACKAGE_NAME", context.getPackageName());
	    intent.setAction(android.content.Intent.ACTION_VIEW);

	    /* 设置intent的file */
	    intent.setDataAndType(Uri.fromFile(f), "application/vnd.android.package-archive");
	    return intent;
	}
	
	/**
	 * 卸载APP
	 * @param context
	 * @param packageName
	 */
	public static void uninstallApp(Context context,String packageName) {
	    Uri packageURI = Uri.parse("package:" + packageName);  
	    Intent uninstallIntent = new Intent(Intent.ACTION_DELETE, packageURI);  
	    uninstallIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	    context.startActivity(uninstallIntent);
	}
	
	/**
	 * 获取系统已安装APP
	 * @param context
	 * @param getSysPackages 是否获取系统APP
	 * @return
	 */
	public static ArrayList<AppInfo> getInstalledApps(Context context,boolean getSysPackages) {
	    PackageManager pm = context.getPackageManager();
	    ArrayList<AppInfo> res = new ArrayList<AppInfo>();          
	    List<PackageInfo> packs = pm.getInstalledPackages(0);
	    int length = packs.size();
	    PackageInfo p;
	    String myPackageName = context.getApplicationInfo().packageName;
	    for(int i=0;i < length;i++) {  
	    	p = packs.get(i);  
	    	if ((p.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) > 0) { //系统程序  
//	                || p.applicationInfo.packageName.equals(myPackageName)) { //是否包括程序自身
	    		continue ;  
	    	}      
	    	AppInfo info = new AppInfo();  
	    	info.name = p.applicationInfo.loadLabel(pm).toString();  
	    	info.packageName = p.packageName;  
	    	info.versionName = p.versionName;  
	    	info.versionCode = p.versionCode;  
	    	res.add(info);  
	    }  
	    return res;
	}
	
	public static class AppInfo {
		String name;
		String packageName;
		String versionName;
		int versionCode;
	}
}
