package com.makingware.tools;

import com.makingware.helloandroid.R;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Parcelable;

/**
 * Android 快捷键
 * 
 * @author liux (http://my.oschina.net/liux)
 * @version 1.0
 * @created 2012-8-24
 */
public class ShortCut {	
//	相关权限配置
//	<uses-permission android:name="com.android.launcher.permission.INSTALL_SHORTCUT" />
//	<uses-permission android:name="com.android.launcher.permission.UNINSTALL_SHORTCUT" />
//	<uses-permission android:name="com.android.launcher.permission.READ_SETTINGS" />
	
	/**
	 * 为当前应用添加桌面快捷方式
	 *
	 * @param cx
	 * @param appName
	 *            快捷方式名称
	 */
	public static void addShortcut(Context cx) {
	    Intent shortcut = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");

	    Intent shortcutIntent = cx.getPackageManager()
	            .getLaunchIntentForPackage(cx.getPackageName());
	    shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
	    // 获取当前应用名称
	    String title = null;
	    try {
	        final PackageManager pm = cx.getPackageManager();
	        title = pm.getApplicationLabel(
	                pm.getApplicationInfo(cx.getPackageName(),
	                        PackageManager.GET_META_DATA)).toString();
	    } catch (Exception e) {
	    }
	    // 快捷方式名称
	    shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, title);
	    // 不允许重复创建（不一定有效）
	    shortcut.putExtra("duplicate", false);
	    // 快捷方式的图标
	    Parcelable iconResource = Intent.ShortcutIconResource.fromContext(cx, R.drawable.icon);
	    shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, iconResource);

	    cx.sendBroadcast(shortcut);
	}
	
	/**
	 * 删除当前应用的桌面快捷方式
	 *
	 * @param cx
	 */
	public static void delShortcut(Context cx) {
	    Intent shortcut = new Intent(
	            "com.android.launcher.action.UNINSTALL_SHORTCUT");

	    // 获取当前应用名称
	    String title = null;
	    try {
	        final PackageManager pm = cx.getPackageManager();
	        title = pm.getApplicationLabel(
	                pm.getApplicationInfo(cx.getPackageName(),
	                        PackageManager.GET_META_DATA)).toString();
	    } catch (Exception e) {
	    }
	    // 快捷方式名称
	    shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, title);
	    Intent shortcutIntent = cx.getPackageManager()
	            .getLaunchIntentForPackage(cx.getPackageName());
	    shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
	    cx.sendBroadcast(shortcut);
	}
	
	/**
	 * 判断桌面是否已添加快捷方式
	 *
	 * @param cx
	 * @param titleName
	 *            快捷方式名称
	 * @return
	 */
	public static boolean hasShortcut(Context cx) {
	    boolean result = false;
	    // 获取当前应用名称
	    String title = null;
	    try {
	        final PackageManager pm = cx.getPackageManager();
	        title = pm.getApplicationLabel(
	                pm.getApplicationInfo(cx.getPackageName(),
	                        PackageManager.GET_META_DATA)).toString();
	    } catch (Exception e) {
	    }

	    final String uriStr;
	    if (android.os.Build.VERSION.SDK_INT < 8) {
	        uriStr = "content://com.android.launcher.settings/favorites?notify=true";
	    } else {
	        uriStr = "content://com.android.launcher2.settings/favorites?notify=true";
	    }
	    final Uri CONTENT_URI = Uri.parse(uriStr);
	    final Cursor c = cx.getContentResolver().query(CONTENT_URI, null,
	            "title=?", new String[] { title }, null);
	    if (c != null && c.getCount() > 0) {
	        result = true;
	    }
	    return result;
	}
}
