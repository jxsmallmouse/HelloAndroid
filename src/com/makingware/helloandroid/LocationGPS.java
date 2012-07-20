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
        
        //获取到LocationManager对象
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        
        //判断当前是否开启GPS
        openGPSSettings();
            
        //增加GPS状态监听器
        //locationManager.addGpsStatusListener(gpsListener);
        
        //直到获得最后一次位置信息为止，如果未获得最后一次位置信息，则显示默认经纬度
        //每隔10秒获取一次位置信息
        getCurrentLocation();
     }
     
     @Override
    protected void onResume() {
    	super.onResume();
		 // 设置监听器，自动更新的最小时间为间隔N秒(1秒为1*1000，这样写主要为了方便)或最小位移变化超过N米 
        locationManager.requestLocationUpdates(currentProvider, 1000, 0, locationListener);
    }
     
     @Override
    protected void onPause() {
    	super.onPause();
      	//关闭GPS
    	locationManager.removeUpdates(locationListener);
    	//locationManager.setTestProviderEnabled(currentProvider, false);
    	//locationManager.removeGpsStatusListener(gpsListener);
    }
     
     private void openGPSSettings() {
    	 if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
    	     Toast.makeText(this, "GPS模块正常", Toast.LENGTH_SHORT).show();
    	     return;
    	 }
    	 else{
    	     Toast.makeText(this, "请开启GPS！", Toast.LENGTH_SHORT).show();
    	     Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
    	     startActivityForResult(intent,0); //此为设置完成后返回到获取界面   
    	 }
     }
     
     private void getCurrentLocation()    {

         //根据设置的Criteria对象，获取最符合此标准的provider对象
         //currentProvider = locationManager.getProvider(LocationManager.GPS_PROVIDER).getName();
         
         //打开GPS
         //locationManager.setTestProviderEnabled(currentProvider, true); 
         
       	// 查找到服务信息
	   	Criteria criteria = new Criteria();
	   	criteria.setAccuracy(Criteria.ACCURACY_FINE);// 高精度
	   	criteria.setAltitudeRequired(false);// 无海拔信息
	   	criteria.setBearingRequired(false);// 无方位信息
	   	criteria.setCostAllowed(true);// 是否允许付费 
	   	criteria.setPowerRequirement(Criteria.POWER_LOW);// 低功耗
	   		
	   	currentProvider = locationManager.getBestProvider(criteria, true);
         
         //根据当前provider对象获取最后一次位置信息--去掉这句，每次都会获得，然后你的执行就结束
         currentLocation = locationManager.getLastKnownLocation(currentProvider);
         
         if(currentLocation != null) {
         	view.setText(str + "\n上次位置 经度："+currentLocation.getLatitude()+"	纬度："+currentLocation.getLongitude());
         }else{
         	view.setText(str + "\n上次位置 经度：0	纬度：0");
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
            view.setText(str + "\n当前 经度："+latitude+"	纬度："+longitude);
            Toast.makeText(this, "维度：" +  latitude+ "\n经度" + longitude, Toast.LENGTH_SHORT).show();
            
            //定位到当前位置后，关闭GPS
            locationManager.removeUpdates(locationListener);
        	//locationManager.setTestProviderEnabled(currentProvider, false);

     	} else {
     		Log.d("LocationGPS", "Latitude: " + 0);
            Log.d("LocationGPS", "location: " + 0);
            
            view.setText(str + "\n当前 经度：0	纬度：0");
     		Toast.makeText(this, "无法获取地理信息", Toast.LENGTH_SHORT).show();
     	}
     }
     
     //创建位置监听器
     private LocationListener locationListener = new LocationListener(){
         //位置发生改变时调用
         //@Override
         public void onLocationChanged(Location location) {
             Log.d("LocationGPS", "位置发生变化");
                 
             updateLocation(location);
      		//locationManager.removeUpdates(this);
      		//locationManager.setTestProviderEnabled(provider, false);

         }
 
         //provider失效时调用
         //@Override
         public void onProviderDisabled(String provider) {
             Log.d("LocationGPS", "屏蔽提供商");
         }
 
         //provider启用时调用
         //@Override
         public void onProviderEnabled(String provider) {
             Log.d("LocationGPS", "激活提供商");
         }
 
         //状态改变时调用
         //@Override
         public void onStatusChanged(String provider, int status, Bundle extras) {
             Log.d("LocationGPS", "状态发生变化");
         }
     };
     
     private GpsStatus.Listener gpsListener = new GpsStatus.Listener(){
         //GPS状态发生变化时触发
         //@Override
         public void onGpsStatusChanged(int event) {
             //获取当前状态
             gpsstatus=locationManager.getGpsStatus(null);
             switch(event){
                 //第一次定位时的事件
                 case GpsStatus.GPS_EVENT_FIRST_FIX:
                     break;
                 //开始定位的事件
                 case GpsStatus.GPS_EVENT_STARTED:
                     break;
                 //发送GPS卫星状态事件
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
                 //停止定位事件
                 case GpsStatus.GPS_EVENT_STOPPED:
                     Log.d("LocationGPS", "GPS_EVENT_STOPPED");
                     break;
             }
         }
     };
     
}