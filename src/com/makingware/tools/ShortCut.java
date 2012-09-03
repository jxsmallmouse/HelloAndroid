package com.makingware.tools;

import com.makingware.helloandroid.R;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Parcelable;

/**
 * Android ��ݼ�
 * 
 * @author liux (http://my.oschina.net/liux)
 * @version 1.0
 * @created 2012-8-24
 */
public class ShortCut {	
//	���Ȩ������
//	<uses-permission android:name="com.android.launcher.permission.INSTALL_SHORTCUT" />
//	<uses-permission android:name="com.android.launcher.permission.UNINSTALL_SHORTCUT" />
//	<uses-permission android:name="com.android.launcher.permission.READ_SETTINGS" />
	
	/**
	 * Ϊ��ǰӦ����������ݷ�ʽ
	 *
	 * @param cx
	 * @param appName
	 *            ��ݷ�ʽ����
	 */
	public static void addShortcut(Context cx) {
	    Intent shortcut = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");

	    Intent shortcutIntent = cx.getPackageManager()
	            .getLaunchIntentForPackage(cx.getPackageName());
	    shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
	    // ��ȡ��ǰӦ������
	    String title = null;
	    try {
	        final PackageManager pm = cx.getPackageManager();
	        title = pm.getApplicationLabel(
	                pm.getApplicationInfo(cx.getPackageName(),
	                        PackageManager.GET_META_DATA)).toString();
	    } catch (Exception e) {
	    }
	    // ��ݷ�ʽ����
	    shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, title);
	    // �������ظ���������һ����Ч��
	    shortcut.putExtra("duplicate", false);
	    // ��ݷ�ʽ��ͼ��
	    Parcelable iconResource = Intent.ShortcutIconResource.fromContext(cx, R.drawable.icon);
	    shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, iconResource);

	    cx.sendBroadcast(shortcut);
	}
	
	/**
	 * ɾ����ǰӦ�õ������ݷ�ʽ
	 *
	 * @param cx
	 */
	public static void delShortcut(Context cx) {
	    Intent shortcut = new Intent(
	            "com.android.launcher.action.UNINSTALL_SHORTCUT");

	    // ��ȡ��ǰӦ������
	    String title = null;
	    try {
	        final PackageManager pm = cx.getPackageManager();
	        title = pm.getApplicationLabel(
	                pm.getApplicationInfo(cx.getPackageName(),
	                        PackageManager.GET_META_DATA)).toString();
	    } catch (Exception e) {
	    }
	    // ��ݷ�ʽ����
	    shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, title);
	    Intent shortcutIntent = cx.getPackageManager()
	            .getLaunchIntentForPackage(cx.getPackageName());
	    shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
	    cx.sendBroadcast(shortcut);
	}
	
	/**
	 * �ж������Ƿ�����ӿ�ݷ�ʽ
	 *
	 * @param cx
	 * @param titleName
	 *            ��ݷ�ʽ����
	 * @return
	 */
	public static boolean hasShortcut(Context cx) {
	    boolean result = false;
	    // ��ȡ��ǰӦ������
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
