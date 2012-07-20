package com.makingware.helloandroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.TextView;

public class LocationManagerTest extends Activity{
	private double latitude=0.0;
	private double longitude =0.0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		
		//�ж�GPS�����綨λ�ر�
		if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) && !locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
			startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)); 
		}
		
		if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
			Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			if(location != null){
				latitude = location.getLatitude();
				longitude = location.getLongitude();
			}
		}else{
			LocationListener locationListener = new LocationListener() {
				
				// Provider��״̬�ڿ��á���ʱ�����ú��޷�������״ֱ̬���л�ʱ�����˺���
				//@Override
				public void onStatusChanged(String provider, int status, Bundle extras) {
					
				}
				
				// Provider��enableʱ�����˺���������GPS����
				//@Override
				public void onProviderEnabled(String provider) {
					
				}
				
				// Provider��disableʱ�����˺���������GPS���ر� 
				//@Override
				public void onProviderDisabled(String provider) {
					
				}
				
				//������ı�ʱ�����˺��������Provider������ͬ�����꣬���Ͳ��ᱻ���� 
				//@Override
				public void onLocationChanged(Location location) {
					if (location != null) {   
						Log.w("Map", "Location changed : Lat: "  
						+ location.getLatitude() + " Lng: "  
						+ location.getLongitude());   
					}
				}
			};
			locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,1000, 0,locationListener);   
			Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);   
			if(location != null){   
				latitude = location.getLatitude(); //����   
				longitude = location.getLongitude(); //γ��
			}   
		}
		
		TextView txtview = new TextView(this);
		txtview.setText("��ǰ ���ȣ�"+latitude+"	γ�ȣ�"+longitude);
		
		setContentView(txtview);
	}
}
