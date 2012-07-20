package com.makingware.helloandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MusicActivity extends Activity{

	TextView txtView;
	Button btn_start;
	Button btn_stop;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.music_activity);
		
		txtView = (TextView)findViewById(R.id.music_txtView);
		txtView.setText(R.string.music_txtView_text);
		
		btn_start = (Button)findViewById(R.id.music_btn1);
		btn_start.setText(R.string.music_btn1_text);
		btn_start.setOnClickListener(start);
		
		btn_stop = (Button)findViewById(R.id.music_btn2);
		btn_stop.setText(R.string.music_btn2_text);
		btn_stop.setOnClickListener(stop);
		
	}
	
	//开始按钮
	private OnClickListener start = new OnClickListener()
    {
        public void onClick(View v)
        {   
        	//开启Service
            startService(new Intent("com.makingware.helloandroid.Music"));
        }
    };
   //停止按钮
    private OnClickListener stop = new OnClickListener()
    {
        public void onClick(View v)
        {
        	//停止Service
            stopService(new Intent("com.makingware.helloandroid.Music"));       
        }
    };


}
