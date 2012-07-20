package com.makingware.helloandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HelloActivity extends Activity {
	
	Button btn01;
	Button btn_readcontacts;
	Button btn_musicplayer;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
                
        //读取电话联系人
        btn_readcontacts = (Button)findViewById(R.id.button_readcontacts);
        btn_readcontacts.setText(R.string.readcontacts);
        btn_readcontacts.setOnClickListener(new ButtonListener());
        
        //音乐播放服务
        MusicPlayer();    
        
        Button btn_FlingGallery = (Button)findViewById(R.id.btn_FlingGallery);
        btn_FlingGallery.setOnClickListener(new OnClickListener() {	
			public void onClick(View arg0) {
				startActivity(new Intent(HelloActivity.this,FlingGalleryActivity.class));				
			}
		});
        
        Button btn_scrolllayout = (Button)findViewById(R.id.btn_scrolllayout);
        btn_scrolllayout.setOnClickListener(new OnClickListener() {	
			public void onClick(View arg0) {
				startActivity(new Intent(HelloActivity.this,ScrollLayoutActivity.class));
			}
		});
        Button btn_MyScrollLayout = (Button)findViewById(R.id.btn_MyScrollLayout);
        btn_MyScrollLayout.setOnClickListener(new OnClickListener() {	
			public void onClick(View arg0) {
				startActivity(new Intent(HelloActivity.this,MyScrollLayoutActivity.class));
			}
		});
        
        Button btn_viewflipper = (Button)findViewById(R.id.btn_viewflipper);
        btn_viewflipper.setOnClickListener(new OnClickListener() {	
			public void onClick(View arg0) {
				startActivity(new Intent(HelloActivity.this,ViewFlipperDemo.class));
			}
		});
        Button btn_viewflipper2 = (Button)findViewById(R.id.btn_viewflipper2);
        btn_viewflipper2.setOnClickListener(new OnClickListener() {	
			public void onClick(View arg0) {
				startActivity(new Intent(HelloActivity.this,ViewFlipperActivity.class));
			}
		});
        Button btn_viewflipper3 = (Button)findViewById(R.id.btn_viewflipper3);
        btn_viewflipper3.setOnClickListener(new OnClickListener() {	
			public void onClick(View arg0) {
				startActivity(new Intent(HelloActivity.this,SwitchViewActivity.class));
			}
		});
        
        Button btn_pageflipper = (Button)findViewById(R.id.btn_pageflipper);
        btn_pageflipper.setOnClickListener(new OnClickListener() {	
			public void onClick(View arg0) {
				startActivity(new Intent(HelloActivity.this,PageFlipperActivity.class));
			}
		});
        
        Button btn_listviewscroll = (Button)findViewById(R.id.btn_listviewscroll);
        btn_listviewscroll.setOnClickListener(new OnClickListener() {	
			public void onClick(View arg0) {
				startActivity(new Intent(HelloActivity.this,ListViewScrollLayoutActivity.class));
			}
		});
        
        Button btn_ExpandableListView = (Button)findViewById(R.id.btn_ExpandableListView);
        btn_ExpandableListView.setOnClickListener(new OnClickListener() {	
			public void onClick(View arg0) {
				startActivity(new Intent(HelloActivity.this,ExpandableActivity.class));
			}
		});
        Button btn_ExpandableListView02 = (Button)findViewById(R.id.btn_ExpandableListView02);
        btn_ExpandableListView02.setOnClickListener(new OnClickListener() {	
			public void onClick(View arg0) {
				startActivity(new Intent(HelloActivity.this,ExpandableListViewActivity.class));
			}
		});
        
        Button btn_flipactivty = (Button)findViewById(R.id.btn_flipactivty);
        btn_flipactivty.setOnClickListener(new OnClickListener() {	
			public void onClick(View arg0) {
				startActivity(new Intent(HelloActivity.this,Activity01.class));
			}
		});
        
        Button btn_ImageEditText = (Button)findViewById(R.id.btn_ImageEditText);
        btn_ImageEditText.setOnClickListener(new OnClickListener() {	
			public void onClick(View arg0) {
				startActivity(new Intent(HelloActivity.this,MyEditTextActivity.class));
			}
		});
        Button btn_TextHtml = (Button)findViewById(R.id.btn_TextHtml);
        btn_TextHtml.setOnClickListener(new OnClickListener() {	
			public void onClick(View arg0) {
				startActivity(new Intent(HelloActivity.this,TextHtml.class));
			}
		});
        
        Button btn_NetworkInfo = (Button)findViewById(R.id.btn_NetworkInfo);
        btn_NetworkInfo.setOnClickListener(new OnClickListener() {	
			public void onClick(View arg0) {
				startActivity(new Intent(HelloActivity.this,Networkinfo.class));
			}
		});
        
        Button btn_getip = (Button)findViewById(R.id.btn_getip);
        btn_getip.setOnClickListener(new OnClickListener() {	
			public void onClick(View arg0) {
				startActivity(new Intent(HelloActivity.this,GetIPActivity.class));
			}
		});
        
        Button btn_LocationManagerTest = (Button)findViewById(R.id.btn_LocationManagerTest);
        btn_LocationManagerTest.setOnClickListener(new OnClickListener() {	
			public void onClick(View arg0) {
				startActivity(new Intent(HelloActivity.this,LocationManagerTest.class));
			}
		});
        
        Button btn_LocationNetwork = (Button)findViewById(R.id.btn_LocationNetwork);
        btn_LocationNetwork.setOnClickListener(new OnClickListener() {	
			public void onClick(View arg0) {
				startActivity(new Intent(HelloActivity.this,LocationNetwork.class));
			}
		});
        
        Button btn_LocationGPS = (Button)findViewById(R.id.btn_LocationGPS);
        btn_LocationGPS.setOnClickListener(new OnClickListener() {	
			public void onClick(View arg0) {
				startActivity(new Intent(HelloActivity.this,LocationGPS.class));
			}
		});
        
        Button btn_Contextmenu = (Button)findViewById(R.id.btn_Contextmenu);
        btn_Contextmenu.setOnClickListener(new OnClickListener() {	
			public void onClick(View arg0) {
				startActivity(new Intent(HelloActivity.this,Test_Contextmenu.class));
			}
		});
        
        Button btn_ToastNotification = (Button)findViewById(R.id.btn_ToastNotification);
        btn_ToastNotification.setOnClickListener(new OnClickListener() {	
			public void onClick(View arg0) {
				startActivity(new Intent(HelloActivity.this,ToastAndNotification.class));
			}
		});
        
        Button btn_TestBoradcast = (Button)findViewById(R.id.btn_TestBoradcast);
        btn_TestBoradcast.setOnClickListener(new OnClickListener() {	
			public void onClick(View arg0) {
				startActivity(new Intent(HelloActivity.this,TestBoradcast.class));
			}
		});
        
        Button btn_quickaction = (Button)findViewById(R.id.btn_quickaction);
        btn_quickaction.setOnClickListener(new OnClickListener() {	
			public void onClick(View arg0) {
				startActivity(new Intent(HelloActivity.this,QuickActionActivity.class));
			}
		});
    }
    
    class ButtonListener implements OnClickListener{

		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			intent.setClass(HelloActivity.this, ReadContacts.class);
			HelloActivity.this.startActivity(intent);
			//HelloActivity.this.finish();
		}
    	
    }
    
    public void MusicPlayer(){
    	btn_musicplayer = (Button)findViewById(R.id.btn_musicplayer);
    	btn_musicplayer.setText(R.string.btn_music_player);
    	btn_musicplayer.setOnClickListener(new OnClickListener() {			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(HelloActivity.this,MusicActivity.class);
				startActivity(intent);
			}
		});
    	
    }
}