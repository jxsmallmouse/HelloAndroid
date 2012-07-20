package com.makingware.helloandroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * ��ʾ��������Ȩ��
 * <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
 */
public class Networkinfo extends Activity{

	Button btn1;
	Button btn2;
	Button btn3;
	Button btn4;
	
	Context context;

	private static final int WIFI = 1;
	private static final int CMWAP = 2;
	private static final int CMNET = 3;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		context = this;
		btn1 = new Button(this); btn1.setText("��⵱ǰ����״̬");
		btn2 = new Button(this); btn2.setText("��ǰ������뷽ʽ");
		btn3 = new Button(this); btn3.setText("��ת��������������");
		btn4 = new Button(this); btn4.setText("��ת������wifi����");
		
		LinearLayout view = new LinearLayout(this);
		LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT);
		view.setLayoutParams(params);
		view.setOrientation(LinearLayout.VERTICAL);
		view.addView(btn1);
		view.addView(btn2);
		view.addView(btn3);
		view.addView(btn4);
		setContentView(view);
		
		btn1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if(isNetworkConnected())
					Toast.makeText(context, "����������", 1500).show();
				else
					Toast.makeText(context, "����Ͽ�", 1500).show();
			}
		});
		btn2.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				int netType = getNetworkType();
				switch (netType) {
					case WIFI:	Toast.makeText(context, "��ǰ���磺WIFI", 1500).show();	break;
					case CMWAP:	Toast.makeText(context, "��ǰ���磺CMWAP", 1500).show();	break;
					case CMNET:	Toast.makeText(context, "��ǰ���磺CMNET", 1500).show();	break;
					default:	Toast.makeText(context, "��ǰ������", 1500).show();	break;
				}
			}
		});
		btn3.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// ��ת�������������ý���
				startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
			}
		});
		btn4.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// ��ת������wifi�������ý���
				startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
			}
		});
		
		//��������Ͽ����������Ӻ����ӳɹ�
		final TelephonyManager mTelephonyMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);  
		 mTelephonyMgr.listen(new PhoneStateListener(){
		                     
		     @Override
		     public void onDataConnectionStateChanged(int state) {
		         switch(state){
		             case TelephonyManager.DATA_DISCONNECTED://����Ͽ�
	            	 	Toast.makeText(context, "����Ͽ�", 1500).show();
		                  break;
		             case TelephonyManager.DATA_CONNECTING://������������
		            	 Toast.makeText(context, "������������", 1500).show();
		                  break;
		             case TelephonyManager.DATA_CONNECTED://����������
		            	 Toast.makeText(context, "����������", 1500).show();
		                  break;
		         }
		     }
		                         
		 },PhoneStateListener.LISTEN_DATA_CONNECTION_STATE);  
	}
	
	/**
	 * ��������Ƿ����
	 */
	public boolean isNetworkConnected() {
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
		return networkInfo != null && networkInfo.isConnectedOrConnecting();
	}
	
	/**
	 * ��ȡ��ǰ��������
	 * @return 0��û������   1��WIFI����   2��wap����    3��net����
	 */
	public int getNetworkType() {
		int netType = 0;
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
		if (networkInfo == null) {
			return netType;
		}

		//if(networkInfo != null)
		//	Toast.makeText( context, "Active Network Type : " + networkInfo.getTypeName(), 1500).show();
		
		int nType = networkInfo.getType();
		if (nType == ConnectivityManager.TYPE_MOBILE) {
			if (networkInfo.getExtraInfo().toLowerCase().equals("cmnet")) {
				netType = CMNET;
			} else {
				netType = CMWAP;
			}
		} else if (nType == ConnectivityManager.TYPE_WIFI) {
			netType = WIFI;
		}
		return netType;
	}
}
