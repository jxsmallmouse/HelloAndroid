package com.makingware.helloandroid;

import java.util.Iterator;

import android.app.Activity;
import android.content.Intent;
import android.location.Criteria;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;


public class LocationGPS extends Activity {
     private LocationManager locationManager;
     private GpsStatus gpsstatus;
     
     private String currentProvider;
     private Location currentLocation;
     
     TextView view;
     String str = "";
     
     @Override
     public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        view = new TextView(this);
        setContentView(view);
        
        //��ȡ��LocationManager����
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        
        //�жϵ�ǰ�Ƿ���GPS
        openGPSSettings();
            
        //����GPS״̬������
        //locationManager.addGpsStatusListener(gpsListener);
        
        //ֱ��������һ��λ����ϢΪֹ�����δ������һ��λ����Ϣ������ʾĬ�Ͼ�γ��
        //ÿ��10���ȡһ��λ����Ϣ
        getCurrentLocation();
     }
     
     @Override
    protected void onResume() {
    	super.onResume();
		 // ���ü��������Զ����µ���Сʱ��Ϊ���N��(1��Ϊ1*1000������д��ҪΪ�˷���)����Сλ�Ʊ仯����N�� 
        locationManager.requestLocationUpdates(currentProvider, 1000, 0, locationListener);
    }
     
     @Override
    protected void onPause() {
    	super.onPause();
      	//�ر�GPS
    	locationManager.removeUpdates(locationListener);
    	//locationManager.setTestProviderEnabled(currentProvider, false);
    	//locationManager.removeGpsStatusListener(gpsListener);
    }
     
     private void openGPSSettings() {
    	 if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
    	     Toast.makeText(this, "GPSģ������", Toast.LENGTH_SHORT).show();
    	     return;
    	 }
    	 else{
    	     Toast.makeText(this, "�뿪��GPS��", Toast.LENGTH_SHORT).show();
    	     Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
    	     startActivityForResult(intent,0); //��Ϊ������ɺ󷵻ص���ȡ����   
    	 }
     }
     
     private void getCurrentLocation()    {

         //�������õ�Criteria���󣬻�ȡ����ϴ˱�׼��provider����
         //currentProvider = locationManager.getProvider(LocationManager.GPS_PROVIDER).getName();
         
         //��GPS
         //locationManager.setTestProviderEnabled(currentProvider, true); 
         
       	// ���ҵ�������Ϣ
	   	Criteria criteria = new Criteria();
	   	criteria.setAccuracy(Criteria.ACCURACY_FINE);// �߾���
	   	criteria.setAltitudeRequired(false);// �޺�����Ϣ
	   	criteria.setBearingRequired(false);// �޷�λ��Ϣ
	   	criteria.setCostAllowed(true);// �Ƿ������� 
	   	criteria.setPowerRequirement(Criteria.POWER_LOW);// �͹���
	   		
	   	currentProvider = locationManager.getBestProvider(criteria, true);
         
         //���ݵ�ǰprovider�����ȡ���һ��λ����Ϣ--ȥ����䣬ÿ�ζ����ã�Ȼ�����ִ�оͽ���
         currentLocation = locationManager.getLastKnownLocation(currentProvider);
         
         if(currentLocation != null) {
         	view.setText(str + "\n�ϴ�λ�� ���ȣ�"+currentLocation.getLatitude()+"	γ�ȣ�"+currentLocation.getLongitude());
         }else{
         	view.setText(str + "\n�ϴ�λ�� ���ȣ�0	γ�ȣ�0");
         }
     	//locationManager.setTestProviderEnabled("gps", true);
     }
     
     private void updateLocation(Location location) {
     	if (location != null) {
     		double  latitude = location.getLatitude(); 
     		double longitude= location.getLongitude();     		
     		
            Log.d("LocationGPS", "Latitude: " + latitude);
            Log.d("LocationGPS", "location: " + longitude);
            
            str = view.getText().toString();
            view.setText(str + "\n��ǰ ���ȣ�"+latitude+"	γ�ȣ�"+longitude);
            Toast.makeText(this, "ά�ȣ�" +  latitude+ "\n����" + longitude, Toast.LENGTH_SHORT).show();
            
            //��λ����ǰλ�ú󣬹ر�GPS
            locationManager.removeUpdates(locationListener);
        	//locationManager.setTestProviderEnabled(currentProvider, false);

     	} else {
     		Log.d("LocationGPS", "Latitude: " + 0);
            Log.d("LocationGPS", "location: " + 0);
            
            view.setText(str + "\n��ǰ ���ȣ�0	γ�ȣ�0");
     		Toast.makeText(this, "�޷���ȡ������Ϣ", Toast.LENGTH_SHORT).show();
     	}
     }
     
     //����λ�ü�����
     private LocationListener locationListener = new LocationListener(){
         //λ�÷����ı�ʱ����
         //@Override
         public void onLocationChanged(Location location) {
             Log.d("LocationGPS", "λ�÷����仯");
                 
             updateLocation(location);
      		//locationManager.removeUpdates(this);
      		//locationManager.setTestProviderEnabled(provider, false);

         }
 
         //providerʧЧʱ����
         //@Override
         public void onProviderDisabled(String provider) {
             Log.d("LocationGPS", "�����ṩ��");
         }
 
         //provider����ʱ����
         //@Override
         public void onProviderEnabled(String provider) {
             Log.d("LocationGPS", "�����ṩ��");
         }
 
         //״̬�ı�ʱ����
         //@Override
         public void onStatusChanged(String provider, int status, Bundle extras) {
             Log.d("LocationGPS", "״̬�����仯");
         }
     };
     
     private GpsStatus.Listener gpsListener = new GpsStatus.Listener(){
         //GPS״̬�����仯ʱ����
         //@Override
         public void onGpsStatusChanged(int event) {
             //��ȡ��ǰ״̬
             gpsstatus=locationManager.getGpsStatus(null);
             switch(event){
                 //��һ�ζ�λʱ���¼�
                 case GpsStatus.GPS_EVENT_FIRST_FIX:
                     break;
                 //��ʼ��λ���¼�
                 case GpsStatus.GPS_EVENT_STARTED:
                     break;
                 //����GPS����״̬�¼�
                 case GpsStatus.GPS_EVENT_SATELLITE_STATUS:
                     Toast.makeText(LocationGPS.this, "GPS_EVENT_SATELLITE_STATUS", Toast.LENGTH_SHORT).show();
                     Iterable<GpsSatellite> allSatellites = gpsstatus.getSatellites();   
                     Iterator<GpsSatellite> it=allSatellites.iterator(); 
                     int count = 0;
                     while(it.hasNext())   
                     {   
                         count++;
                     }
                     Toast.makeText(LocationGPS.this, "Satellite Count:" + count, Toast.LENGTH_SHORT).show();
                     break;
                 //ֹͣ��λ�¼�
                 case GpsStatus.GPS_EVENT_STOPPED:
                     Log.d("LocationGPS", "GPS_EVENT_STOPPED");
                     break;
             }
         }
     };
     
}