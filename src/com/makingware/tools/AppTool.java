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
 * APP������
 * Android��װ��ɾ��(ж��)Ӧ���������(apk/app)
 * ��ȡϵͳ�Ѱ�װӦ���������(apk)��Ϣ
 * @author liux (http://my.oschina.net/liux)
 * @version 1.0
 * @created 2012-8-24
 */
public class AppTool {
	/**
	 * ��װapp
	 */
	public static void openAPK(File f, Context context) {
	    context.startActivity(getInstallApp(f, context));
	}

	public static Intent getInstallApp(File f, Context context) {
	    Intent intent = new Intent();
	    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	        //����Ӧ�õİ�װ��Դ������ȸ��г�
	    intent.putExtra("android.intent.extra.INSTALLER_PACKAGE_NAME", context.getPackageName());
	    intent.setAction(android.content.Intent.ACTION_VIEW);

	    /* ����intent��file */
	    intent.setDataAndType(Uri.fromFile(f), "application/vnd.android.package-archive");
	    return intent;
	}
	
	/**
	 * ж��APP
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
	 * ��ȡϵͳ�Ѱ�װAPP
	 * @param context
	 * @param getSysPackages �Ƿ��ȡϵͳAPP
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
	    	if ((p.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) > 0) { //ϵͳ����  
//	                || p.applicationInfo.packageName.equals(myPackageName)) { //�Ƿ������������
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
