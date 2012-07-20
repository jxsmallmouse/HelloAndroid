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
       
       //��ȡ��LocationManager����
       locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
       
       List<String> lp = locationManager.getAllProviders();
       for (String item:lp)
       {
    	   Log.d("LocationNetwork", "����λ�÷���: " + item);
    	   str += "\n����λ�÷���"+item;
       }
       
       //����һ��Criteria����
       Criteria criteria = new Criteria();
       //���þ�ȷ��		ACCURACY_COARSE����	ACCURACY_FINE��ȷ
       criteria.setAccuracy(Criteria.ACCURACY_COARSE);
       //�����Ƿ���Ҫ���غ�����Ϣ
       criteria.setAltitudeRequired(false);
       //�����Ƿ���Ҫ���ط�λ��Ϣ
       criteria.setBearingRequired(false);
       //�����Ƿ������ѷ���
       criteria.setCostAllowed(false);
       //���õ������ĵȼ�		POWER_HIGH��	  POWER_MEDIUM��      POWER_LOW��
       criteria.setPowerRequirement(Criteria.POWER_LOW);
       //�����Ƿ���Ҫ�����ٶ���Ϣ
       criteria.setSpeedRequired(false);

       //�������õ�Criteria���󣬻�ȡ����ϴ˱�׼��provider����
       currentProvider = locationManager.getBestProvider(criteria, true);
       Log.d("LocationNetwork", "��ǰ�ṩ��: " + currentProvider);
       str += "\n��ǰλ�÷���: " + currentProvider;
       view.setText(str);
       
       //���ݵ�ǰprovider�����ȡ���һ��λ����Ϣ
       Location currentLocation = locationManager.getLastKnownLocation(currentProvider);
       
       //������һ��λ����Ϣ
       currentLocation = locationManager.getLastKnownLocation(currentProvider);
       if(currentLocation != null){
           Log.d("LocationNetwork", "Latitude: " + currentLocation.getLatitude());
           Log.d("LocationNetwork", "location: " + currentLocation.getLongitude());
       }else{
           Log.d("LocationNetwork", "Latitude: " + 0);
           Log.d("LocationNetwork", "location: " + 0);
       }
       
       //������ַ����ʾ
       geoCoder = new Geocoder(this,Locale.CHINA);

	   str = view.getText().toString();
	   view.setText(str + "\n��ǰ  ���ȣ�"+currentLocation.getLatitude()+"	γ�ȣ�"+currentLocation.getLongitude());
       
       handler = new Handler(){
			@SuppressWarnings("unchecked")
			public void handleMessage(Message msg) {
				if(msg.what == 1){
					List<Address> list = (List<Address>)msg.obj;
					for(int i=0; i<list.size(); i++){
		               Address address = list.get(i); 
		               str = view.getText().toString();
		               view.setText(str + "\n���ң�"+address.getCountryName() + 
		            		   "\nʡ�ݣ�"+address.getAdminArea() + 
		            		   "\n���У�"+address.getLocality() + 
		            		   "\n����"+address.getSubLocality() + 
		            		   "\n�ص㣺"+address.getFeatureName());
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
		               view.setText(str + "\n������"+s);
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
    	//�������λ����Ϣ
    	locationManager.requestLocationUpdates(currentProvider, 1000, 0, locationListener);
    }
    
    @Override
    protected void onPause() {
    	super.onPause();
    	locationManager.removeUpdates(locationListener);
    }
    
    //����λ�ü�����
    private LocationListener locationListener = new LocationListener(){
        //λ�÷����ı�ʱ����
        //@Override
        public void onLocationChanged(Location location) {
            Log.d("LocationNetwork", "onLocationChanged");
            Log.d("LocationNetwork", "onLocationChanged Latitude" + location.getLatitude());
            Log.d("LocationNetwork", "onLocationChanged location" + location.getLongitude());
            
            getCurrentLocation(location);
        }

        //providerʧЧʱ����
        //@Override
        public void onProviderDisabled(String provider) {
            Log.d("LocationNetwork", "onProviderDisabled");
        }

        //provider����ʱ����
        //@Override
        public void onProviderEnabled(String provider) {
            Log.d("LocationNetwork", "onProviderEnabled");
        }

        //״̬�ı�ʱ����
        //@Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.d("LocationNetwork", "onStatusChanged");
        }
    };
}