package com.makingware.helloandroid;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class Activity01 extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		LayoutParams params= new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		LinearLayout layout = new LinearLayout(this);
		layout.setLayoutParams(params);
		layout.setBackgroundColor(Color.WHITE);
		
		Button btn01 = new Button(this);
		btn01.setText("切换到Activity02");
		btn01.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(Activity01.this,Activity02.class));
				//finish();
				
				//实现淡入淡出的效果 
				//overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);    

				//由左向右滑入的效果
				//overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);    

				//实现zoomin和zoomout,即类似iphone的进入和退出时的效果 
				//overridePendingTransition(R.anim.zoomin, R.anim.zoomout);    
				//overridePendingTransition(R.anim.zoomout, R.anim.zoomin);
				overridePendingTransition(R.anim.translucent_zoom_in, R.anim.translucent_zoom_out);    
			}
		});
		
		layout.addView(btn01);
		
		setContentView(layout);
	}

}
