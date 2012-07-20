package com.makingware.helloandroid;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class LocationNetwork extends Activity {
	
	TextView view;
	String str = "";
	Handler handler;
	
	LocationManager locationManager;
	String currentProvider;
	Geocoder geoCoder;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       
       view = new TextView(this);
       setContentView(view);
       
       //获取到LocationManager对象
       locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
       
       List<String> lp = locationManager.getAllProviders();
       for (String item:lp)
       {
    	   Log.d("LocationNetwork", "可用位置服务: " + item);
    	   str += "\n可用位置服务："+item;
       }
       
       //创建一个Criteria对象
       Criteria criteria = new Criteria();
       //设置精确度		ACCURACY_COARSE粗略	ACCURACY_FINE精确
       criteria.setAccuracy(Criteria.ACCURACY_COARSE);
       //设置是否需要返回海拔信息
       criteria.setAltitudeRequired(false);
       //设置是否需要返回方位信息
       criteria.setBearingRequired(false);
       //设置是否允许付费服务
       criteria.setCostAllowed(false);
       //设置电量消耗等级		POWER_HIGH高	  POWER_MEDIUM中      POWER_LOW低
       criteria.setPowerRequirement(Criteria.POWER_LOW);
       //设置是否需要返回速度信息
       criteria.setSpeedRequired(false);

       //根据设置的Criteria对象，获取最符合此标准的provider对象
       currentProvider = locationManager.getBestProvider(criteria, true);
       Log.d("LocationNetwork", "当前提供者: " + currentProvider);
       str += "\n当前位置服务: " + currentProvider;
       view.setText(str);
       
       //根据当前provider对象获取最后一次位置信息
       Location currentLocation = locationManager.getLastKnownLocation(currentProvider);
       
       //获得最后一次位置信息
       currentLocation = locationManager.getLastKnownLocation(currentProvider);
       if(currentLocation != null){
           Log.d("LocationNetwork", "Latitude: " + currentLocation.getLatitude());
           Log.d("LocationNetwork", "location: " + currentLocation.getLongitude());
       }else{
           Log.d("LocationNetwork", "Latitude: " + 0);
           Log.d("LocationNetwork", "location: " + 0);
       }
       
       //解析地址并显示
       geoCoder = new Geocoder(this,Locale.CHINA);

	   str = view.getText().toString();
	   view.setText(str + "\n当前  经度："+currentLocation.getLatitude()+"	纬度："+currentLocation.getLongitude());
       
       handler = new Handler(){
			@SuppressWarnings("unchecked")
			public void handleMessage(Message msg) {
				if(msg.what == 1){
					List<Address> list = (List<Address>)msg.obj;
					for(int i=0; i<list.size(); i++){
		               Address address = list.get(i); 
		               str = view.getText().toString();
		               view.setText(str + "\n国家："+address.getCountryName() + 
		            		   "\n省份："+address.getAdminArea() + 
		            		   "\n城市："+address.getLocality() + 
		            		   "\n区域："+address.getSubLocality() + 
		            		   "\n地点："+address.getFeatureName());
//		               System.out.println("-----------------("+i+")-----------------");
//		               System.out.println("CountryName: "+address.getCountryName());
//		               System.out.println("AdminArea: "+address.getAdminArea());
//		               System.out.println("SubAdminArea: "+address.getSubAdminArea());
//		               System.out.println("Locality: "+address.getLocality());
//		               System.out.println("SubLocality: "+address.getSubLocality());
//		               System.out.println("FeatureName: "+address.getFeatureName());
//		               System.out.println("SubThoroughfare: "+address.getSubThoroughfare());
//		               System.out.println("Premises: "+address.getPremises());
//		               System.out.println("Url: "+address.getUrl());
		               int k = address.getMaxAddressLineIndex();
		               String s = "";
		               for(int j=0; j<= k; j++){
//		            	   System.out.println("AddressLine"+j+": "+address.getAddressLine(j));
		            	   s +=  address.getAddressLine(j)+" - ";
		               }
		               str = view.getText().toString();
		               view.setText(str + "\n完整："+s);
					}
				}else{
					IOException e = (IOException)msg.obj;
					Toast.makeText(LocationNetwork.this,e.getMessage(), Toast.LENGTH_LONG).show();
				}
			}    	   
       };
       
       getCurrentLocation(currentLocation);
    }
    
    private void getCurrentLocation(final Location currentLocation){
    	new Thread(){
			public void run() {
				Message msg = new Message();
				try { 
			        List<Address> list = geoCoder.getFromLocation(currentLocation.getLatitude(), currentLocation.getLongitude(), 1);
			        msg.what = 1;
			        msg.obj = list;
			    } catch (IOException e) {
			    	e.printStackTrace();
		            msg.what = -1;
		            msg.obj = e;
			    }
				handler.sendMessage(msg);
			}    	   
       }.start();
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    	//请求更新位置信息
    	locationManager.requestLocationUpdates(currentProvider, 1000, 0, locationListener);
    }
    
    @Override
    protected void onPause() {
    	super.onPause();
    	locationManager.removeUpdates(locationListener);
    }
    
    //创建位置监听器
    private LocationListener locationListener = new LocationListener(){
        //位置发生改变时调用
        //@Override
        public void onLocationChanged(Location location) {
            Log.d("LocationNetwork", "onLocationChanged");
            Log.d("LocationNetwork", "onLocationChanged Latitude" + location.getLatitude());
            Log.d("LocationNetwork", "onLocationChanged location" + location.getLongitude());
            
            getCurrentLocation(location);
        }

        //provider失效时调用
        //@Override
        public void onProviderDisabled(String provider) {
            Log.d("LocationNetwork", "onProviderDisabled");
        }

        //provider启用时调用
        //@Override
        public void onProviderEnabled(String provider) {
            Log.d("LocationNetwork", "onProviderEnabled");
        }

        //状态改变时调用
        //@Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.d("LocationNetwork", "onStatusChanged");
        }
    };
}